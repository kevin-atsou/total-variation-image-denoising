
public class ConvolutionKernel {
	private double kernel[][];
	private int hauteur;
	private int largeur;

	public ConvolutionKernel(int numero) {
		this.largeur = 3;
		this.hauteur = 3;
		this.kernel = new double[hauteur][largeur];
		switch (numero) {
		case 0:

			System.out.println("convolution récupérer les bords de l'image");
			for (int i = 0; i < 3; i++) {
				this.kernel[i][0] = -1;
			}
			this.kernel[0][1] = -1;
			this.kernel[1][1] = 12;
			this.kernel[2][1] = -1;

			for (int i = 0; i < 3; i++) {
				this.kernel[i][2] = -1;
			}
			break;
		case 1:
			System.out.println("convolution pour détruire l'image");
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					this.kernel[i][j] = 1;
				}
			}

			break;
		}

	}

	// public ConvolutionKernel getKernelInstance(int numero){
	// return new ConvolutionKernel(numero);
	// }
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

	public double[][] getKernel() {
		return kernel;
	}

	public void setKernel(double[][] kernel) {
		this.kernel = kernel;
	}

}
