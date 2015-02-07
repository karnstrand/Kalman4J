package filter.unscented;

import Jama.Matrix;

public interface ProcessModel {
	
	public Matrix f(Matrix x, Matrix v, double T); 
	public Matrix getNoise(); 

	
}
