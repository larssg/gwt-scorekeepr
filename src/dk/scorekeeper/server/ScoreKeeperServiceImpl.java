package dk.scorekeeper.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dk.scorekeeper.client.service.ScoreKeeperService;
import dk.scorekeeper.shared.entities.Game;
import dk.scorekeeper.shared.entities.User;
import dk.scorekeepr.server.BCrypt;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ScoreKeeperServiceImpl extends RemoteServiceServlet implements
		ScoreKeeperService {

	@Override
	public Game createGame(Game game) {
		game.setId(null);

		Dao dao = new Dao();
		return dao.saveGame(game);
	}

	@Override
	public ArrayList<User> listAllUsers() {
		Dao dao = new Dao();
		return dao.listAllUsers();
	}

	@Override
	public User createUser(String userName, String password, String fullName,
			String email) {
		String salt = BCrypt.gensalt();

		User user = new User(userName, fullName, email);
		user.setPasswordHash(BCrypt.hashpw(password, salt));

		Dao dao = new Dao();
		return dao.saveUser(user);
	}
}
