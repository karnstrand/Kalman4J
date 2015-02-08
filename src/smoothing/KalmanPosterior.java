package smoothing;

import Jama.Matrix;
import filter.ProcessModel;

public class KalmanPosterior extends filter.KalmanPosterior{

	private KalmanPrediction pred; 
	
	public KalmanPosterior(Matrix x, Matrix P, double t, ProcessModel mod) {
		super(x, P, t, mod);
		pred = null; 
	}
	
	public KalmanPrediction predict(double t){
		
		ProcessModel mod = getProcessModel();  
		double T = t - time(); 
		
		Matrix F = getF(t);
		Matrix Q = getQ(t);
		
		Matrix x = mod.predict(mean(), T);
		
		Matrix Ppart = cov().times(F.transpose());
		Matrix P = (F.times(Ppart)).plus(Q); 
		
		Matrix A = Ppart.times(P.inverse());
		
		return new KalmanPrediction(x, P, t, mod, this, A);
		
	}
	
	public boolean hasPrediction(){
		return (pred != null); 
	}
	
	public KalmanPrediction getPrediction(){
		return pred; 
	}
	
	public boolean hasParent(){
		if(hasPrediction()){
			return pred.hasParent();
		}
		return false; 
	}
	
	public KalmanPosterior getParent(){
		return pred.getParent(); 
	}
	
	public KalmanSmoothed getSmoother() {
		return new KalmanSmoothed(this); 
	}
	
	public void clean(int N) {
		if(N==0){
			pred = null;
		} else if(hasParent()){
			getParent().clean(N--);
		}
		
	}
	
}
