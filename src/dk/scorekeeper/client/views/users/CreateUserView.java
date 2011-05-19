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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;

import dk.scorekeeper.client.event.UserAddedEvent;
import dk.scorekeeper.shared.action.SaveUserAction;
import dk.scorekeeper.shared.action.SaveUserResult;
import dk.scorekeeper.shared.domain.User;

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

	private final DispatchAsync dispatcher;

	@Inject
	public CreateUserView(final EventBus eventBus, final DispatchAsync dispatcher) {
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;

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
			User user = new User();

			user.setUserName(userName.getText());
			user.setFullName(fullName.getText());
			user.setEmail(email.getText());

			saveButton.setEnabled(false);

			eventBus.fireEvent(new UserAddedEvent(user));

			SaveUserAction action = new SaveUserAction(user, password.getText());
			dispatcher.execute(action, new AsyncCallback<SaveUserResult>() {
				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(SaveUserResult result) {
					clear();
					saveButton.setEnabled(true);
				}
			});
		}

	}
}
