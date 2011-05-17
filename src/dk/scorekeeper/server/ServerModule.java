package dk.scorekeeper.server;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

import dk.scorekeeper.server.rpc.LoadGamesHandler;
import dk.scorekeeper.server.rpc.LoadUsersHandler;
import dk.scorekeeper.server.rpc.SaveUserHandler;
import dk.scorekeeper.shared.action.LoadGamesAction;
import dk.scorekeeper.shared.action.LoadUsersAction;
import dk.scorekeeper.shared.action.SaveUserAction;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(LoadUsersAction.class, LoadUsersHandler.class);
		bindHandler(SaveUserAction.class, SaveUserHandler.class);
		bindHandler(LoadGamesAction.class, LoadGamesHandler.class);
	}

}
