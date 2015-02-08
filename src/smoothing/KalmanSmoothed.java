package smoothing;

import Jama.Matrix;
import filter.StateGaussian;

public class KalmanSmoothed extends StateGaussian{

	private final KalmanPosterior post; 
	
	protected KalmanSmoothed(Matrix x, Matrix P, KalmanPosterior post) {
		super(x, P, post.time(), post.getProcessModel());
		this.post = post;  
	}

	
	protected KalmanSmoothed(KalmanPosterior post) {
		super(post.mean(), post.cov(), post.time(), post.getProcessModel());
		this.post = post;  
	}

	public KalmanSmoothed smooth() {
		
		KalmanPosterior parent = post.getParent();
		KalmanPrediction pre = post.getPrediction(); 
		
		Matrix A = pre.getSmoothingGain(); 
		
		Matrix x = parent.mean().plus(A.times(mean().minus(pre.mean()))); 
		Matrix P = parent.cov().plus(A.times(cov().minus(pre.cov())).times(A.transpose())); 
		
		return new KalmanSmoothed(x, P, parent); 
	
	}
	
	
}
