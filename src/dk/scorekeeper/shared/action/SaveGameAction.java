package dk.scorekeeper.shared.action;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

import dk.scorekeeper.shared.domain.Game;

public class SaveGameAction extends UnsecuredActionImpl<SaveGameResult> {
	private Game game;

	@SuppressWarnings("unused")
	private SaveGameAction() {
	}

	public SaveGameAction(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}
}
