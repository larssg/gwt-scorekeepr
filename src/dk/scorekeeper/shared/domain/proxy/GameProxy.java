package dk.scorekeeper.shared.domain.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

import dk.scorekeeper.server.domain.Game;
import dk.scorekeeper.server.service.locator.ObjectifyLocator;

@ProxyFor(value = Game.class, locator = ObjectifyLocator.class)
public interface GameProxy extends DatastoreObjectProxy {
	String getName();
	void setName(String name);
}
