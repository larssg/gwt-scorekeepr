package dk.scorekeeper.server.domain;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

@Unindexed
public class Match extends DatastoreObject {
	@Indexed
	private List<Long> teamOne;

	@Indexed
	private List<Long> teamTwo;

	private List<Integer> scores;

	@Indexed
	private Date playedAt;

	public Date getPlayedAt() {
		return playedAt;
	}

	public List<Integer> getScores() {
		return scores;
	}

	public List<Long> getTeamOne() {
		return teamOne;
	}

	public List<Long> getTeamTwo() {
		return teamTwo;
	}

	public void setPlayedAt(Date playedAt) {
		this.playedAt = playedAt;
	}

	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}

	public void setTeamOne(List<Long> teamOne) {
		this.teamOne = teamOne;
	}

	public void setTeamTwo(List<Long> teamTwo) {
		this.teamTwo = teamTwo;
	}

}
