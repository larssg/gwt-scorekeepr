package dk.scorekeeper.client;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

import dk.scorekeeper.client.gin.DefaultPlace;

public class ScoreKeeperPlaceManager extends PlaceManagerImpl {
	private final PlaceRequest defaultPlaceRequest;

	@Inject
	public ScoreKeeperPlaceManager(final EventBus eventBus, final TokenFormatter tokenFormatter, @DefaultPlace String defaultNameToken) {
		super(eventBus, tokenFormatter);
		defaultPlaceRequest = new PlaceRequest(defaultNameToken);
	}

	@Override
	public void revealDefaultPlace() {
		revealPlace(defaultPlaceRequest);
	}
}
