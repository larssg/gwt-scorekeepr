package dk.scorekeeper.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ClientModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		// Singletons
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

		// bind(PlaceManager.class).to(GwtpnestedsamplePlaceManager.class).in(Singleton.class);
		// bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);

		// bind(RootPresenter.class).asEagerSingleton();

		// bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class).in(Singleton.class);
	}
}
