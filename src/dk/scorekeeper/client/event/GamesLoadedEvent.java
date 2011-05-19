package dk.scorekeeper.client.event;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;

import dk.scorekeeper.shared.domain.Game;

public class GamesLoadedEvent extends GwtEvent<GamesLoadedEventHandler> {

	public static Type<GamesLoadedEventHandler> TYPE = new Type<GamesLoadedEventHandler>();

	private final List<Game> games;

	public GamesLoadedEvent(List<Game> games) {
		this.games = games;
	}

	@Override
	public Type<GamesLoadedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GamesLoadedEventHandler handler) {
		handler.onEvent(this);
	}

	public List<Game> getGames() {
		return games;
	}

}
