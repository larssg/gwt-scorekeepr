package dk.scorekeeper.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(ClientModule.class)
public interface ClientGinjector extends Ginjector {
	EventBus getEventBus();
}
