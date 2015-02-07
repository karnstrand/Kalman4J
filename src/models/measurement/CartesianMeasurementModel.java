package models.measurement;

import filter.MeasurementModel;
import Jama.Matrix;
import util.MatrixFactory; 

public class CartesianMeasurementModel implements MeasurementModel {

	private final Matrix R; 
	
	public CartesianMeasurementModel(double sigma){
		R = new Matrix(new double[][]{{sigma*sigma,0},{0,sigma*sigma}});
	}
	
	public Matrix h(Matrix x, Matrix w) {
		return x.getMatrix(0,2,0,0).plus(w);
	}

	public Matrix h(Matrix x) {
		return x.getMatrix(0,2,0,0);
	}

	public Matrix getH(Matrix x) {
		return MatrixFactory.eye(3); 
	}

	public Matrix getR() {
		return R; 
	}

}
