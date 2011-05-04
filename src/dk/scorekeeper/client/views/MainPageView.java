package dk.scorekeeper.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import dk.scorekeeper.client.presenter.MainPagePresenter;
import dk.scorekeeper.client.presenter.MainPagePresenter.MyView;

public class MainPageView extends ViewImpl implements MyView {

	interface MainPageViewUiBinder extends UiBinder<Widget, MainPageView> {
	}

	private static MainPageViewUiBinder uiBinder = GWT.create(MainPageViewUiBinder.class);

	@UiField
	HTMLPanel notification;

	@UiField
	InlineHyperlink gamesLink;

	@UiField
	FlowPanel mainContentPanel;

	@UiField
	InlineHyperlink usersLink;

	public final Widget widget;

	public MainPageView() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == MainPagePresenter.TYPE_SetMainContent) {
			setMainContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setMainContent(Widget content) {
		mainContentPanel.clear();

		if (content != null) {
			mainContentPanel.add(content);
		}
	}

	@Override
	public void showLoading(boolean visible) {
		notification.setVisible(visible);
	}
}
