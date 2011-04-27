/**
 * 
 */
package dk.scorekeeper.client.views.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import dk.scorekeeper.client.events.UserAddedEvent;
import dk.scorekeeper.shared.domain.proxy.UserProxy;
import dk.scorekeeper.shared.requestfactory.ScoreKeeperRequestFactory;
import dk.scorekeeper.shared.requestfactory.UserRequest;

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

	private final ScoreKeeperRequestFactory requestFactory;

	@Inject
	public CreateUserView(final EventBus eventBus, final ScoreKeeperRequestFactory requestFactory) {
		this.eventBus = eventBus;
		this.requestFactory = requestFactory;

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
			UserRequest request = requestFactory.userRequest();
			UserProxy user = request.create(UserProxy.class);

			user.setUserName(userName.getText());
			user.setFullName(fullName.getText());
			user.setEmail(email.getText());

			saveButton.setEnabled(false);

			eventBus.fireEvent(new UserAddedEvent(user));

			Request<Void> createReq = request.persist().using(user);
			createReq.fire(new Receiver<Void>() {
				@Override
				public void onSuccess(Void response) {
					clear();
					saveButton.setEnabled(true);
				}
			});
		}

	}
}
