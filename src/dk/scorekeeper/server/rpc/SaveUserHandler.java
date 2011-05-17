package dk.scorekeeper.server.rpc;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import dk.scorekeeper.server.BCrypt;
import dk.scorekeeper.server.service.UserDao;
import dk.scorekeeper.shared.action.SaveUserAction;
import dk.scorekeeper.shared.action.SaveUserResult;
import dk.scorekeeper.shared.domain.User;

public class SaveUserHandler implements ActionHandler<SaveUserAction, SaveUserResult> {
	@Override
	public SaveUserResult execute(SaveUserAction arg0, ExecutionContext arg1)
	throws ActionException {
		UserDao dao = new UserDao();

		User user = arg0.getUser();

		String password = arg0.getPassword();

		if (password != null && !password.equals("")) {
			String salt = BCrypt.gensalt();
			String hash = BCrypt.hashpw(password, salt);
			user.setPasswordHash(hash);
		}

		dao.put(user);

		return new SaveUserResult(user);
	}

	@Override
	public Class<SaveUserAction> getActionType() {
		return SaveUserAction.class;
	}

	@Override
	public void undo(SaveUserAction arg0, SaveUserResult arg1, ExecutionContext arg2)
	throws ActionException {
	}
}
