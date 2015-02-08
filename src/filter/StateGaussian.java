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
		
		Matrix F = this.mod.getF(this.mean(), T);
		Matrix Q = this.mod.getQ(this.mean(), T);
		
		Matrix x = this.mod.predict(this.mean(), T);
		Matrix P = (F.times(this.cov()).times(F.transpose())).plus(Q);
		
		return new StateGaussian(x, P, t, mod); 
		
	}
	
	public ProcessModel getProcessModel(){
		return mod; 
	}
	
	public double time(){
		return t; 
	}
	
	
	
}
