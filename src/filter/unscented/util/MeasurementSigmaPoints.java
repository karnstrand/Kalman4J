package filter.unscented.util;

import filter.MeasurementModel;
import probability.Gaussian;
import probability.SigmaPoints;
import matrix.Matrix;
import measurement.MeasurementGaussian;

public class MeasurementSigmaPoints extends SigmaPoints{

	private MeasurementModel model;
	private double time; 
	
	public MeasurementSigmaPoints(Matrix[][] p, double[] w, double t, MeasurementModel model){
		super(p, w); 
		this.model = model; 
		this.time = t; 
	} 
	
	public MeasurementGaussian getMeasurmentGaussian(){
		Matrix mean = Gaussian.mean(getPoints()[0], getWeights());
		Matrix cov = Gaussian.cov(mean, getPoints()[0], getWeights());
		return new MeasurementGaussian(mean, cov, time, model);
	}
	
}
