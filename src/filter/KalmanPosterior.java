package filter;

import Jama.Matrix;



public class KalmanPosterior extends StateGaussian{
	
	public KalmanPosterior(Matrix x, Matrix P, double t, ProcessModel mod) {
		
		super(x, P, t, mod);
	
	}
	
	public KalmanPrediction predict(double t){
		
		StateGaussian state = super.predict(t);
		
		return new KalmanPrediction(state.mean(), state.cov(), state.time(), state.getProcessModel());   
		
	}
	
	public KalmanPosterior filter(Measurement M) {
		
		KalmanPrediction pred = this.predict(M.time());
		return pred.update(M);
	
	}
	
}
