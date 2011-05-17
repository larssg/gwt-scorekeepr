package dk.scorekeeper.shared.action;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

import dk.scorekeeper.shared.domain.User;

public class SaveUserAction extends UnsecuredActionImpl<SaveUserResult> {
	private User user;
	private String password;

	@SuppressWarnings("unused")
	private SaveUserAction() {
	}

	public SaveUserAction(User user, String password) {
		this.user = user;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public User getUser() {
		return user;
	}
}
