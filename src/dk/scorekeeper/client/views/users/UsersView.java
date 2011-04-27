package dk.scorekeeper.client.views.users;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import dk.scorekeeper.client.events.UsersLoadedEvent;
import dk.scorekeeper.client.presenter.UsersPresenter.MyView;
import dk.scorekeeper.shared.domain.proxy.UserProxy;
import dk.scorekeeper.shared.requestfactory.ScoreKeeperRequestFactory;

public class UsersView extends ViewImpl implements MyView {

	interface UsersViewUiBinder extends UiBinder<Widget, UsersView> {
	}

	private static UsersViewUiBinder uiBinder = GWT.create(UsersViewUiBinder.class);

	private final EventBus eventBus;

	private final Widget widget;

	private final ScoreKeeperRequestFactory requestFactory;

	@Inject
	public UsersView(final EventBus eventBus, ScoreKeeperRequestFactory requestFactory) {
		this.eventBus = eventBus;
		this.requestFactory = requestFactory;

		widget = uiBinder.createAndBindUi(this);

		requestFactory.userRequest().findAllUsers().fire(new Receiver<List<UserProxy>>() {
			@Override
			public void onSuccess(List<UserProxy> users) {
				UsersLoadedEvent event = new UsersLoadedEvent(users);
				eventBus.fireEvent(event);
			}
		});
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiFactory
	CreateUserView makeCreateUserView() {
		return new CreateUserView(eventBus, requestFactory);
	}

	@UiFactory
	UserListView makeUserListView() {
		return new UserListView(eventBus);
	}
}
