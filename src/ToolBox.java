
public class ToolBox {

	private static void afficherImage(Image image_tab) {
		double[][] tab = image_tab.getImage();
		for (int i = 0; i < image_tab.getHauteur(); i++) {
			for (int j = 0; j < image_tab.getLargeur(); j++) {
				System.out.print("" + tab[i][j]);
			}
			System.out.println("\n");
		}

	}

	// UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
	// UIManager.setLookAndFeel("com.jtattoo.plaf.graphite");
}
