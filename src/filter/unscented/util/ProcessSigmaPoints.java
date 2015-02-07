package filter.unscented.util;

import Jama.Matrix;
import filter.StateGaussian;
import filter.unscented.ProcessModel;


public class ProcessSigmaPoints extends SigmaPoints{

	private ProcessModel model;
	private double time; 
	
	public ProcessSigmaPoints(StateGaussian post, ProcessModel mod){
		super(post);
		add(model.getNoise()); 
		this.model = mod;
		this.time = post.time(); 
	}
	
	public ProcessSigmaPoints(Matrix[][] p, double[] w, double t, ProcessModel model){
		super(p, w); 
		this.model = model; 
		this.time = t; 
	} 
	
	public ProcessSigmaPoints predict(double t){
		
		Matrix[][] new_points = new Matrix[2][numPoints()];
		new_points[1] = getPoints()[1];
		
		for(int i = 0; i < numPoints(); i++){
			new_points[0][i] = model.predict(getPoint(0,i), getPoint(1,i), t - time);  
		}
		
		return new ProcessSigmaPoints(new_points, getWeights(), t, model); 
	}
	
	public ProcessStateGaussian getStateGaussian(){
		Gaussian G = getGaussian(0); ;
		return new ProcessStateGaussian(new ProcessState(G.mean(), time, model), G.cov()); 
	}

	public Matrix cov(SigmaPoints S){
		return covariance(0, S);
	}
	
	
	
}
