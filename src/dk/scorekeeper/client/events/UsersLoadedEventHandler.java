package dk.scorekeeper.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface UsersLoadedEventHandler extends EventHandler {
	void onEvent(UsersLoadedEvent event);
}
