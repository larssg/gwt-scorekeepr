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
import dk.scorekeeper.client.events.UsersLoadedEvent;
import dk.scorekeeper.shared.domain.proxy.UserProxy;
import dk.scorekeeper.shared.requestfactory.ScoreKeeperRequestFactory;

public class UsersPresenter extends Presenter<UsersPresenter.MyView, UsersPresenter.MyProxy> {
	@ProxyCodeSplit
	@NameToken(NameTokens.usersPage)
	public interface MyProxy extends ProxyPlace<UsersPresenter> {
	}

	public interface MyView extends View {
	}

	@Inject
	public UsersPresenter(final EventBus eventBus, MyView view, MyProxy proxy, ScoreKeeperRequestFactory requestFactory) {
		super(eventBus, view, proxy);

		requestFactory.userRequest().listAll().fire(new Receiver<List<UserProxy>>() {
			@Override
			public void onSuccess(List<UserProxy> users) {
				UsersLoadedEvent event = new UsersLoadedEvent(users);
				eventBus.fireEvent(event);
			}
		});
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
}
