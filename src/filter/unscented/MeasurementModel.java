package filter.unscented;

import Jama.Matrix;

public interface MeasurementModel {

	public Matrix h(Matrix x, Matrix w);
	public Matrix getR(); 
	
}
