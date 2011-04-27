package dk.scorekeeper.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.LockInteractionEvent;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * This is the top-level presenter of the hierarchy. Other presenters reveal
 * themselves within this presenter.
 */
public class MainPagePresenter extends Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy> {
	@ProxyStandard
	public interface MyProxy extends Proxy<MainPagePresenter> {
	}

	public interface MyView extends View {
		void showLoading(boolean visible);
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@ProxyEvent
	public void onLockInteraction(LockInteractionEvent event) {
		getView().showLoading(event.shouldLock());
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}
}
