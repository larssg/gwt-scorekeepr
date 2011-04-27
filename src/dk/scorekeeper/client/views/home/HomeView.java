package dk.scorekeeper.client.views.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import dk.scorekeeper.client.presenter.HomePresenter.MyView;

public class HomeView extends ViewImpl implements MyView {

	interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
	}

	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);

	private final Widget widget;

	public HomeView() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

}
