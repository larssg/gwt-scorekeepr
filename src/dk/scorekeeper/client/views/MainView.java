package dk.scorekeeper.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Widget;

import dk.scorekeeper.client.views.games.CreateGameView;
import dk.scorekeeper.client.views.users.MainUserView;

public class MainView extends Composite {

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	private static MainViewUiBinder uiBinder = GWT
			.create(MainViewUiBinder.class);

	private final EventBus eventBus;

	@UiField
	InlineHyperlink gamesLink;

	@UiField
	FlowPanel mainPanel;

	@UiField
	InlineHyperlink usersLink;

	public MainView(final EventBus eventBus) {
		this.eventBus = eventBus;

		initWidget(uiBinder.createAndBindUi(this));

		revealUsers();
	}

	public void addWidget(Widget widget) {
		mainPanel.add(widget);
	}

	@UiHandler("gamesLink")
	void onGamesLinkClick(ClickEvent event) {
		revealGames();
	}

	@UiHandler("usersLink")
	void onUsersLinkClick(ClickEvent event) {
		revealUsers();
	}

	private void revealGames() {
		mainPanel.clear();
		CreateGameView gameView = new CreateGameView(eventBus);
		addWidget(gameView);
	}

	private void revealUsers() {
		mainPanel.clear();
		MainUserView userView = new MainUserView(eventBus);
		addWidget(userView);
	}
}
