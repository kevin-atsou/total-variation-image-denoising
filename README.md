# Image denoising with total variation regularization using Fast Gradient Projection
Implementation of an image denoising method using total variation regularization. 

# Fast Gradient Projection Method

## Overview
This method is based on the principle that signals with excessive and possibly parasitic details have a high **total variation**. Reducing the total variation of the signal while matching the original signal eliminates undesirable details while preserving important details.

## Mathematical Details
Consider an image whose pixel matrix is represented by $x \in \mathbb{R}^{m \times n}$, $w \in \mathbb{R}^{m \times n}$ an unknown noise, and $b \in \mathbb{R}^{m \times n}$, the noisy image satisfying the relation:
$$b = x + w$$

### Optimization Problem
The denoising problem becomes:
$$\underset{x}{\text{min}} \lbrace{ \Vert x-b \Vert^2 + 2\lambda TV(x)\rbrace}, \qquad (\lambda > 0)$$

### Total Variation (TV)
$TV(\cdot)$ is a semi-norm that can be isotropic or anisotropic. Isotropic $TV_{I}(x)$ is usually defined by:
$$TV_{I}(x) = \sum_{i=1}^{m-1} \sum_{j=1}^{n-1} \sqrt{(x_{i,j}-x_{i+1,j})^2 + (x_{i,j}-x_{i,j+1})^2} + \sum_{i=1}^{m-1} |x_{i,n}-x_{i+1,n}| + \sum_{j=1}^{n-1} |x_{m,j}-x_{m,j+1}|$$

The anisotropic version, $TV_{A}(x)$, is defined similarly but with absolute differences.

## References
1. Beck, A., & Teboulle, M. (2009). Fast gradient-based algorithms for constrained total variation image denoising and deblurring problems. IEEE Transactions on Image Processing, 18(11), 2419-2434. [DOI](https://doi.org/10.1109/TIP.2009.2028250)&#8203;.
2. Chambolle, A. (2004). An algorithm for total variation minimization and applications. Journal of Mathematical Imaging and Vision, 20(1-2), 89-97.




