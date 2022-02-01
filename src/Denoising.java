/**
 * 
 * @author kokou
 * Classe contenant les méthodes de débruitage
 */
public class Denoising implements ImageOP {
	private Image img;
	/**
	 * méthode effectuant le débruitage FGP
	 * @param b  paramètre représentant la matrice d'image
	 * @param lambda  paramètre de régularisation
	 * @param N  paramètre représentant le nombre d'itérations
	 * @return
	 */
	public static double[][] FGP(double[][] b, double lambda, int N) {
		int m = b.length;
		int n = b[0].length;
		double r[][][] = zeros(m, n);
		double p[][][] = zeros(m, n); // p[0]  p[1] sont les deux matrices après PP
		double temp[][][] = zeros(m, n); // matrice du résultat des LT
		double[][] mk = new double[m][n]; //matrice qui va contenir l'opérateur L
		double[][] PCk = new double[m][n]; // matrice qui contient b-L()
		double[][] ak = new double[m][n]; //matrice qui contient la projection PC
		// double[][] bk = new double[m - 1][n];
		// double[][] ck = new double[m][n - 1];
		double[][] x = new double[m][n]; // la matrice qui contient l'image finale
		double[][] pk = new double[m - 1][n]; // la matrice qui contient rk + 1/8(LT(...))
		double[][] qk = new double[m][n - 1]; // la matrice qui contient sk + 1/8(LT(...))
		double[][] pk1 = new double[m - 1][n]; 
		double[][] qk1 = new double[m][n - 1];
		pk1 = r[0];
		qk1 = r[1];
		double tk = 1;
		double tk1 = 1;

		for (int k = 1; k <= N; k++) {

			mk = L(r[0], r[1]);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					PCk[i][j] = b[i][j] - lambda * mk[i][j];
				}
			}
			ak = PC(PCk);
			temp = LT(ak);
			for (int i = 0; i < m - 1; i++) {
				for (int j = 0; j < n; j++) {
					pk[i][j] = r[0][i][j] + (1 / (8 * lambda)) * (temp[0][i][j]);
				}
			}
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n - 1; j++) {
					// if (i < m - 1 && j < n - 2) {
					qk[i][j] = r[1][i][j] + (1 / (8 * lambda)) * (temp[1][i][j]);
					// }
				}
			}
			p = PP(pk, qk);
			tk1 = (1 + Math.sqrt(1 + 4 * tk * tk)) / 2;

//			System.out.println("valeur de t: " + (tk - 1) / tk1);

			for (int i = 0; i < m - 1; i++) {
				for (int j = 0; j < n; j++) {
					r[0][i][j] = p[0][i][j] + ((tk - 1) / tk1) * (p[0][i][j] - pk1[i][j]);
				}
			}
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n - 1; j++) {
					r[1][i][j] = p[1][i][j] + (((tk - 1) / tk1)) * (p[1][i][j] - qk1[i][j]);
				}
			}

			// mise à jour
			tk = (1 + Math.sqrt(1 + 4 * tk * tk)) / 2;
			pk1 = p[0];
			qk1 = p[1];

		}

		mk = L(pk1, qk1);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				mk[i][j] = b[i][j] - lambda * mk[i][j];
			}
		}
		x = PC(mk);
		// for (int i = 0; i < m - 1; i++) {
		// for (int j = 0; j < n - 1; j++) {
		// System.out.print("" + pk1[i][j]);
		// }
		// System.out.println("\n");
		// }
		return x;
	}

	/**
	 * méthode calculant l'opérateur LT
	 * @param img
	 * @return
	 */
	public static double[][][] LT(double[][] img) {
		int m = img.length;
		int n = img[0].length;
		double[][] p = new double[m - 1][n];
		double[][] q = new double[m][n - 1];
		double[][][] r = { p, q };
		for (int i = 0; i < m - 1; i++) {
			for (int j = 0; j < n; j++) {
				if (j > 0 && j < n - 1 && i > 0 && i < m - 2) {
					r[0][i][j] = img[i][j] - img[i + 1][j];
				}

			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (j > 0 && j < n - 2 && i > 0 && i < m - 1) {
					r[1][i][j] = img[i][j] - img[i][j + 1];
				}

			}
		}
		return r;
	}

	public static double[][] L(double[][] p, double[][] q) {
		int m = p.length + 1;
		int n = p[0].length;
		double[][] L = new double[m][n];
		double pij = 0;
		double qij = 0;
		double pimmj = 0;
		double qijmm = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (j > 0) {
					qijmm = q[i][j - 1];
				}
				if (j < n - 2) {
					qij = q[i][j];
				}
				if (i > 0) {
					pimmj = p[i - 1][j];
				}
				if (i < m - 2) {
					pij = p[i][j] = 0;
				}
				// L[i][j] = p[i][j]+ q[i][j] - p[i - 1][j] - q[i][j - 1]; ;
				L[i][j] = pij + qij - pimmj - qijmm;
			}
		}
		return L;
	}

	private static double[][] PC(double[][] img) {
		int n = img.length;
		int m = img[0].length;
		// double[][] res = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (img[i][j] <= 0) {
					img[i][j] = 0;
				}
				if (img[i][j] > 255) {
					img[i][j] = 255;
				}
			}
		}
		return img;
	}

	public static double[][][] PP(double[][] p, double[][] q) {
		int m = p.length + 1;
		int n = p[0].length;
		double[][][] r = { p, q };
		for (int i = 0; i < m - 1; i++) {
			for (int j = 0; j < n; j++) {
				if (j < n - 1) {
					r[0][i][j] = p[i][j] / (Math.max(1, normeF(p[i][j], q[i][j])));
				}
				if (j == n - 1) {
					r[0][i][j] = p[i][j] / Math.max(1, Math.abs(p[i][j]));
				}

			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (i < m - 1) {
					r[1][i][j] = q[i][j] / (Math.max(1, normeF(p[i][j], q[i][j])));
				}
				if (i == m - 1) {
					r[1][i][j] = q[i][j] / Math.max(1, Math.abs(q[i][j]));
				}

			}
		}
		return r;
	}

	public static double normeF(double p, double q) {
		return Math.sqrt(p * p + q * q);
	}

	public static double[][][] zeros(int m, int n) {
		double[][] p = new double[m - 1][n];
		double[][] q = new double[m][n - 1];
		double[][][] r = { p, q };
		for (int i = 0; i < (m - 1); i++) {
			for (int j = 0; j <= (n - 1); j++) {
				r[0][i][j] = 0;
			}
		}
		for (int i = 0; i <= (m - 1); i++) {
			for (int j = 0; j < (n - 1); j++) {
				r[1][i][j] = 0;
			}
		}
		return r;
	}

	@Override
	public Image Op(Image img) {
		img.setImage(FGP( img.getImage(),10, 200));
		return img;
	}
}
