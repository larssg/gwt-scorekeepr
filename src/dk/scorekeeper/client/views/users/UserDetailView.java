package dk.scorekeeper.client.views.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

import dk.scorekeeper.shared.entities.User;

public class UserDetailView extends Composite {

	interface UserDetailViewUiBinder extends UiBinder<Widget, UserDetailView> {
	}

	private static UserDetailViewUiBinder uiBinder = GWT
			.create(UserDetailViewUiBinder.class);

	@UiField
	InlineLabel emailLabel;

	@UiField
	InlineLabel fullNameLabel;

	@UiField
	InlineLabel userNameLabel;

	public UserDetailView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public UserDetailView(User user) {
		initWidget(uiBinder.createAndBindUi(this));
		setUser(user);
	}

	private void setUser(User user) {
		userNameLabel.setText(user.getUserName());
		fullNameLabel.setText(user.getFullName());
		emailLabel.setText(user.getEmail());
	}
}
