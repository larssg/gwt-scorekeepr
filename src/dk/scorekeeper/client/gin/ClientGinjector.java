package dk.scorekeeper.client.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;

import dk.scorekeeper.client.presenter.GamesPresenter;
import dk.scorekeeper.client.presenter.HomePresenter;
import dk.scorekeeper.client.presenter.MainPagePresenter;
import dk.scorekeeper.client.presenter.UsersPresenter;

@GinModules({DispatchAsyncModule.class, ClientModule.class})
public interface ClientGinjector extends Ginjector {
	EventBus getEventBus();
	AsyncProvider<GamesPresenter> getGamesPresenter();
	Provider<HomePresenter> getHomePresenter();
	Provider<MainPagePresenter> getMainPagePresenter();
	PlaceManager getPlaceManager();
	ProxyFailureHandler getProxyFailureHandler();

	AsyncProvider<UsersPresenter> getUsersPresenter();
}
