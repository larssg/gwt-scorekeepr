package dk.scorekeeper.server.rpc;

import java.util.List;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import dk.scorekeeper.server.service.GameDao;
import dk.scorekeeper.shared.action.LoadGamesAction;
import dk.scorekeeper.shared.action.LoadGamesResult;
import dk.scorekeeper.shared.domain.Game;

public class LoadGamesHandler implements ActionHandler<LoadGamesAction, LoadGamesResult> {

	@Override
	public LoadGamesResult execute(LoadGamesAction arg0, ExecutionContext arg1)
	throws ActionException {
		GameDao gameDao = new GameDao();
		List<Game> games = gameDao.listAll();
		return new LoadGamesResult(games);
	}

	@Override
	public Class<LoadGamesAction> getActionType() {
		return LoadGamesAction.class;
	}

	@Override
	public void undo(LoadGamesAction arg0, LoadGamesResult arg1,
			ExecutionContext arg2) throws ActionException {
	}

}
