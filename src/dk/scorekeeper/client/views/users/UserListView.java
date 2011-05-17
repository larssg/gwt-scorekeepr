package dk.scorekeeper.client.views.users;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import dk.scorekeeper.client.events.UserAddedEvent;
import dk.scorekeeper.client.events.UserAddedEventHandler;
import dk.scorekeeper.client.events.UsersLoadedEvent;
import dk.scorekeeper.client.events.UsersLoadedEventHandler;
import dk.scorekeeper.shared.domain.User;

public class UserListView extends Composite {

	interface UserListViewUiBinder extends UiBinder<Widget, UserListView> {
	}

	private static UserListViewUiBinder uiBinder = GWT
	.create(UserListViewUiBinder.class);

	private final EventBus eventBus;

	@UiField
	FlowPanel userDetailList;

	public UserListView(EventBus eventBus) {
		this.eventBus = eventBus;

		initWidget(uiBinder.createAndBindUi(this));

		bindEvents();
	}

	protected void addUser(User user) {
		userDetailList.add(new UserDetailView(user));
	}

	private void bindEvents() {
		eventBus.addHandler(UsersLoadedEvent.TYPE,
				new UsersLoadedEventHandler() {
			@Override
			public void onEvent(UsersLoadedEvent event) {
				setUsers(event.getUsers());
			}
		});

		eventBus.addHandler(UserAddedEvent.TYPE, new UserAddedEventHandler() {
			@Override
			public void onEvent(UserAddedEvent event) {
				addUser(event.getUser());
			}
		});
	}

	public void setUsers(List<User> list) {
		userDetailList.clear();
		for (User user : list) {
			userDetailList.add(new UserDetailView(user));
		}
	}

}
