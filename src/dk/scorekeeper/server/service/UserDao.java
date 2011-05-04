package dk.scorekeeper.server.service;

import dk.scorekeeper.server.domain.User;

public class UserDao extends ObjectifyDao<User> {
	public User saveAndReturn(User user) {
		put(user);
		return user;
	}
}
