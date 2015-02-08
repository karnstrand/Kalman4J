package util.probability; 

import Jama.Matrix; 

public class Gaussian{

	private final Matrix x;
	private final Matrix P;
	private final int dim; 
	
	public Gaussian(Matrix mean, Matrix cov){
		x = mean; 
		P = cov; 
		dim = mean.getRowDimension(); 
	}
	
	public Matrix mean(){
		return x; 
	}
	
	public Matrix cov(){
		return P; 
	}
	
	public int dimension(){
		return dim; 
	}
	
	public double density(Matrix point){
		return Math.pow((2 * Math.PI), -0.5 *(double)dim) * 
			Math.pow(this.P.det(), -0.5) * Math.exp(-0.5 * (this.x.minus(point).transpose().times(this.P.inverse()).times(this.x.minus(point))).get(0,0));   
	}
	
}