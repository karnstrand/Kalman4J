package util.probability; 

import Jama.Matrix; 

/**
 * Implements a multivariate normal distribution
 * @see <a href="http://en.wikipedia.org/wiki/Multivariate_normal_distribution">Multivariate Normal Distribution</a>
 */

public class Gaussian {

	private final Matrix x;
	private final Matrix P;
	private final int dim; 
	
	public Gaussian(Matrix mean, Matrix cov){
		x = mean; 
		P = cov; 
		dim = mean.getRowDimension(); 
	}
	
	/**
	 * Returns the mean of the distribution as a column vector
	 *
	 * @return	mean vector of the distribution
	 */
	public Matrix mean(){
		return x; 
	}
	
	/**
	 * Returns the covariance matrix of the distribution
	 *
	 * @return	covariance matrix of the distribution
	 */
	public Matrix cov(){
		return P; 
	}
	
	/**
	 * Returns the dimensionality of the distribution
	 *
	 * @return	dimensionslity of the distribution
	 */
	public int dimension(){
		return dim; 
	}
	
	/**
	 * Calculates the probability density of the gaussian distribution in a point in space
	 *
	 * @param  	point the point where to evaluate the probability density function
	 * @return	the value of the probability density function
	 */
	
	public double density(Matrix point){
		return Math.pow((2 * Math.PI), -0.5 *(double)dim) * 
			Math.pow(this.P.det(), -0.5) * Math.exp(-0.5 * (this.x.minus(point).transpose().times(this.P.inverse()).times(this.x.minus(point))).get(0,0));   
	}
	
}