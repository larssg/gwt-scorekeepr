package dk.scorekeeper.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

import dk.scorekeeper.client.gin.ClientGinjector;
import dk.scorekeeper.client.resources.Resources;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScoreKeeper implements EntryPoint {
	private final ClientGinjector injector = GWT.create(ClientGinjector.class);

	private void injectStylesheets() {
		Resources.INSTANCE.reset().ensureInjected();
		Resources.INSTANCE.text().ensureInjected();
		Resources.INSTANCE.grid().ensureInjected();
		Resources.INSTANCE.screen().ensureInjected();
	}

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		injectStylesheets();

		// This is required for Gwt-Platform proxy's generator.
		DelayedBindRegistry.bind(injector);

		injector.getPlaceManager().revealCurrentPlace();
	}
}
