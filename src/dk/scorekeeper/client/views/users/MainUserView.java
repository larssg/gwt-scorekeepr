package dk.scorekeeper.client.views.users;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import dk.scorekeeper.client.Factory;
import dk.scorekeeper.client.events.UsersLoadedEvent;
import dk.scorekeeper.shared.entities.User;

public class MainUserView extends Composite {

	interface MainUserViewUiBinder extends UiBinder<Widget, MainUserView> {
	}

	private static MainUserViewUiBinder uiBinder = GWT
			.create(MainUserViewUiBinder.class);

	private final EventBus eventBus;

	public MainUserView(final EventBus eventBus) {
		this.eventBus = eventBus;

		initWidget(uiBinder.createAndBindUi(this));

		Factory.getServiceInstance().listAllUsers(
				new AsyncCallback<ArrayList<User>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(Factory.SERVER_ERROR);
					}

					@Override
					public void onSuccess(ArrayList<User> result) {
						UsersLoadedEvent event = new UsersLoadedEvent(result);
						eventBus.fireEvent(event);
					}
				});
	}

	@UiFactory
	CreateUserView makeCreateUserView() {
		return new CreateUserView(eventBus);
	}

	@UiFactory
	UserListView makeUserListView() {
		return new UserListView(eventBus);
	}
}
