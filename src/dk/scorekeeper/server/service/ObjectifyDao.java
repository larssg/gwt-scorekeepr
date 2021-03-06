package dk.scorekeeper.server.service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Transient;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

import dk.scorekeeper.shared.TooManyResultsException;
import dk.scorekeeper.shared.domain.Game;
import dk.scorekeeper.shared.domain.Match;
import dk.scorekeeper.shared.domain.User;

public class ObjectifyDao<T> extends DAOBase {
	static {
		ObjectifyService.register(Match.class);
		ObjectifyService.register(Game.class);
		ObjectifyService.register(User.class);
	}


	static final int BAD_MODIFIERS = Modifier.FINAL | Modifier.STATIC
	| Modifier.TRANSIENT;

	protected Class<T> clazz;

	@SuppressWarnings("unchecked")
	public ObjectifyDao()
	{
		clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Query<T> buildQueryByExample(T exampleObj)
	{
		Query<T> q = ofy().query(clazz);

		// Add all non-null properties to query filter
		for (Field field : clazz.getDeclaredFields())
		{
			// Ignore transient, embedded, array, and collection properties
			if (field.isAnnotationPresent(Transient.class)
					|| field.isAnnotationPresent(Embedded.class)
					|| field.getType().isArray()
					|| field.getType().isArray()
					|| Collection.class.isAssignableFrom(field.getType())
					|| (field.getModifiers() & BAD_MODIFIERS) != 0) {
				continue;
			}

			field.setAccessible(true);

			Object value;
			try
			{
				value = field.get(exampleObj);
			} catch (IllegalArgumentException e)
			{
				throw new RuntimeException(e);
			} catch (IllegalAccessException e)
			{
				throw new RuntimeException(e);
			}
			if (value != null)
			{
				q.filter(field.getName(), value);
			}
		}

		return q;
	}

	public void delete(T entity)
	{
		ofy().delete(entity);
	}

	public void deleteAll(Iterable<T> entities)
	{
		ofy().delete(entities);
	}

	public void deleteKey(Key<T> entityKey)
	{
		ofy().delete(entityKey);
	}

	public void deleteKeys(Iterable<Key<T>> keys)
	{
		ofy().delete(keys);
	}

	public Map<Key<T>, T> get(Iterable<Key<T>> keys)
	{
		return ofy().get(keys);
	}

	public T get(Key<T> key) throws EntityNotFoundException
	{
		return ofy().get(key);
	}

	public T get(Long id) throws EntityNotFoundException
	{
		return ofy().get(this.clazz, id);
	}

	public T getByExample(T exampleObj) throws TooManyResultsException
	{
		Query<T> q = buildQueryByExample(exampleObj);
		Iterator<T> fetch = q.limit(2).list().iterator();
		if (!fetch.hasNext())
		{
			return null;
		}
		T obj = fetch.next();
		if (fetch.hasNext())
		{
			throw new TooManyResultsException(q.toString()
					+ " returned too many results");
		}
		return obj;
	}

	/**
	 * Convenience method to get all objects matching a single property
	 * 
	 * @param propName
	 * @param propValue
	 * @return T matching Object
	 * @throws TooManyResultsException
	 */
	public T getByProperty(String propName, Object propValue)
	throws TooManyResultsException
	{
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		Iterator<T> fetch = q.limit(2).list().iterator();
		if (!fetch.hasNext())
		{
			return null;
		}
		T obj = fetch.next();
		if (fetch.hasNext())
		{
			throw new TooManyResultsException(q.toString()
					+ " returned too many results");
		}
		return obj;
	}

	public Key<T> getKey(Long id)
	{
		return new Key<T>(this.clazz, id);
	}

	public Key<T> key(T obj)
	{
		return ObjectifyService.factory().getKey(obj);
	}

	public List<T> listAll()
	{
		Query<T> q = ofy().query(clazz);
		return q.list();
	}

	public List<T> listByExample(T exampleObj)
	{
		Query<T> queryByExample = buildQueryByExample(exampleObj);
		return queryByExample.list();
	}

	public List<T> listByProperty(String propName, Object propValue)
	{
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return q.list();
	}

	public List<Key<T>> listChildKeys(Object parent)
	{
		return ofy().query(clazz).ancestor(parent).listKeys();
	}

	public List<T> listChildren(Object parent)
	{
		return ofy().query(clazz).ancestor(parent).list();
	}

	public List<Key<T>> listKeysByProperty(String propName, Object propValue)
	{
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return q.listKeys();
	}

	public Key<T> put(T entity)
	{
		return ofy().put(entity);
	}

	public Map<Key<T>, T> putAll(Iterable<T> entities)
	{
		return ofy().put(entities);
	}

	public T saveAndReturn(T entity) {
		put(entity);
		return entity;
	}
}
