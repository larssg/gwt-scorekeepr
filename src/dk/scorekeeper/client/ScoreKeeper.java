package dk.scorekeeper.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import dk.scorekeeper.client.gin.ClientGinjector;
import dk.scorekeeper.client.resources.Resources;
import dk.scorekeeper.client.views.MainView;

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
	public void onModuleLoad() {
		injectStylesheets();

		MainView mainView = new MainView(injector.getEventBus());
		RootPanel.get().add(mainView);
	}
}
