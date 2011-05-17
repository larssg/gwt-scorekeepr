package dk.scorekeeper.client.events;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;

import dk.scorekeeper.shared.domain.User;

public class UsersLoadedEvent extends GwtEvent<UsersLoadedEventHandler> {

	public static Type<UsersLoadedEventHandler> TYPE = new Type<UsersLoadedEventHandler>();

	private final List<User> users;

	public UsersLoadedEvent(List<User> users) {
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

	public List<User> getUsers() {
		return users;
	}
}
