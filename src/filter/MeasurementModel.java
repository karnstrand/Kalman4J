package filter;

import Jama.Matrix;


public interface MeasurementModel {
	
	public Matrix h(Matrix x);
	public Matrix getH(Matrix x);
	public Matrix getR(); 
	
}
