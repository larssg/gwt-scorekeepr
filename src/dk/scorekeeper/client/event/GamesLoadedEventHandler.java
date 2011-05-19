package dk.scorekeeper.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface GamesLoadedEventHandler extends EventHandler {
	void onEvent(GamesLoadedEvent event);
}
