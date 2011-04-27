package dk.scorekeeper.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dk.scorekeeper.shared.entities.Game;
import dk.scorekeeper.shared.entities.User;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ScoreKeeperServiceAsync {
	void createGame(Game game, AsyncCallback<Game> callback);

	void createUser(String userName, String password, String fullName,
			String email, AsyncCallback<User> callback);

	void listAllUsers(AsyncCallback<ArrayList<User>> callback);
}
