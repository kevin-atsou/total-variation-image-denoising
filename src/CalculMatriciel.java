public class CalculMatriciel {

	public static double[][] produitMatriciel(double[][] mat1, double[][] mat2) {
		int m1rows = mat1.length;
		int m2rows = mat2.length;
		int m1cols = mat1[0].length;
		int m2cols = mat2[0].length;
		double[][] A = null;
		if (m1cols == m2cols) {
			A = zeros(m1rows, m2cols);
			for (int i = 0; i < m1rows; i++) {
				for (int j = 0; j < m2cols; j++) {
					for (int k = 0; k < m1cols; k++) {
						// System.out.println("calcul du produit:");
						A[i][j] += mat1[i][k] * mat2[k][j];
					}
				}
			}
		} else {
			System.out.println("dimensions des matrices non cohérentes");
		}

		return A;
	}

	public static double[][] produitMatriciel(int mat1, double[][] mat2) {
		int m2rows = mat2.length;
		int m2cols = mat2[0].length;
		double[][] A = null;
		A = zeros(m2rows, m2cols);
		for (int i = 0; i < m2rows; i++) {
			for (int j = 0; j < m2cols; j++) {
				// System.out.println("calcul du produit:");
				A[i][j] += mat1 * mat2[i][j];
			}
		}
		System.out.println("dimensions des matrices non cohérentes");

		return A;
	}

	public static double[][] produitMatriciel(double mat1, double[][] mat2) {
		int m2rows = mat2.length;
		int m2cols = mat2[0].length;
		double[][] A = null;
		A = zeros(m2rows, m2cols);
		for (int i = 0; i < m2rows; i++) {
			for (int j = 0; j < m2cols; j++) {
				// System.out.println("calcul du produit:");
				A[i][j] += mat1 * mat2[i][j];
			}
		}
		System.out.println("dimensions des matrices non cohérentes");

		return A;
	}

	public static double[][] somme(double[][] mat1, double[][] mat2) {
		int m1rows = mat1.length;
		int m2rows = mat2.length;
		int m1cols = mat1[0].length;
		int m2cols = mat2[0].length;
		double[][] A = null;

		if (m1cols == m2cols && m1rows == m2rows) {
			A = zeros(m2rows, m2cols);
			for (int i = 0; i < m2rows; i++) {
				for (int j = 0; j < m2cols; j++) {
					// System.out.println("calcul de la somme:");
					A[i][j] = mat1[i][j] + mat2[i][j];
				}
			}
		} else {
			System.out.println("dimensions des matrices non cohérentes");
		}
		return A;
	}

	public static double[][] soustraction(double[][] mat1, double[][] mat2) {
		int m1rows = mat1.length;
		int m2rows = mat2.length;
		int m1cols = mat1[0].length;
		int m2cols = mat2[0].length;
		double[][] A = null;
		if (m1cols == m2cols && m1rows == m2rows) {
			A = zeros(m2rows, m2cols);
			for (int i = 0; i < m2rows; i++) {
				for (int j = 0; j < m2cols; j++) {
					// System.out.println("calcul de la soustraction:");
					A[i][j] = mat1[i][j] - mat2[i][j];
				}
			}
		} else {
			System.out.println("dimensions des matrices non cohérentes");
		}
		return A;
	}

	public static double[][] zeros(int m, int n) {
		double[][] p = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				p[i][j] = 0;
			}
		}

		return p;
	}

}
