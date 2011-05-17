package dk.scorekeeper.server.rpc;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import dk.scorekeeper.server.service.GameDao;
import dk.scorekeeper.shared.action.SaveGameAction;
import dk.scorekeeper.shared.action.SaveGameResult;
import dk.scorekeeper.shared.domain.Game;

public class SaveGameHandler implements ActionHandler<SaveGameAction, SaveGameResult> {

	@Override
	public SaveGameResult execute(SaveGameAction arg0, ExecutionContext arg1)
	throws ActionException {
		GameDao dao = new GameDao();

		Game game = arg0.getGame();

		dao.put(game);

		return new SaveGameResult(game);
	}

	@Override
	public Class<SaveGameAction> getActionType() {
		return SaveGameAction.class;
	}

	@Override
	public void undo(SaveGameAction arg0, SaveGameResult arg1,
			ExecutionContext arg2) throws ActionException {
	}
}
