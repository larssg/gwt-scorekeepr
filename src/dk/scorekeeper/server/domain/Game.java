package dk.scorekeeper.server.domain;

import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

@Unindexed
public class Game extends DatastoreObject {
	@Indexed
	private String name;

	public Game() {
	}

	public Game(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
