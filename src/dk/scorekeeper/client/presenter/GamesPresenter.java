package dk.scorekeeper.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import dk.scorekeeper.client.NameTokens;

public class GamesPresenter extends Presenter<GamesPresenter.MyView, GamesPresenter.MyProxy> {
	@ProxyCodeSplit
	@NameToken(NameTokens.gamesPage)
	public interface MyProxy extends ProxyPlace<GamesPresenter> {
	}

	public interface MyView extends View {
	}

	@Inject
	public GamesPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
}
