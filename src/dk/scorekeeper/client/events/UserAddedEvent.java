package dk.scorekeeper.client.events;

import com.google.gwt.event.shared.GwtEvent;

import dk.scorekeeper.shared.domain.proxy.UserProxy;

public class UserAddedEvent extends GwtEvent<UserAddedEventHandler> {

	public static Type<UserAddedEventHandler> TYPE = new Type<UserAddedEventHandler>();

	private final UserProxy user;

	public UserAddedEvent(UserProxy user) {
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

	public UserProxy getUser() {
		return user;
	}

}
