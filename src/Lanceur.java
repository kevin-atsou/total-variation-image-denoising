import java.util.Random;

import javax.swing.UIManager;

/**
 * Classe permettant de lancer l'application
 * 
 * @author kokou
 *
 */
public class Lanceur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			DenoisingIHM ihm = new DenoisingIHM();
			ihm.setVisible(true);
		} catch (Exception e) {
			System.err.println("Look and feel non effectué");
		}

	}

}
