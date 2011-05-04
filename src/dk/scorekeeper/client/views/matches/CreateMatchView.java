package dk.scorekeeper.client.views.matches;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CreateMatchView extends Composite {

	private static CreateMatchViewUiBinder uiBinder = GWT
			.create(CreateMatchViewUiBinder.class);

	interface CreateMatchViewUiBinder extends UiBinder<Widget, CreateMatchView> {
	}

	public CreateMatchView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
