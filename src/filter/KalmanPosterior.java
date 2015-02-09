package filter;

import Jama.Matrix;

/**
 * Implements the posterior state in the Kalman filter.
 * @see filter.StateGaussian
 * @see <a href="http://en.wikipedia.org/wiki/Kalman_filter">Kalman filter</a>
 */
public class KalmanPosterior extends StateGaussian{
	
	public KalmanPosterior(Matrix x, Matrix P, double t, ProcessModel mod) {
		
		super(x, P, t, mod);
	
	}
	
	/**
	 * Perform prediction in the Kalman filter
	 *
	 * @param	t the time point
	 * @return	the predicted state in the Kalman filter
	 */
	public KalmanPrediction predict(double t){
		
		StateGaussian state = super.predict(t);
		
		return new KalmanPrediction(state.mean(), state.cov(), state.time(), state.getProcessModel());   
		
	}
	
	/**
	 * Perform filtering in the Kalman filter
	 *
	 * @param	M the measurement
	 * @return	the filterted state in the Kalman filter
	 */
	public KalmanPosterior filter(Measurement M) {
		
		KalmanPrediction pred = this.predict(M.time());
		return pred.update(M);
	
	}
	
}
