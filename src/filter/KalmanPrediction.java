package filter;

import Jama.Matrix;


/**
 * Implements predicton in the Kalman Filter.
 * @see filter.StateGaussian
 * @see <a href="http://en.wikipedia.org/wiki/Kalman_filter">Kalman filter</a>
 */
public class KalmanPrediction extends StateGaussian{
	
	public KalmanPrediction(Matrix x, Matrix P, double t, ProcessModel mod){
		
		super(x, P, t, mod); 
		 
	}

	/**
	 * Perform Kalman update given a meaurement
	 *
	 * @param   M the measurement
	 * @return	the updated state
	 */
	public KalmanPosterior update(Measurement M) {
		
		MeasurementModel measmod = M.getMeasurementModel();
		
		Matrix y = measmod.h(mean());
		
		Matrix R = measmod.getR(null);
		Matrix H = measmod.getH(mean()); 
		
		Matrix PHT = cov().times(H.transpose());
		Matrix S = (H.times(PHT)).plus(R);
		Matrix K = PHT.times(S.inverse());
		Matrix x = mean().plus(K.times(M.minus(y))); 
		
		int n = K.getRowDimension();  
		Matrix Pmult = (Matrix.identity(n, n)).minus(K.times(H));
		Matrix P = (Pmult.times(cov()).times(Pmult.transpose())).plus(K.times(R).times(K.transpose())); 
	
		return new KalmanPosterior(x, P, this.time(), this.getProcessModel());
		
	}
	
	
}
