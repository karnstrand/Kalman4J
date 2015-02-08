package test.filter; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import filter.StateGaussian; 
import util.MatrixFactory; 

public class TestStateGaussian {

   @Test
   public void testTime() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0}});
	  Matrix P = MatrixFactory.eye(2);  
	  
	  StateGaussian gauss = new StateGaussian(x, P, 2.0);  
	  
      assertEquals(gauss.time(), 2.0, 0.01);
	  
   }

}