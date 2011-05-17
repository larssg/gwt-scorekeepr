package dk.scorekeeper.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface GameAddedEventHandler extends EventHandler {
	void onEvent(GameAddedEvent event);
}
