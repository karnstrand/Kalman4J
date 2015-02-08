package test.util.probability; 

import Jama.Matrix; 

public class Gaussian{

    public static void main(String[] args){

		Matrix x = new Matrix(new double[][]{{0}, {0}});
		Matrix P = new Matrix(new double[][]{{1, 0},{0, 1}}); 

		util.probability.Gaussian gauss = new util.probability.Gaussian(x, P);
	
		System.out.println("halooo!");

    }

}
