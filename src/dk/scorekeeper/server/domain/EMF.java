package dk.scorekeeper.server.domain;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {
	private static final EntityManagerFactory emfInstance =
		Persistence.createEntityManagerFactory("transactions-optional");

	public static EntityManagerFactory get() {
		return emfInstance;
	}

	private EMF() {}
}