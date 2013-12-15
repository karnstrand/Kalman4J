package util; 

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
	
	

}