package test.models.process.general; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import filter.*; 
import util.MatrixFactory; 
import models.process.general.*; 

public class TestConstantVelocity {

   @Test
   public void testPredict() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0},{1},{1},{0},{0}});
	  Matrix P = MatrixFactory.eye(6);  
	  
	  ProcessModel model = new ConstantVelocity(1.0); 
	  
      Matrix F = model.getF(null, 1.0); 
   
   	  assertEquals(F.get(0,0), 1.0, 0.01);
   	
	  
   }

}