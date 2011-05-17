package dk.scorekeeper.client.views.games;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.DispatchAsync;
import com.gwtplatform.mvp.client.ViewImpl;

import dk.scorekeeper.client.presenter.GamesPresenter.MyView;
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

	@Inject
	public GamesView(DispatchAsync dispatcher) {
		widget = uiBinder.createAndBindUi(this);
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
		//		GameProxy game = request.create(GameProxy.class);

		//		game.setName(name.getText());
		//
		//		saveButton.setEnabled(false);
		//
		//		Request<GameProxy> createReq = request.saveAndReturn(game);
		//		createReq.fire(new Receiver<GameProxy>() {
		//			@Override
		//			public void onSuccess(GameProxy response) {
		//				clear();
		//				saveButton.setEnabled(true);
		//			}
		//		});
	}

	@Override
	public void setGames(List<Game> games) {
		listView.setGames(games);
	}
}
