package dk.scorekeeper.client.views.games;

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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import dk.scorekeeper.client.Factory;
import dk.scorekeeper.shared.entities.Game;

public class CreateGameView extends Composite {

	interface CreateGameViewUiBinder extends UiBinder<Widget, CreateGameView> {
	}

	private static CreateGameViewUiBinder uiBinder = GWT
			.create(CreateGameViewUiBinder.class);

	@UiField
	TextBox name;

	@UiField
	Button saveButton;

	public CreateGameView(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void clear() {
		name.setText("");
	}

	@UiHandler("saveButton")
	void onSaveButtonClick(ClickEvent event) {
		Game game = new Game(name.getText());

		saveButton.setEnabled(false);

		Factory.getServiceInstance().createGame(game,
				new AsyncCallback<Game>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(Factory.SERVER_ERROR);
						saveButton.setEnabled(true);
					}

					@Override
					public void onSuccess(Game result) {
						clear();
						saveButton.setEnabled(true);
					}
				});
	}
}
