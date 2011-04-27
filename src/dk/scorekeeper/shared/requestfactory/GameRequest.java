package dk.scorekeeper.shared.requestfactory;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

import dk.scorekeeper.server.domain.Game;
import dk.scorekeeper.shared.domain.proxy.GameProxy;

@Service(Game.class)
public interface GameRequest extends RequestContext {
	InstanceRequest<GameProxy, Void> persist();
}
