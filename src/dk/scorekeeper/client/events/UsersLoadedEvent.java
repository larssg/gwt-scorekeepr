package dk.scorekeeper.client.events;

import java.util.ArrayList;

import com.google.gwt.event.shared.GwtEvent;

import dk.scorekeeper.shared.entities.User;

public class UsersLoadedEvent extends GwtEvent<UsersLoadedEventHandler> {

	public static Type<UsersLoadedEventHandler> TYPE = new Type<UsersLoadedEventHandler>();

	private final ArrayList<User> users;

	public UsersLoadedEvent(ArrayList<User> users) {
		this.users = users;
	}

	@Override
	protected void dispatch(UsersLoadedEventHandler handler) {
		handler.onEvent(this);
	}

	@Override
	public Type<UsersLoadedEventHandler> getAssociatedType() {
		return TYPE;
	}

	public ArrayList<User> getUsers() {
		return users;
	}
}
