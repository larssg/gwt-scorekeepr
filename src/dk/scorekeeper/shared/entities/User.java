package dk.scorekeeper.shared.entities;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

@Unindexed
@Cached
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Indexed
	private String email;

	private String fullName;

	@Id
	private Long id;

	private transient String passwordHash;

	@Indexed
	private String userName;

	public User() {
	}

	public User(String userName, String fullName, String email) {
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		return fullName;
	}

	public Long getId() {
		return id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public String getUserName() {
		return userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
