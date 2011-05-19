package dk.scorekeeper.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface UserAddedEventHandler extends EventHandler {
	void onEvent(UserAddedEvent event);
}
