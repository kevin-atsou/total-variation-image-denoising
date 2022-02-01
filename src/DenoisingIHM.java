import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DenoisingIHM extends JFrame {

	private JPanel contentPane;
	public static JTextField fichierTextField;
	private final Action action = new SwingAction();
	public static JComboBox<String> filtreComboBox;
	public static JComboBox<String> denoiseComboBox_1;
	public static JSpinner regSpinner;
	public static JSpinner iteSpinner_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DenoisingIHM frame = new DenoisingIHM();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DenoisingIHM() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 452);
		this.setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("fichier");
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		filtreComboBox = new JComboBox();
		filtreComboBox.setBounds(207, 65, 157, 34);
		contentPane.add(filtreComboBox);

		denoiseComboBox_1 = new JComboBox();
		denoiseComboBox_1.setBounds(207, 126, 157, 35);
		contentPane.add(denoiseComboBox_1);

		JButton btnOk = new JButton("OK");

		btnOk.setBounds(84, 304, 97, 42);
		contentPane.add(btnOk);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnAnnuler.setBounds(207, 304, 157, 42);
		contentPane.add(btnAnnuler);

		JLabel lblParamtrage = new JLabel("Param\u00E9trage");
		lblParamtrage.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblParamtrage.setBounds(84, 191, 100, 16);
		contentPane.add(lblParamtrage);

		JLabel lblRegularisation = new JLabel("Regularisation:");
		lblRegularisation.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblRegularisation.setBounds(207, 191, 100, 16);
		contentPane.add(lblRegularisation);

		regSpinner = new JSpinner();
		regSpinner.setEnabled(false);
		regSpinner.setBounds(303, 188, 61, 22);
		contentPane.add(regSpinner);

		JLabel lblItrations = new JLabel("it\u00E9rations:");
		lblItrations.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblItrations.setBounds(207, 247, 76, 16);
		contentPane.add(lblItrations);

		iteSpinner_1 = new JSpinner();
		iteSpinner_1.setEnabled(false);
		iteSpinner_1.setBounds(303, 244, 61, 22);
		contentPane.add(iteSpinner_1);

		JPanel chooserPane = new JPanel();
		chooserPane.setBackground(new Color(230, 230, 250));
		chooserPane.setBounds(0, 0, 432, 52);
		contentPane.add(chooserPane);
		chooserPane.setLayout(null);

		fichierTextField = new JTextField();
		fichierTextField.setBounds(118, 5, 302, 34);
		chooserPane.add(fichierTextField);
		fichierTextField.setColumns(10);

		JButton btnFichier = new JButton("fichier");
		btnFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChoixFichier ch = new ChoixFichier();
				ch.setLocationRelativeTo(null);
				ch.setVisible(true);
			}
		});
		btnFichier.setBounds(12, 4, 97, 35);
		chooserPane.add(btnFichier);

		filtreComboBox.setEnabled(false);
		denoiseComboBox_1.setEnabled(false);

		JLabel lblFiltres = new JLabel("filtres: ");
		lblFiltres.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblFiltres.setBounds(56, 65, 63, 16);
		contentPane.add(lblFiltres);

		JLabel lblDbruitage = new JLabel("d\u00E9bruitage:");
		lblDbruitage.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblDbruitage.setBounds(56, 126, 97, 16);

		filtreComboBox.addItem(" ");
		filtreComboBox.addItem("dérivatif");
		denoiseComboBox_1.addItem(" ");
		denoiseComboBox_1.addItem("FGP");

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reg = (int) regSpinner.getValue();
				int ite = (int) iteSpinner_1.getValue();

				if (filtreComboBox.getSelectedItem().equals(" ") && denoiseComboBox_1.getSelectedItem().equals(" ")) {
					JOptionPane.showMessageDialog(null, "choisissez une option ", "ERREUR", JOptionPane.ERROR_MESSAGE);
				}
				if (filtreComboBox.getSelectedItem().equals("dérivatif")
						&& denoiseComboBox_1.getSelectedItem().equals(" ")) {
					int numero = 0;
					String fichier = fichierTextField.getText().trim();
					String tabname = fichier.replaceAll(".pgm", "");
					Image image_tab1 = new Image();
					image_tab1 = Image.lectureFichier(fichier);
					// afficherImage(image_tab);
					image_tab1.convolution(image_tab1, numero);
					// afficherImage(image_tab);
					System.out.println("le nom du fiche: " + fichier);
					image_tab1.ecritureFichier(tabname + "_new_filter" + ".pgm");
					JOptionPane.showMessageDialog(null, "filtrage réussit", "réussit", JOptionPane.INFORMATION_MESSAGE);

				}
				if (filtreComboBox.getSelectedItem().equals(" ") && denoiseComboBox_1.getSelectedItem().equals("FGP")) {
					if (reg <= 0 || ite <= 0) {
						JOptionPane.showMessageDialog(null, "mauvaise valeur de régularisation ou d'itération ",
								"ERREUR", JOptionPane.ERROR_MESSAGE);
					} else {
						int numero = 0;
						String fichier = fichierTextField.getText().trim();
						Image image_tab = new Image();
						image_tab = Image.lectureFichier(fichier);
						String tabname = fichier.replaceAll(".pgm", "");
//						double[][] y = bruitUniform(image_tab.getImage());
//						image_tab.setImage(y);
//						image_tab.ecritureFichier(tabname + "_new_noisy" + ".pgm");

						double[][] x = Denoising.FGP(image_tab.getImage(), reg, ite);
						image_tab.setImage(x);
						image_tab.ecritureFichier(tabname + "_new_denoise" + ".pgm");
						JOptionPane.showMessageDialog(null, "débruitage réussit", "réussit",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				if (filtreComboBox.getSelectedItem().equals("dérivatif")
						&& denoiseComboBox_1.getSelectedItem().equals("FGP")) {
					if (reg <= 0 || ite <= 0) {
						JOptionPane.showMessageDialog(null, "mauvaise valeur de régularisation ou d'itération ",
								"ERREUR", JOptionPane.ERROR_MESSAGE);
					} else {
						int numero = 0;
						String fichier = fichierTextField.getText().trim();
						String tabname = fichier.replaceAll(".pgm", "");
						Image image_tab1 = new Image();
						image_tab1 = Image.lectureFichier(fichier);
						// afficherImage(image_tab);
						image_tab1.convolution(image_tab1, numero);
						// afficherImage(image_tab);
						image_tab1.ecritureFichier(tabname + "_new_filter" + ".pgm");

						Image image_tab = new Image();
						image_tab = Image.lectureFichier(fichier);
						double[][] y = bruitUniform(image_tab.getImage());
						image_tab.setImage(y);
						image_tab.ecritureFichier(tabname + "_new_noisy" + ".pgm");

						double[][] x = Denoising.FGP(image_tab.getImage(), ite, ite);
						image_tab.setImage(x);
						image_tab.ecritureFichier(tabname + "_new_denoise" + ".pgm");
						JOptionPane.showMessageDialog(null, "filtrage et débruitage réussit", "réussit",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});
		contentPane.add(lblDbruitage);
	}

	private static double[][] bruitGaussien(double[][] img) {
		int m = img.length;
		int n = img[0].length;
		Random r = new Random();
		double tab[] = new double[n];
		double imTab[][] = new double[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				imTab[i][j] = img[i][j] + r.nextGaussian() * 25.00;
			}
		}

		return imTab;
	}

	private static double[][] bruitUniform(double[][] img) {
		int m = img.length;
		int n = img[0].length;
		Random r = new Random();
		double tab[] = new double[n];
		double imTab[][] = new double[m][n];
		// for(int i=0;i<n;i++){
		// tab[i] = r.nextDouble()*255.0 ;
		// }

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				imTab[i][j] = img[i][j] + r.nextDouble() * 50;
			}
		}

		return imTab;
	}

	public DenoisingIHM(String path, boolean comboFilter, boolean comboDenoise, boolean spinnerReg,
			boolean spinnerIte) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 434);
		this.setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("fichier");
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox filtreComboBox = new JComboBox();
		filtreComboBox.setBounds(207, 53, 157, 22);
		contentPane.add(filtreComboBox);

		JComboBox denoiseComboBox_1 = new JComboBox();
		denoiseComboBox_1.setBounds(207, 99, 157, 22);
		contentPane.add(denoiseComboBox_1);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(84, 274, 97, 25);
		contentPane.add(btnOk);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(207, 274, 157, 25);
		contentPane.add(btnAnnuler);

		JLabel lblParamtrage = new JLabel("Param\u00E9trage");
		lblParamtrage.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblParamtrage.setBounds(84, 166, 100, 16);
		contentPane.add(lblParamtrage);

		JLabel lblRegularisation = new JLabel("Regularisation:");
		lblRegularisation.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblRegularisation.setBounds(207, 166, 100, 16);
		contentPane.add(lblRegularisation);

		JSpinner regSpinner = new JSpinner();
		regSpinner.setEnabled(spinnerReg);
		regSpinner.setBounds(303, 163, 30, 22);
		contentPane.add(regSpinner);

		JLabel lblItrations = new JLabel("it\u00E9rations:");
		lblItrations.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblItrations.setBounds(207, 217, 76, 16);
		contentPane.add(lblItrations);

		JSpinner iteSpinner_1 = new JSpinner();
		iteSpinner_1.setEnabled(spinnerIte);
		iteSpinner_1.setBounds(303, 214, 30, 22);
		contentPane.add(iteSpinner_1);

		JPanel chooserPane = new JPanel();
		chooserPane.setBackground(new Color(230, 230, 250));
		chooserPane.setBounds(0, 0, 432, 40);
		contentPane.add(chooserPane);
		chooserPane.setLayout(null);

		fichierTextField = new JTextField();
		fichierTextField.setBounds(118, 5, 302, 22);
		chooserPane.add(fichierTextField);
		fichierTextField.setColumns(10);

		JButton btnFichier = new JButton("fichier");
		btnFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChoixFichier ch = new ChoixFichier();
				ch.setLocationRelativeTo(null);
				ch.setVisible(true);
			}
		});
		btnFichier.setBounds(12, 4, 97, 25);
		chooserPane.add(btnFichier);

		filtreComboBox.setEnabled(comboFilter);
		denoiseComboBox_1.setEnabled(comboDenoise);

		JLabel lblFiltres = new JLabel("filtres: ");
		lblFiltres.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblFiltres.setBounds(56, 56, 63, 16);
		contentPane.add(lblFiltres);

		JLabel lblDbruitage = new JLabel("d\u00E9bruitage:");
		lblDbruitage.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblDbruitage.setBounds(52, 102, 97, 16);
		contentPane.add(lblDbruitage);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
