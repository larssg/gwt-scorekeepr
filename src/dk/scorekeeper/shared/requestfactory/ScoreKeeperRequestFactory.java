package dk.scorekeeper.shared.requestfactory;

import com.google.gwt.requestfactory.shared.RequestFactory;

public interface ScoreKeeperRequestFactory extends RequestFactory {
	GameRequest gameRequest();
	UserRequest userRequest();
}
