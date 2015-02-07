package filter;

import Jama.Matrix;



public class KalmanPosterior extends StateGaussian{
	
	private final ProcessModel mod; 
	
	public KalmanPosterior(Matrix x, Matrix P, double t, ProcessModel mod) {
		super(x, P, t);
		this.mod = mod; 
	}
	
	public KalmanPrediction predict(double t){
		
		double T = t - time(); 
		
		Matrix F = mod.getF(mean(), T);
		Matrix Q = mod.getQ(mean(), T);
		
		Matrix x = mod.f(mean(), T);
		Matrix P = (F.times(cov()).times(F.transpose())).plus(Q);
		
		return new KalmanPrediction(x, P, t, mod); 
		
	}

	public ProcessModel getProcessModel(){
		return mod; 
	}
	
	
	public KalmanPosterior filter(Measurement M) {
		KalmanPrediction pred = predict(M.time());
		return pred.update(M);
	}


	
	
	
}
