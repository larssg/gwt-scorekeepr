package dk.scorekeeper.shared.action;

import com.gwtplatform.dispatch.shared.Result;

import dk.scorekeeper.shared.domain.User;

public class SaveUserResult implements Result {
	private User user;

	@SuppressWarnings("unused")
	private SaveUserResult() {
	}

	public SaveUserResult(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
