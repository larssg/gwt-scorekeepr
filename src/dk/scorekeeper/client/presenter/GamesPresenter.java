package dk.scorekeeper.client.presenter;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import dk.scorekeeper.client.NameTokens;
import dk.scorekeeper.shared.action.LoadGamesAction;
import dk.scorekeeper.shared.action.LoadGamesResult;
import dk.scorekeeper.shared.domain.Game;

public class GamesPresenter extends Presenter<GamesPresenter.MyView, GamesPresenter.MyProxy> {
	@ProxyCodeSplit
	@NameToken(NameTokens.gamesPage)
	public interface MyProxy extends ProxyPlace<GamesPresenter> {
	}

	public interface MyView extends View {
		void setGames(List<Game> games);
	}

	private final DispatchAsync dispatcher;
	private final MyView view;

	@Inject
	public GamesPresenter(EventBus eventBus, final MyView view, MyProxy proxy, DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		this.view = view;
		this.dispatcher = dispatcher;
	}

	@Override
	protected void onReset() {
		super.onReset();

		dispatcher.execute(new LoadGamesAction(), new AsyncCallback<LoadGamesResult>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(LoadGamesResult result) {
				view.setGames(result.getGames());
			}
		});
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
}
