package dk.scorekeeper.shared.action;

import com.gwtplatform.dispatch.shared.Result;

import dk.scorekeeper.shared.domain.Game;

public class SaveGameResult implements Result {
	private Game game;

	@SuppressWarnings("unused")
	private SaveGameResult() {
	}

	public SaveGameResult(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}
}
