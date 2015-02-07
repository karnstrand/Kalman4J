package filter.unscented;

import Jama.Matrix;
import filter.KalmanPosterior;
import filter.ProcessModel;
import filter.unscented.util.ProcessSigmaPoints;


public class UnscentedKalmanPosterior extends KalmanPosterior {
	
	
	public UnscentedKalmanPosterior(Matrix x, Matrix P, double t, ProcessModel mod) {
		super(x, P, t, mod);
	}

	
	public UnscentedKalmanPrediction predict(double t){
		
		ProcessSigmaPoints S0 = new ProcessSigmaPoints(this);
		ProcessSigmaPoints SX = S0.predict(t);
		ProcessStateGaussian pre = SX.getStateGaussian();
		CartesianSigmaPoints SXlin = SX.linearize();
		CartesianStateGaussian lin = SXlin.getStateGaussian();
		Matrix A = S0.cov(SX).times(pre.cov().inverse());
		return new UnscentedKalmanPrediction(lin, pre, this, A, SX, SXlin);
	
	}
	

	
	
}
