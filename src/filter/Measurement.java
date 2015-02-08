package filter;

import Jama.Matrix;


public class Measurement extends Matrix{
	
	private static final long serialVersionUID = 1L;
	private MeasurementModel mod; 
	private double t; 
	
	public Measurement(double[][] x, double t, MeasurementModel mod) {
		super(x); 
		this.mod = mod;
		this.t = t; 
	}

	public MeasurementModel getMeasurementModel(){
		return mod; 
	}
	
	public double time(){
		return t; 
	}
	
}
