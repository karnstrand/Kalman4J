package models.process;

import filter.ProcessModel;
import matrix.Matrix;
import probability.Gaussian;
import state.unscented.UnscentedProcessModel; 
 

public class ConstantAcceleration implements ProcessModel, UnscentedProcessModel{
	 
	private Gaussian noise; 
	
	public ConstantAcceleration(double[][] jerk, double sigma)
	{
		noise = new Gaussian(new Matrix(jerk), Matrix.eye(2).times(sigma*sigma)); 
	}
	
	
	public ConstantAcceleration(Matrix jerk, double sigma)
	{
		noise = new Gaussian(jerk, Matrix.eye(2).times(sigma*sigma));  
	}
	
	public Matrix predict(Matrix x, Matrix v, double T) {
		
		Matrix B = getB();
		Matrix F = getF(T);
		Matrix G = getG(T); 
		return (F.times(x)).plus(G.times(B).times(v));
	}
	
	public Matrix predict(Matrix x, double T) {
		
		return predict(x, noise.mean(), T); 
	}

	
	public Matrix getF(double T)
	{
		double[][] F = {{1,0,T,0,T*T/2.0,0},{0,1,0,T,0,T*T/2.0},{0,0,1,0,T,0},{0,0,0,1,0,T},{0,0,0,0,1,0},{0,0,0,0,0,1}}; 
		return new Matrix(F); 
	}
	
	public Matrix getF(Matrix x, double T)
	{
		return getF(T);  
	}
	
	private Matrix getG(double T)
	{
		double[][] G = {{T,0,T*T/2,0,T*T*T/6.0,0},{0,T,0,T*T/2,0,T*T*T/6.0},{0,0,T,0,T*T/2,0},{0,0,0,T,0,T*T/2},{0,0,0,0,T,0},{0,0,0,0,0,T}}; 
		return new Matrix(G); 
	}

	private Matrix getB(){
		double[][] BT = {{0, 0},{0,0},{0,0},{0,0},{1,0},{0,1}}; 
		return new Matrix(BT); 
	}
	
	/**
	 * Get linear motion uncertainty matrix Q
	 * @param T Prediction time
	 * @return Linear motion uncertainty matrix Q  
	 */
	
	public Matrix getQ(Matrix x, double T) {
		Matrix B = getB();
		Matrix G = getG(T); 
		return G.times(B).times(noise.cov()).times(B.transpose()).times(G.transpose()); 
	}
	
	public Matrix transform(Matrix x, Matrix v){
		return x.copy(); 
	}

	public Gaussian getNoise() {
		return noise; 
	}

	public Matrix linearize(Matrix x) {
		return x.copy();
	}
	
	public Matrix transform(Matrix x) {
		return x.copy();
	}


	public Matrix transformJacobian(Matrix x) {
		return Matrix.eye(6);
	}


	public Matrix linearizeJacobian(Matrix x) {
		return Matrix.eye(6);
	}

	
}

