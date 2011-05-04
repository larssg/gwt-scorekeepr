package dk.scorekeeper.server.domain;

import javax.persistence.Id;
import javax.persistence.PrePersist;

public class DatastoreObject {
	@Id
	private Long id;

	private Integer version = 0;

	@PrePersist
	void onPersist()
	{
		setVersion(getVersion() + 1);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}
}
