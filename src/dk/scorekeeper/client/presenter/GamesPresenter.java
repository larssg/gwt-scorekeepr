package dk.scorekeeper.client.presenter;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import dk.scorekeeper.client.NameTokens;
import dk.scorekeeper.shared.domain.proxy.GameProxy;
import dk.scorekeeper.shared.requestfactory.ScoreKeeperRequestFactory;

public class GamesPresenter extends Presenter<GamesPresenter.MyView, GamesPresenter.MyProxy> {
	@ProxyCodeSplit
	@NameToken(NameTokens.gamesPage)
	public interface MyProxy extends ProxyPlace<GamesPresenter> {
	}

	public interface MyView extends View {
		void setGames(List<GameProxy> games);
	}

	@Inject
	public GamesPresenter(EventBus eventBus, final MyView view, MyProxy proxy, ScoreKeeperRequestFactory requestFactory) {
		super(eventBus, view, proxy);

		requestFactory.gameRequest().listAll().fire(new Receiver<List<GameProxy>>() {
			@Override
			public void onSuccess(List<GameProxy> games) {
				view.setGames(games);
			}
		});
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
}
