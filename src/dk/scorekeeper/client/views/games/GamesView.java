package dk.scorekeeper.client.views.games;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.ViewImpl;

import dk.scorekeeper.client.events.GameAddedEvent;
import dk.scorekeeper.client.presenter.GamesPresenter.MyView;
import dk.scorekeeper.shared.action.SaveGameAction;
import dk.scorekeeper.shared.action.SaveGameResult;
import dk.scorekeeper.shared.domain.Game;

public class GamesView extends ViewImpl implements MyView {

	interface GamesViewUiBinder extends UiBinder<Widget, GamesView> {
	}

	private static GamesViewUiBinder uiBinder = GWT
			.create(GamesViewUiBinder.class);

	@UiField
	TextBox name;

	@UiField
	Button saveButton;

	@UiField
	GameListView listView;

	private final Widget widget;

	private final DispatchAsync dispatcher;

	private final EventBus eventBus;

	@Inject
	public GamesView(EventBus eventBus, DispatchAsync dispatcher) {
		widget = uiBinder.createAndBindUi(this);
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	public void clear() {
		name.setText("");
	}

	@UiHandler("saveButton")
	void onSaveButtonClick(ClickEvent event) {
		Game game = new Game();
		game.setName(name.getText());

		eventBus.fireEvent(new GameAddedEvent(game));

		saveButton.setEnabled(false);
		dispatcher.execute(new SaveGameAction(game),
				new AsyncCallback<SaveGameResult>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(SaveGameResult result) {
						clear();
						saveButton.setEnabled(true);
					}
				});
	}

	@Override
	public void setGames(List<Game> games) {
		listView.setGames(games);
	}
}
