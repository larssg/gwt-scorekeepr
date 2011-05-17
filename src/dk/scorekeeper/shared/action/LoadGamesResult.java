package dk.scorekeeper.shared.action;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;

import dk.scorekeeper.shared.domain.Game;

public class LoadGamesResult implements Result {
	private List<Game> games;

	@SuppressWarnings("unused")
	private LoadGamesResult() {
	}

	public LoadGamesResult(final List<Game> games) {
		this.games = games;
	}

	public List<Game> getGames() {
		return games;
	}
}
