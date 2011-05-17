package dk.scorekeeper.client.events;

import com.google.gwt.event.shared.GwtEvent;

import dk.scorekeeper.shared.domain.User;

public class UserAddedEvent extends GwtEvent<UserAddedEventHandler> {

	public static Type<UserAddedEventHandler> TYPE = new Type<UserAddedEventHandler>();

	private final User user;

	public UserAddedEvent(User user) {
		this.user = user;
	}

	@Override
	protected void dispatch(UserAddedEventHandler handler) {
		handler.onEvent(this);
	}

	@Override
	public Type<UserAddedEventHandler> getAssociatedType() {
		return TYPE;
	}

	public User getUser() {
		return user;
	}

}
