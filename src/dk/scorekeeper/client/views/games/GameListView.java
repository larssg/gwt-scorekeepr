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

import dk.scorekeeper.shared.domain.proxy.GameProxy;

public class GameListView extends Composite {

	interface GameListViewUiBinder extends UiBinder<Widget, GameListView> {
	}

	private static GameListViewUiBinder uiBinder = GWT
	.create(GameListViewUiBinder.class);

	@UiField
	FlowPanel panel;

	private final CellTable<GameProxy> table = new CellTable<GameProxy>();

	private final ListDataProvider<GameProxy> dataProvider = new ListDataProvider<GameProxy>();

	public GameListView() {
		initWidget(uiBinder.createAndBindUi(this));
		initTable();
	}

	private void initTable() {
		TextColumn<GameProxy> idColumn = new TextColumn<GameProxy>() {
			@Override
			public String getValue(GameProxy object) {
				return object.getId().toString();
			}
		};

		TextColumn<GameProxy> nameColumn = new TextColumn<GameProxy>() {
			@Override
			public String getValue(GameProxy object) {
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

	public void setGames(List<GameProxy> games) {
		List<GameProxy> list = dataProvider.getList();
		list.clear();
		for (GameProxy game : games) {
			list.add(game);
		}
	}
}
