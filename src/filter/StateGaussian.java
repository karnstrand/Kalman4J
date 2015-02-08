package filter;

import Jama.Matrix;
import util.probability.Gaussian;

public class StateGaussian extends Gaussian{

	private final double t; 
	private final ProcessModel mod; 
	
	public StateGaussian(Matrix mean, Matrix cov, double t, ProcessModel mod){
		super(mean, cov); 
		this.t = t;
		this.mod = mod;  
	}
	
	public StateGaussian predict(double t){
		
		double T = t - this.time(); 
		
		Matrix F = getF(t);
		Matrix Q = getQ(t);
		
		Matrix x = this.mod.predict(this.mean(), T);
		Matrix P = (F.times(this.cov()).times(F.transpose())).plus(Q);
		
		return new StateGaussian(x, P, t, mod); 
		
	}
	
	public Matrix getF(double t){
		
		return mod.getF(mean(), t - this.time());	
		
	}
	
	public Matrix getQ(double t){
		
		return mod.getQ(mean(), t - this.time());	
		
	}
	
	public ProcessModel getProcessModel(){
		return mod; 
	}
	
	public double time(){
		return t; 
	}
	
	
	
}
