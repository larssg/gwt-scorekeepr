package dk.scorekeeper.client;

import com.google.gwt.core.client.GWT;

import dk.scorekeeper.client.service.ScoreKeeperService;
import dk.scorekeeper.client.service.ScoreKeeperServiceAsync;

public class Factory {
	public static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private static ScoreKeeperServiceAsync SERVICE_INSTANCE = GWT
			.create(ScoreKeeperService.class);

	public static ScoreKeeperServiceAsync getServiceInstance() {
		return SERVICE_INSTANCE;
	}
}
