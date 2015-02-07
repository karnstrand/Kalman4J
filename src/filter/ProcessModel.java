package filter;

import Jama.Matrix;

public interface ProcessModel {
	 
	public Matrix predict(Matrix x, double T); 
	 
	public Matrix getF(Matrix x, double T);
	public Matrix getQ(Matrix x, double T); 

	
}
