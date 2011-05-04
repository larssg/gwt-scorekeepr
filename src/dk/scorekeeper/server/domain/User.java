package dk.scorekeeper.server.domain;

import javax.validation.constraints.NotNull;

import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

import dk.scorekeeper.server.BCrypt;

@Unindexed
public class User extends DatastoreObject {
	@Indexed
	private String email;

	private String fullName;

	private String passwordHash;

	@Indexed
	private String userName;

	public User() {
	}

	public User(String userName, String fullName, String email) {
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
	}

	@NotNull
	public String getEmail() {
		return email;
	}

	@NotNull
	public String getFullName() {
		return fullName;
	}

	public String getPassword() {
		return null;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	@NotNull
	public String getUserName() {
		return userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setPassword(String password) {
		if (password == null || password.equals("")) {
			return;
		}

		String salt = BCrypt.gensalt();
		String hash = BCrypt.hashpw(password, salt);
		setPasswordHash(hash);
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
