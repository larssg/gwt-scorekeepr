package dk.scorekeeper.shared.requestfactory;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface ScoreKeeperRequestFactory extends RequestFactory {
	GameRequest gameRequest();
	MatchRequest matchRequest();
	UserRequest userRequest();
}
