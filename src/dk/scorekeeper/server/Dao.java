package dk.scorekeeper.server;

import java.util.ArrayList;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;

import dk.scorekeeper.shared.entities.Game;
import dk.scorekeeper.shared.entities.User;

public class Dao extends DAOBase {
	static {
		ObjectifyService.register(User.class);
		ObjectifyService.register(Game.class);
	}

	public ArrayList<User> listAllUsers() {
		ArrayList<User> users = new ArrayList<User>();

		Iterable<User> q = ofy().query(User.class);
		for (User user : q) {
			users.add(user);
		}

		return users;
	}

	public Game saveGame(Game game) {
		ofy().put(game);
		return game;
	}

	public User saveUser(User user) {
		ofy().put(user);
		return user;
	}
}
