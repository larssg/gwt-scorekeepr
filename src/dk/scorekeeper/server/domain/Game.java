package dk.scorekeeper.server.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final EntityManager entityManager() {
		return EMF.get().createEntityManager();
	}

	public static List<Game> findAllGames() {
		EntityManager em = entityManager();
		try {
			List<Game> games = em.createQuery("select g from Game g").getResultList();
			games.size();
			return games;
		} finally {
			em.close();
		}
	}

	public static Game findGame(Long id) {
		if (id == null) {
			return null;
		}
		EntityManager em = entityManager();
		try {
			Game game = em.find(Game.class, id);
			return game;
		} finally {
			em.close();
		}
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Integer version;

	private String name;

	public Game() {
	}

	public Game(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getVersion() {
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
			Game attached = em.find(Game.class, id);
			em.remove(attached);
		} finally {
			em.close();
		}
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
