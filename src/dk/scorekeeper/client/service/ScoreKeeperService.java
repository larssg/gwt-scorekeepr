package dk.scorekeeper.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dk.scorekeeper.shared.entities.Game;
import dk.scorekeeper.shared.entities.User;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("service")
public interface ScoreKeeperService extends RemoteService {
	Game createGame(Game game);

	User createUser(String userName, String password, String fullName,
			String email);

	ArrayList<User> listAllUsers();
}
