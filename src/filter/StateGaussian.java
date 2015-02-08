package filter;

import Jama.Matrix;
import util.probability.Gaussian;

/**
 * Implements a gaussian density in the state space and time of a dynamical system 
 * that is governed by a process model implemented as a discretized multivariate stochastic differential equation
 * @see filter.ProcessModel
 * @see <a href="http://en.wikipedia.org/wiki/State_space">State space</a>
 * @see <a href="http://en.wikipedia.org/wiki/Dynamical_system">Dynamical system</a>
 * @see <a href="http://en.wikipedia.org/wiki/Stochastic_differential_equation">Stochastic differential equation</a>
 */

public class StateGaussian extends Gaussian {

	private final double t; 
	private final ProcessModel mod; 
	
	public StateGaussian(Matrix mean, Matrix cov, double t, ProcessModel mod){
		super(mean, cov); 
		this.t = t;
		this.mod = mod;  
	}
	
	/**
	 * Transforms the distribution in state space and adds process noise to represent the information about 
	 * forward in time
	 *
	 * @param  	t the time to which you wish to predict the distribution
	 * @return	gaussian distribution in state space representing the information about the process at time t
	 */
	public StateGaussian predict(double t){
		
		double T = t - this.time(); 
		
		Matrix F = getF(t);
		Matrix Q = getQ(t);
		
		Matrix x = this.mod.predict(this.mean(), T);
		Matrix P = (F.times(this.cov()).times(F.transpose())).plus(Q);
		
		return new StateGaussian(x, P, t, mod); 
		
	}
	
	/**
	 * Matrix to transform point in state space forward in time
	 *
	 * @param  	t the time to which you wish to predict a point in state space at current time
	 * @return	transformation matrix for prediction
	 */
	public Matrix getF(double t){
		
		return mod.getF(mean(), t - this.time());	
		
	}
	
	/**
	 * Process noise covariance matrix representing the loss off information about the process by moving 
	 * forward in time
	 *
	 * @param  	t the time at which you wish to find the information loss
	 * @return	noise covariance matrix representing loss off information about the process by moving 
	 * forward in time 
	 */
	public Matrix getQ(double t){
		
		return mod.getQ(mean(), t - this.time());	
		
	}
	
	/**
	 * Get the state space process model
	 *
	 * @return	process model representing the dynamics and information loss about the process 
	 * represented in state space
	 */
	public ProcessModel getProcessModel(){
		return mod; 
	}
	
	/**
	 * Get current process time
	 *
	 * @return	current process time
	 */
	public double time(){
		return t; 
	}
	
	
	
}
