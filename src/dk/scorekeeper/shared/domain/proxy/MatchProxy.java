package dk.scorekeeper.shared.domain.proxy;

import java.util.Date;
import java.util.List;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

import dk.scorekeeper.server.domain.Match;
import dk.scorekeeper.server.service.locator.ObjectifyLocator;

@ProxyFor(value = Match.class, locator = ObjectifyLocator.class)
public interface MatchProxy extends DatastoreObjectProxy {
	Date getPlayedAt();
	List<Integer> getScores();
	List<Long> getTeamOne();
	List<Long> getTeamTwo();
	void setPlayedAt(Date playedAt);
	void setScores(List<Integer> scores);
	void setTeamOne(List<Long> teamOne);
	void setTeamTwo(List<Long> teamTwo);
}
