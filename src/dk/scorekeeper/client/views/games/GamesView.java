package dk.scorekeeper.client.views.games;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
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

		Request<Void> createReq = request.persist().using(game);
		createReq.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				clear();
				saveButton.setEnabled(true);
			}
		});
	}
}
