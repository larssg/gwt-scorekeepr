package dk.scorekeeper.client.views.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.ViewImpl;

import dk.scorekeeper.client.presenter.UsersPresenter.MyView;

public class UsersView extends ViewImpl implements MyView {

	interface UsersViewUiBinder extends UiBinder<Widget, UsersView> {
	}

	private static UsersViewUiBinder uiBinder = GWT.create(UsersViewUiBinder.class);

	private final EventBus eventBus;

	private final Widget widget;

	private final DispatchAsync dispatcher;

	@Inject
	public UsersView(final EventBus eventBus, DispatchAsync dispatcher) {
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;

		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@UiFactory
	CreateUserView makeCreateUserView() {
		return new CreateUserView(eventBus, dispatcher);
	}

	@UiFactory
	UserListView makeUserListView() {
		return new UserListView(eventBus);
	}
}
