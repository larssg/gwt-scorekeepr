package dk.scorekeeper.shared.action;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;

import dk.scorekeeper.shared.domain.User;

public class LoadUsersResult implements Result {
	private List<User> users;

	@SuppressWarnings("unused")
	private LoadUsersResult() {
	}

	public LoadUsersResult(final List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}
}
