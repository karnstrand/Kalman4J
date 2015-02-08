package models.process.general;

import filter.ProcessModel;
import Jama.Matrix; 
import util.probability.Gaussian; 
 

public class ConstantAcceleration implements ProcessModel{
	 
	private double var; 
	
	// public interface
	
	public ConstantAcceleration(double sigma)
	{
		this.var = sigma * sigma; 
	}
	
	public Matrix predict(Matrix x, Matrix v, double T) {
		
		Matrix B = getB();
		Matrix F = getF(x, T);
		Matrix G = getG(T); 
		return (F.times(x)).plus(G.times(B).times(v));
	}
	
	public Matrix predict(Matrix x, double T) {
		
		return predict(x, new Matrix(new double[][]{{0},{0}}), T); 
	}
	
	public Matrix getF(Matrix x, double T)
	{
		double[][] F = {{1,0,T,0,T*T/2.0,0},{0,1,0,T,0,T*T/2.0},{0,0,1,0,T,0},{0,0,0,1,0,T},{0,0,0,0,1,0},{0,0,0,0,0,1}}; 
		return new Matrix(F);   
	}
	
	public Matrix getQ(Matrix x, double T) {
		
		double T2 = T * T; 
		double T3 = T2 * T / 2.0;
		double T4 = T2 * T2 / 4.0; 
		double T5 = T4 * T / 3.0; 
		double T6 = T5 * T / 3.0;
		
		double[][] Q = new double[][]{{T6, 0, T5, 0, T4, 0},
								  {0, T6, 0, T5, 0, T4},
								  {T5, 0, T4, 0, T3, 0},
								  {0, T5, 0, T4, 0, T3},
								  {T4, 0, T3, 0, T2, 0},
								  {0, T4, 0, T3, 0, T2}};
		
		return (new Matrix(Q)).times(this.var);
		
	}
	
	// private methods
	
	private Matrix getG(double T)
	{
		double[][] G = {{T,0,T*T/2,0,T*T*T/6.0,0},{0,T,0,T*T/2,0,T*T*T/6.0},{0,0,T,0,T*T/2,0},{0,0,0,T,0,T*T/2},{0,0,0,0,T,0},{0,0,0,0,0,T}}; 
		return new Matrix(G); 
	}

	private Matrix getB(){
		double[][] BT = {{0, 0},{0,0},{0,0},{0,0},{1,0},{0,1}}; 
		return new Matrix(BT); 
	}	

	
}

