package models.measurement;

import filter.MeasurementModel;
import Jama.Matrix;

public class CartesianMeasurementModel implements MeasurementModel {

	private final Matrix R; 
	
	public CartesianMeasurementModel(double sigma){
		R = new Matrix(new double[][]{{sigma*sigma,0},{0,sigma*sigma}});
	}
	
	public Matrix h(Matrix x, Matrix w) {
		return x.getMatrix(0,1,0,0).plus(w);
	}

	public Matrix h(Matrix x) {
		return x.getMatrix(0,1,0,0);
	}

	public Matrix getH(Matrix x) {
		return new Matrix(new double[][]{{1,0,0,0},{0,1,0,0}}); 
	}

	public Matrix getR(Matrix x) {
		return R; 
	}

}
