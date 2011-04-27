package dk.scorekeeper.shared.domain.proxy;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

import dk.scorekeeper.server.domain.Game;

@ProxyFor(Game.class)
public interface GameProxy extends EntityProxy {
	Long getId();
	String getName();
	void setName(String name);
}
