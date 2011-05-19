package dk.scorekeeper.client.views.games;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.ViewImpl;

import dk.scorekeeper.client.KeyboardUtil;
import dk.scorekeeper.client.event.GameAddedEvent;
import dk.scorekeeper.client.presenter.GamesPresenter.MyView;
import dk.scorekeeper.shared.action.SaveGameAction;
import dk.scorekeeper.shared.action.SaveGameResult;
import dk.scorekeeper.shared.domain.Game;

public class GamesView extends ViewImpl implements MyView {

	interface GamesViewUiBinder extends UiBinder<Widget, GamesView> {
	}

	private static GamesViewUiBinder uiBinder = GWT.create(GamesViewUiBinder.class);

	@UiField
	TextBox name;

	@UiField
	Button saveButton;

	@UiField
	GameListView listView;

	private final Widget widget;

	private final DispatchAsync dispatcher;

	private final EventBus eventBus;

	private final List<FocusWidget> widgets = new ArrayList<FocusWidget>();

	@Inject
	public GamesView(EventBus eventBus, DispatchAsync dispatcher) {
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;

		widget = uiBinder.createAndBindUi(this);

		widgets.add(name);

		onBind();
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	public void clear() {
		name.setText("");
	}

	private void onBind() {
		for (FocusWidget widget : widgets) {
			widget.addKeyPressHandler(new KeyPressHandler() {
				@Override
				public void onKeyPress(KeyPressEvent event) {
					if (KeyboardUtil.enterKeyPressed(event)) {
						onSave();
					}
				}
			});
		}
	}

	private void onSave() {
		setEnabled(false);

		final Game game = new Game();
		game.setName(name.getText());

		dispatcher.execute(new SaveGameAction(game), new AsyncCallback<SaveGameResult>() {
			@Override
			public void onFailure(Throwable caught) {
				setEnabled(true);
			}

			@Override
			public void onSuccess(SaveGameResult result) {
				eventBus.fireEvent(new GameAddedEvent(result.getGame()));
				clear();
				setEnabled(true);
			}
		});
	}

	@UiHandler("saveButton")
	void onSaveButtonClick(ClickEvent event) {
		onSave();
	}

	public void setEnabled(boolean enabled) {
		for (FocusWidget widget : widgets) {
			widget.setEnabled(enabled);
		}

		saveButton.setEnabled(enabled);
	}

	@Override
	public void setGames(List<Game> games) {
		listView.setGames(games);
	}
}
