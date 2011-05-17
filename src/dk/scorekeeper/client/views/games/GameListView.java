package dk.scorekeeper.client.views.games;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import dk.scorekeeper.shared.domain.Game;

public class GameListView extends Composite {

	interface GameListViewUiBinder extends UiBinder<Widget, GameListView> {
	}

	private static GameListViewUiBinder uiBinder = GWT
	.create(GameListViewUiBinder.class);

	@UiField
	FlowPanel panel;

	private final CellTable<Game> table = new CellTable<Game>();

	private final ListDataProvider<Game> dataProvider = new ListDataProvider<Game>();

	public GameListView() {
		initWidget(uiBinder.createAndBindUi(this));
		initTable();
	}

	private void initTable() {
		TextColumn<Game> idColumn = new TextColumn<Game>() {
			@Override
			public String getValue(Game object) {
				return object.getId().toString();
			}
		};

		TextColumn<Game> nameColumn = new TextColumn<Game>() {
			@Override
			public String getValue(Game object) {
				return object.getName();
			}
		};

		nameColumn.setSortable(true);

		table.addColumn(idColumn, "ID");
		table.addColumn(nameColumn, "Name");

		dataProvider.addDataDisplay(table);

		table.getColumnSortList().push(nameColumn);

		table.setWidth("100%");

		panel.add(table);
	}

	public void setGames(List<Game> games) {
		List<Game> list = dataProvider.getList();
		list.clear();
		for (Game game : games) {
			list.add(game);
		}
	}
}
