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
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.gwtplatform.mvp.client.ViewImpl;

import dk.scorekeeper.client.presenter.GamesPresenter.MyView;
import dk.scorekeeper.shared.domain.proxy.GameProxy;
import dk.scorekeeper.shared.requestfactory.GameRequest;
import dk.scorekeeper.shared.requestfactory.ScoreKeeperRequestFactory;

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

	private final ScoreKeeperRequestFactory requestFactory;

	@Inject
	public GamesView(final ScoreKeeperRequestFactory requestFactory) {
		widget = uiBinder.createAndBindUi(this);
		this.requestFactory = requestFactory;
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
		GameRequest request = requestFactory.gameRequest();
		GameProxy game = request.create(GameProxy.class);

		game.setName(name.getText());

		saveButton.setEnabled(false);

		Request<GameProxy> createReq = request.saveAndReturn(game);
		createReq.fire(new Receiver<GameProxy>() {
			@Override
			public void onSuccess(GameProxy response) {
				clear();
				saveButton.setEnabled(true);
			}
		});
	}

	@Override
	public void setGames(List<GameProxy> games) {
		listView.setGames(games);
	}
}
