import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Image {

	private double image[][];
	private int valmax;
	private int hauteur;
	private int largeur;

	public Image(double[][] image, int valmax, int hauteur, int largeur) {
		super();
		this.image = image;
		this.valmax = valmax;
		this.hauteur = hauteur;
		this.largeur = largeur;
	}

	public Image() {
		this(255, 128, 128); // valeur par défaut de l'image (valmax=255 ,
		// largeur =128 ,hauteur =128)
	}

	public Image(int valmax, int largeur, int hauteur) {

		this.valmax = valmax;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.image = new double[hauteur][largeur];// création de l'image et la
		// mettre a 0
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++)
				this.image[i][j] = 0;
		}
	}

	public double[][] getImage() {
		return image;
	}

	public void setImage(double[][] image) {
		this.image = image;
	}

	public int getValmax() {
		return valmax;
	}

	public void setValmax(int valmax) {
		this.valmax = valmax;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	static Image lectureFichier(String fichier) {
		System.out.println("fichier: " + fichier);
		BufferedReader fluxEntree = null;
		try {
			fluxEntree = new BufferedReader(new FileReader(fichier));
			Image img = null;
			fluxEntree.readLine();
			String[] ligneLue = fluxEntree.readLine().split(" ");
			int largeur = Integer.parseInt(ligneLue[0]);
			int hauteur = Integer.parseInt(ligneLue[1]);
			String ligneLu = fluxEntree.readLine();
			int valmax = Integer.parseInt(ligneLu);
			img = new Image(valmax, largeur, hauteur);
			for (int i = 0; i < hauteur; i++) {
				for (int j = 0; j < largeur; j++) {
					img.image[i][j] = Double.valueOf(ligneLu);// si on laisse en
					ligneLu = fluxEntree.readLine();
					// entier perte de
					// precision
				}
			}
			fluxEntree.close();
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("renvoie null: " + e.getMessage());
			return null;
		}
	}

	/**
	 * La méthode qui permet de lire le fichier "image"
	 */

	// static Image lectureFichier(String fichier) {
	//
	// try {
	// File f = new File(fichier); // ouvrir le fichier
	// FileInputStream fi = new FileInputStream(f); // entrer les bits dans
	// // le fichier f
	// Scanner sc = new Scanner(fi);
	// sc.next("P2");// contient magic nbr
	//
	// int largeur = sc.nextInt();
	// int hauteur = sc.nextInt();
	// int valmax = sc.nextInt();
	//
	// Image img = new Image(valmax, largeur, hauteur);// instance img de
	// // type Image et la
	// // remplire avec
	// // image telechargé
	// for (int i = 0; i < largeur; i++) {
	// for (int j = 0; j < hauteur; j++)
	// img.image[i][j] = (double) sc.nextInt();// si on laisse en
	// // entier perte de
	// // precision
	// }
	// sc.close();
	// fi.close();
	// return img;
	// } catch (Exception e) {
	// return null;
	// }
	// }

	void ecritureFichier(String fichier) {
		File f = new File(fichier);
		PrintWriter output = null;
		try {
			output = new PrintWriter(f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
		try {
			output.println("P2");
			output.println("" + largeur + " " + hauteur);
			output.println("" + valmax);
			for (int i = 0; i < hauteur; i++) {
				for (int j = 0; j < largeur; j++) {
					output.println("" + (int) image[i][j]);
				}
			}
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void convolution(double[][] image1, int numero) {
		ConvolutionKernel kernel = new ConvolutionKernel(numero);
		int kernelWidth = kernel.getLargeur();
		int kernelHeight = kernel.getHauteur();
		int smallWidth = this.getLargeur() - kernelWidth + 1;
		int smallHeight = this.getHauteur() - kernelHeight + 1;
		double[][] output = new double[hauteur][largeur];
		for (int i = 0; i < hauteur; ++i) {
			for (int j = 0; j < largeur; ++j) {
				output[i][j] = 0;
			}
		}
		for (int i = 0; i < smallHeight; ++i) {
			for (int j = 0; j < smallWidth; ++j) {
				output[i][j] = convolutionParPixel(image1, i, j, kernel.getKernel(), kernelWidth, kernelHeight);
				// if (i==32- kernelWidth + 1 && j==100- kernelHeight + 1)
				// System.out.println("Convolve2D: "+output[i][j]);
			}
		}
		this.image = output;
		// return output;
	}

	public void convolution(Image image, int numero) {
		convolution(image.image, numero);
	}

	/**
	 * prend une image, un noyaux et une position, applique la convolution à la
	 * position et retourne la nouvelle valeur du pixel.
	 * 
	 * @param entree
	 * @param x
	 * @param y
	 * @param noyau
	 * @param largeurNoyau
	 * @param HauteurNoyau
	 * @return
	 */
	private static double convolutionParPixel(double[][] entree, int x, int y, double[][] noyau, int largeurNoyau,
			int HauteurNoyau) {
		double output = 0;
		for (int i = 0; i < HauteurNoyau; ++i) {
			for (int j = 0; j < largeurNoyau; ++j) {
				output = output + (entree[x + i][y + j] * noyau[i][j]);
			}
		}
		return output;
	}

}
