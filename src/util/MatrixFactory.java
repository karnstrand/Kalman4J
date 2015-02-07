package util; 

import Jama.Matrix; 

public class MatrixFactory{
	
	public static Matrix eye(int size){
		
		double[][] array = new double[size][size];

		for(int i = 0; i < size; i++){
		    for(int j = 0; j < size; j++){
			if(i == j){
			    array[i][j] = 1; 
			} else {
			    array[i][j] = 0; 
			}
		    }
		}
		
		return new Matrix(array); 
		
	}
	
	
}
	
	
	
	
	
	