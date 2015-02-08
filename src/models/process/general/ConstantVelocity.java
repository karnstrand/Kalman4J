package models.process.general;

import filter.ProcessModel;
import Jama.Matrix;
import util.probability.Gaussian; 
 

public class ConstantVelocity implements ProcessModel
{

	private double var; 
	
	// public interface
	
	public ConstantVelocity(double sigma)
	{
		this.var = sigma*sigma; 
	}
	
	public Matrix predict(Matrix x, Matrix v, double T) {
		
		Matrix F = getF(x, T);
		Matrix B = getB();
		Matrix G = getG(T); 
		return (F.times(x)).plus(G.times(B).times(v)); 
	
	}
	
	public Matrix predict(Matrix x, double T){
		return predict(x, new Matrix(new double[][]{{0},{0}}), T); 
	}
	
	public Matrix getF(Matrix x, double T)
	{
		double[][] F = {{1,0,T,0},{0,1,0,T},{0,0,1,0},{0,0,0,1}}; 
		return new Matrix(F);  
	}

	public Matrix getQ(Matrix x, double T) {
	
		double T2 = T * T; 
		double T3 = T2 * T / 2.0;
		double T4 = T2 * T2 / 4.0; 
		
		double[][] Q = new double[][]{{T4, 0, T3, 0},
								  {0, T4, 0, T3},
								  {T3, 0, T2, 0},
								  {0, T3, 0, T2}};
		
		return (new Matrix(Q)).times(this.var);
		
	}
	
	// private methods
	
	private Matrix getG(double T)
	{
		double T2 = T*T/2.0; 
		double[][] G = {{T,0,T2,0},
			            {0,T,0,T2},
						{0,0,T,0 },
						{0,0,0,T }}; 
		return  new Matrix(G); 
	}
	
	private Matrix getB()
	{
		double[][] BT = new double[][]{{0, 0},{0,0},{1,0},{0,1}};
		return new Matrix(BT); 
	}

	
}
