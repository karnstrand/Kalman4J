package filter.unscented;

import matrix.Matrix;
import state.CartesianStateGaussian;
import state.ProcessStateGaussian;
import unscented.CartesianSigmaPoints;
import unscented.MeasurementSigmaPoints;
import unscented.ProcessSigmaPoints;
import filter.KalmanPosterior;
import filter.KalmanPrediction;
import filter.Measurement;
import filter.MeasurementGaussian;
import filter.Prediction;

public class UnscentedKalmanPrediction extends KalmanPrediction implements Prediction{

	private ProcessSigmaPoints SX;
	private CartesianSigmaPoints SXlin; 
	
	public UnscentedKalmanPrediction(CartesianStateGaussian csg, ProcessStateGaussian internal, KalmanPosterior parent, Matrix A, ProcessSigmaPoints SX, CartesianSigmaPoints SXlin) {
		super(csg, internal, parent, A);
		this.SX = SX;
		this.SXlin = SXlin; 
	}
	
	public UnscentedKalmanPosterior update(Measurement z){
		MeasurementSigmaPoints SY = SXlin.measure(z.getMeasurementModel());
		MeasurementGaussian meas = SY.getMeasurmentGaussian();
		Matrix PXY = SX.cov(SY); 
		Matrix PYYinv = meas.cov().inverse();
		Matrix K = PXY.times(PYYinv);
		Matrix x = getInternal().mean().plus(K.times(z.minus(meas.mean()))); 
		Matrix P = getInternal().cov().minus(K.times(PXY.transpose())); 
		
		ProcessStateGaussian post = new ProcessStateGaussian(x, P, time(), getProcessModel());
		
		SX = null; SXlin = null; 
		
		return new UnscentedKalmanPosterior(post.linearize(), post, this);
		
	}

	
	
}
