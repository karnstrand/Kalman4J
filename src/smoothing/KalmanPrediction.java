package smoothing;

import Jama.Matrix;
import filter.ProcessModel;

public class KalmanPrediction extends filter.KalmanPrediction{

	private final KalmanPosterior parent; 
	private final Matrix A; 
	
	public KalmanPrediction(Matrix x, Matrix P, double t, ProcessModel mod) {
		super(x, P, t, mod);
		A = null;
		parent = null; 
	}
	
	public KalmanPrediction(Matrix x, Matrix P, double t, ProcessModel mod, KalmanPosterior parent, Matrix A) {
		super(x, P, t, mod);
		this.parent = parent; 
		this.A = A; 
	}
	
	public Matrix getSmoothingGain(){
		return A; 
	}
	
	public boolean hasParent(){
		return parent != null; 
	}
	
	public KalmanPosterior getParent(){
		return parent;
	}
	
	
}
