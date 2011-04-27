/**
 * 
 */
package dk.scorekeeper.client.views.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import dk.scorekeeper.client.Factory;
import dk.scorekeeper.client.events.UserAddedEvent;
import dk.scorekeeper.shared.entities.User;

/**
 * @author larssg
 */
public class CreateUserView extends Composite {

	interface CreateUserViewUiBinder extends UiBinder<Widget, CreateUserView> {
	}

	private static CreateUserViewUiBinder uiBinder = GWT
			.create(CreateUserViewUiBinder.class);

	@UiField
	TextBox email;

	private final EventBus eventBus;

	@UiField
	TextBox fullName;

	@UiField
	Button saveButton;

	@UiField
	TextBox userName;

	@UiField
	PasswordTextBox password;

	@UiField
	PasswordTextBox passwordConfirmation;

	public CreateUserView(EventBus eventBus) {
		this.eventBus = eventBus;

		initWidget(uiBinder.createAndBindUi(this));
	}

	public void clear() {
		userName.setText("");
		password.setText("");
		passwordConfirmation.setText("");
		email.setText("");
		fullName.setText("");
	}

	@UiHandler("saveButton")
	void onSaveButtonClick(ClickEvent event) {
		if (password.getText().equals(passwordConfirmation.getText())) {
			User user = new User(userName.getText(), fullName.getText(),
					email.getText());

			saveButton.setEnabled(false);

			eventBus.fireEvent(new UserAddedEvent(user));

			Factory.getServiceInstance().createUser(userName.getText(),
					password.getText(), fullName.getText(), email.getText(),
					new AsyncCallback<User>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert(Factory.SERVER_ERROR);
							saveButton.setEnabled(true);
						}

						@Override
						public void onSuccess(User result) {
							clear();
							saveButton.setEnabled(true);
						}
					});
		}

	}
}
