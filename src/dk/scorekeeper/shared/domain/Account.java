package dk.scorekeeper.shared.domain;

import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;


@Unindexed
public class Account extends DatastoreObject {
	@Indexed
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
