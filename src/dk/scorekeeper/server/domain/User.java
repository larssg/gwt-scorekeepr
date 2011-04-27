package dk.scorekeeper.server.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import dk.scorekeeper.server.BCrypt;

@Entity
public class User {
	public static final EntityManager entityManager() {
		return EMF.get().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	public static List<User> findAllUsers() {
		EntityManager em = entityManager();
		try {
			List<User> users = em.createQuery("select u from User u").getResultList();
			users.size();
			return users;
		} finally {
			em.close();
		}
	}

	public static User findUser(Long id) {
		if (id == null) {
			return null;
		}
		EntityManager em = entityManager();
		try {
			User user = em.find(User.class, id);
			return user;
		} finally {
			em.close();
		}
	}

	private String email;

	private String fullName;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;

	private transient String passwordHash;

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

	public Long getId() {
		return id;
	}

	/**
	 * Always returns an empty string.
	 */
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

	public Long getVersion() {
		return version;
	}

	public void persist() {
		EntityManager em = entityManager();
		try {
			em.persist(this);
		} finally {
			em.close();
		}
	}

	public void remove() {
		EntityManager em = entityManager();
		try {
			User attached = em.find(User.class, id);
			em.remove(attached);
		} finally {
			em.close();
		}
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

	/**
	 * Generates and sets the password hash.
	 * @param password
	 */
	public void setPassword(String password) {
		if (password == null || password == "") {
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

	public void setVersion(Long version) {
		this.version = version;
	}
}
