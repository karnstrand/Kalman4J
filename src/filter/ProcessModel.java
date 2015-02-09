package filter;

import Jama.Matrix;

/**
 * Implements a process model in the state space and time of a dynamical system described as a 
 * discretized multivariate stochastic differential equation
 * @see filter.ProcessModel
 * @see <a href="http://en.wikipedia.org/wiki/State_space">State space</a>
 * @see <a href="http://en.wikipedia.org/wiki/Dynamical_system">Dynamical system</a>
 */
public interface ProcessModel {
	 
	/**
	 * Transforms point in state space forward in time
	 *
	 * @param   x point in state space which to transform
	 * @param  	T the timespan of the transformation
	 * @return	point in state space which represents the process state after transformation
	 */		 
	public Matrix predict(Matrix x, double T); 
	 
	/**
	 * Matrix to transform point in state space forward in time
	 *
	 * @param   x the current state of the process
	 * @param  	T the timespan of the transformation
	 * @return	transformation matrix for prediction
	 */	 
	public Matrix getF(Matrix x, double T);
	
	/**
	 * Process noise covariance matrix representing the loss off information about the process by moving 
	 * forward in time
	 *
	 * @param   x the current state of the process
	 * @param  	T the timespan of the transformation
	 * @return	noise covariance matrix representing loss off information about the process by predicting 
	 * the process  
	 */
	public Matrix getQ(Matrix x, double T); 

	
}
