package Utils;

import javax.swing.JTextField;

public class DigerUtils {

	public static void alanTemizle(JTextField[] textler) {
		for (int i = 0; i < textler.length; i++) {
			textler[i].setText("");
		}
	}
	
}
