package filter.unscented.util; 

import util.probability.Gaussian;
import Jama.Matrix; 

public class SigmaPoints {

    private static final double lambda = 3; 
    private final int dimension; 
    private final int numberOfPoints; 
    private final double[] weights; 
    private final Matrix[] points;  

    public SigmaPoints(Gaussian density){

		dimension = density.dimension(); 
		numberOfPoints = 2*dimension + 1; 
	
		points = new Matrix[numberOfPoints]; 
		weights = new double[numberOfPoints]; 

		points[0] = density.mean(); 

		for(int i = 1; i < dimension; i++){
		
			points[i] = density.mean();

		}

    }


}