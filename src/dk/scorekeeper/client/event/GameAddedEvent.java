package dk.scorekeeper.client.event;

import com.google.gwt.event.shared.GwtEvent;

import dk.scorekeeper.shared.domain.Game;

public class GameAddedEvent extends GwtEvent<GameAddedEventHandler> {

	public static Type<GameAddedEventHandler> TYPE = new Type<GameAddedEventHandler>();
	private final Game game;

	public GameAddedEvent(Game game) {
		this.game = game;
	}

	@Override
	public Type<GameAddedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GameAddedEventHandler handler) {
		handler.onEvent(this);
	}

	public Game getGame() {
		return game;
	}

}
