package dk.scorekeeper.client;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;

public class KeyboardUtil {

	public static boolean enterKeyPressed(KeyPressEvent event) {
		int charCode = event.getUnicodeCharCode();
		if (charCode == 0) {
			// it's probably Firefox
			int keyCode = event.getNativeEvent().getKeyCode();
			if (keyCode == KeyCodes.KEY_ENTER) {
				return true;
			}
		} else if (charCode == 13) {
			return true;
		}

		return false;
	}

}
