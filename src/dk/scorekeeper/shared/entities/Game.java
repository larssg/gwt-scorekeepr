package dk.scorekeeper.shared.entities;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Unindexed;

@Unindexed
@Cached
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

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

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
