package filter;

import Jama.Matrix;
import util.probability.Gaussian;

public class StateGaussian extends Gaussian{

	private final double t; 
	
	public StateGaussian(Matrix mean, Matrix cov, double t){
		super(mean, cov); 
		this.t = t; 
	}
	
	public double time(){
		return t; 
	}
	
}
