package dk.scorekeeper.server.rpc;

import java.util.List;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import dk.scorekeeper.server.service.UserDao;
import dk.scorekeeper.shared.action.LoadUsersAction;
import dk.scorekeeper.shared.action.LoadUsersResult;
import dk.scorekeeper.shared.domain.User;

public class LoadUsersHandler implements ActionHandler<LoadUsersAction, LoadUsersResult> {

	@Override
	public LoadUsersResult execute(LoadUsersAction arg0, ExecutionContext arg1)
	throws ActionException {
		UserDao userDao = new UserDao();
		List<User> users = userDao.listAll();
		return new LoadUsersResult(users);
	}

	@Override
	public Class<LoadUsersAction> getActionType() {
		return LoadUsersAction.class;
	}

	@Override
	public void undo(LoadUsersAction arg0, LoadUsersResult arg1, ExecutionContext arg2)
	throws ActionException {
	}

}
