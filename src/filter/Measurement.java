package filter;

import Jama.Matrix;

/**
 * Implements a measurement
 * @see filter.StateGaussian
 * @see <a href="http://en.wikipedia.org/wiki/Kalman_filter">Kalman filter</a>
 */
public class Measurement extends Matrix{
	
	private static final long serialVersionUID = 1L;
	private MeasurementModel mod; 
	private double t; 
	
	public Measurement(double[][] x, double t, MeasurementModel mod) {
		super(x); 
		this.mod = mod;
		this.t = t; 
	}

	/**
	 * Get the measurement model
	 *
	 * @return	the measurement model
	 */
	public MeasurementModel getMeasurementModel(){
		return mod; 
	}
	
	/**
	 * Get current measurement time
	 *
	 * @return	current time
	 */
	public double time(){
		return t; 
	}
	
}
