import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChoixFichier extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoixFichier frame = new ChoixFichier();
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
	public ChoixFichier() {
		MonFiltre mfi = new MonFiltre(new String[] { "pgm" }, "les fichiers image (*.pgm)");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				arg0.getWindow().dispose();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JFileChooser fileChooser = new JFileChooser(new File("."));

		fileChooser.addChoosableFileFilter(mfi);
		int retour = fileChooser.showOpenDialog(this);
		if (retour == JFileChooser.APPROVE_OPTION) {
			// un fichier a été choisi (sortie par OK)
			// nom du fichier choisi
			String path = fileChooser.getSelectedFile().getName();
			// chemin absolu du fichier choisi
			fileChooser.getSelectedFile().getAbsolutePath();

			DenoisingIHM.fichierTextField.setText(path);
			DenoisingIHM.filtreComboBox.setEnabled(true);
			DenoisingIHM.denoiseComboBox_1.setEnabled(true);
			DenoisingIHM.regSpinner.setEnabled(true);
			DenoisingIHM.iteSpinner_1.setEnabled(true);
			this.dispose();
		} else {
			this.dispose();
		}
		;
		contentPane.add(fileChooser, BorderLayout.CENTER);
	}

}
