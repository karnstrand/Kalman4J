package test.filter; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import filter.*; 
import util.MatrixFactory; 
import models.process.general.*; 

public class TestStateGaussian {

   @Test
   public void testPredict() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0},{1},{1}});
	  Matrix P = MatrixFactory.eye(4);  
	  
	  ProcessModel model = new ConstantVelocity(1.0); 
	  
	  StateGaussian gauss = new StateGaussian(x, P, 0.0, model);  
	  
      assertEquals(gauss.time(), 0.0, 0.01);
	  assertEquals(gauss.mean().get(0,0), 0.0, 0.01); 
	  assertEquals(gauss.mean().get(1,0), 0.0, 0.01);
	  
	  StateGaussian gauss2 = gauss.predict(1.0); 
	  
	  assertEquals(gauss2.time(), 1.0, 0.01);
	  assertEquals(gauss2.mean().get(0,0), 1.0, 0.01); 
	  assertEquals(gauss2.mean().get(1,0), 1.0, 0.01); 
	  assertTrue(gauss2.cov().det() > gauss.cov().det());
	     
	  
   }

}