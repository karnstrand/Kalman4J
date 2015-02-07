package models.process;

import filter.ProcessModel;
import Jama.Matrix;
import util.MatrixFactory; 
import util.probability.Gaussian; 
 

public class ConstantVelocity implements ProcessModel{

	private Gaussian noise; 
	
	public ConstantVelocity(double sigma)
	{
		noise = new Gaussian(new Matrix(2,1,0),MatrixFactory.eye(2).times(sigma*sigma)); 
	}
	
	public ConstantVelocity(Matrix acc, double sigma)
	{
		noise = new Gaussian(acc, MatrixFactory.eye(2).times(sigma*sigma));  
	}
	
	public Matrix predict(Matrix x, Matrix v, double T) {
		Matrix F = getF(T);
		Matrix B = getB();
		Matrix G = getG(T); 
		Matrix x_new = (F.times(x)).plus(G.times(B).times(v)); 
		x_new.setMatrix(4,5,0,0,noise.mean());
		return x_new;
	}
	
	public Matrix predict(Matrix x, double T){
		return predict(x, noise.mean(), T); 
	}
	
	public Matrix getF(double T)
	{
		double[][] F = {{1,0,T,0,0,0},{0,1,0,T,0,0},{0,0,1,0,0,0},{0,0,0,1,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}}; 
		return new Matrix(F); 
	}
	
	private Matrix getG(double T)
	{
		double T2 = T*T/2.0; 
		double[][] G = {{T,0,T2,0,0,0},{0,T,0,T2,0,0},{0,0,T,0,0,0},{0,0,0,T,0,0},{0,0,0,0,1,0},{0,0,0,0,0,1}}; 
		return  new Matrix(G); 
	}
	
	public Matrix getF(Matrix x, double T)
	{
		return getF(T);  
	}

	private Matrix getB()
	{
		double[][] BT = {{0, 0},{0,0},{1,0},{0,1},{0,0},{0,0}};
		return new Matrix(BT); 
	}

	public Matrix getQ(Matrix x, double T) {
		Matrix B = getB(); 
		Matrix G = getG(T); 
		Matrix Q = G.times(B).times(noise.cov()).times(B.transpose()).times(G.transpose());
		return Q;  
	}
	
	public Matrix getQ(double T) {
		return getQ(null, T); 
	}

	public Gaussian getNoise(){
		return noise; 
	}



	
}
