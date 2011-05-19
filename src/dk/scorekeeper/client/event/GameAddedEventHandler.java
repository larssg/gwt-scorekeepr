package dk.scorekeeper.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface GameAddedEventHandler extends EventHandler {
	void onEvent(GameAddedEvent event);
}
