package dk.scorekeeper.client.presenter;

import java.util.ArrayList;
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
import dk.scorekeeper.client.events.UsersLoadedEvent;
import dk.scorekeeper.shared.action.LoadUsersAction;
import dk.scorekeeper.shared.action.LoadUsersResult;
import dk.scorekeeper.shared.domain.User;

public class UsersPresenter extends
		Presenter<UsersPresenter.MyView, UsersPresenter.MyProxy> {
	@ProxyCodeSplit
	@NameToken(NameTokens.usersPage)
	public interface MyProxy extends ProxyPlace<UsersPresenter> {
	}

	public interface MyView extends View {
	}

	private final DispatchAsync dispatcher;
	private final EventBus eventBus;

	private List<User> users = new ArrayList<User>();

	@Inject
	public UsersPresenter(final EventBus eventBus, MyView view, MyProxy proxy,
			DispatchAsync dispatcher) {
		super(eventBus, view, proxy);

		this.dispatcher = dispatcher;
		this.eventBus = eventBus;
	}

	@Override
	protected void onReset() {
		super.onReset();

		dispatcher.execute(new LoadUsersAction(),
				new AsyncCallback<LoadUsersResult>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(LoadUsersResult result) {
						users = result.getUsers();
						UsersLoadedEvent event = new UsersLoadedEvent(users);
						eventBus.fireEvent(event);
					}
				});
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent,
				this);
	}
}
