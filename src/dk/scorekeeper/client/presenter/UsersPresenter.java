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

public class UsersPresenter extends Presenter<UsersPresenter.MyView, UsersPresenter.MyProxy> {
	@ProxyCodeSplit
	@NameToken(NameTokens.usersPage)
	public interface MyProxy extends ProxyPlace<UsersPresenter> {
	}

	public interface MyView extends View {
	}

	@Inject
	public UsersPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
}
