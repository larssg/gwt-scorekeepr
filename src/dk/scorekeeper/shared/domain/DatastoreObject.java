package dk.scorekeeper.shared.domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.PrePersist;

@SuppressWarnings("serial")
public class DatastoreObject implements Serializable {
	@Id
	private Long id;

	private Integer version = 0;

	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@PrePersist
	void onPersist()
	{
		setVersion(getVersion() + 1);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
