package filter;

import Jama.Matrix;



public class KalmanPrediction extends StateGaussian{
	
	public KalmanPrediction(Matrix x, Matrix P, double t, ProcessModel mod){
		
		super(x, P, t, mod); 
		 
	}

	public KalmanPosterior update(Measurement M) {
		
		MeasurementModel measmod = M.getMeasurementModel();
		
		Matrix y = measmod.h(mean());
		
		Matrix R = measmod.getR();
		Matrix H = measmod.getH(mean()); 
		
		Matrix PHT = cov().times(H.transpose());
		Matrix S = H.times(PHT).plus(R);
		Matrix K = PHT.times(S.transpose());
		Matrix x = mean().plus(K.times((M.minus(y)))); 
		
		int n = K.getRowDimension();  
		Matrix Pmult = (Matrix.identity(n, n)).minus(K.times(H));
		Matrix P = (Pmult.times(cov()).times(Pmult.transpose())).plus(K.times(R).times(K.transpose())); 
	
		return new KalmanPosterior(x, P, this.time(), this.getProcessModel());
		
	}
	
	
}
