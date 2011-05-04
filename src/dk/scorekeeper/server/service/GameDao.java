package dk.scorekeeper.server.service;

import dk.scorekeeper.server.domain.Game;

public class GameDao extends ObjectifyDao<Game> {
	public Game saveAndReturn(Game game) {
		put(game);
		return game;
	}
}
