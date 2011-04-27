package dk.scorekeeper.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface UserAddedEventHandler extends EventHandler {
	void onEvent(UserAddedEvent event);
}
