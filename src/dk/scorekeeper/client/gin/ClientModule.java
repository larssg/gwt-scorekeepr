package dk.scorekeeper.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.DefaultProxyFailureHandler;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

import dk.scorekeeper.client.NameTokens;
import dk.scorekeeper.client.ScoreKeeperPlaceManager;
import dk.scorekeeper.client.presenter.GamesPresenter;
import dk.scorekeeper.client.presenter.HomePresenter;
import dk.scorekeeper.client.presenter.MainPagePresenter;
import dk.scorekeeper.client.presenter.UsersPresenter;
import dk.scorekeeper.client.views.MainPageView;
import dk.scorekeeper.client.views.games.GamesView;
import dk.scorekeeper.client.views.home.HomeView;
import dk.scorekeeper.client.views.users.UsersView;
import dk.scorekeeper.shared.requestfactory.ScoreKeeperRequestFactory;

public class ClientModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		// Singletons
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

		bind(PlaceManager.class).to(ScoreKeeperPlaceManager.class).in(Singleton.class);

		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);

		bind(RootPresenter.class).asEagerSingleton();

		bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class).in(Singleton.class);

		// Constants
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.homePage);

		// Presenters
		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
				MainPageView.class, MainPagePresenter.MyProxy.class);

		bindPresenter(HomePresenter.class, HomePresenter.MyView.class,
				HomeView.class, HomePresenter.MyProxy.class);

		bindPresenter(UsersPresenter.class, UsersPresenter.MyView.class,
				UsersView.class, UsersPresenter.MyProxy.class);

		bindPresenter(GamesPresenter.class, GamesPresenter.MyView.class,
				GamesView.class, GamesPresenter.MyProxy.class);
	}

	@Provides
	@Singleton
	ScoreKeeperRequestFactory provideScoreKeeperRequestFactory(EventBus eventBus) {
		ScoreKeeperRequestFactory requestFactory = GWT.create(ScoreKeeperRequestFactory.class);
		requestFactory.initialize(eventBus);
		return requestFactory;
	}
}
