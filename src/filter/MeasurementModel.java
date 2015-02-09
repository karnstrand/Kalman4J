package filter;

import Jama.Matrix;

/**
 * Implements a measurement model in the state space and time of a dynamical system 
 * @see filter.ProcessModel
 * @see <a href="http://en.wikipedia.org/wiki/State_space">State space</a>
 * @see <a href="http://en.wikipedia.org/wiki/Dynamical_system">Dynamical system</a>
 */
public interface MeasurementModel {
	
	public Matrix h(Matrix x);
	public Matrix getH(Matrix x);
	public Matrix getR(Matrix x); 
	
}
