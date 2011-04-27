package dk.scorekeeper.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Resources extends ClientBundle {
	public static final Resources INSTANCE = GWT.create(Resources.class);

	@Source("960.css")
	public GridCssResource grid();

	@Source("reset.css")
	public CssResource reset();

	@Source("screen.css")
	public CssResource screen();

	@Source("text.css")
	public CssResource text();
}
