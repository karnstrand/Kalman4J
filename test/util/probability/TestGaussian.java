package test.util.probability; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import util.probability.Gaussian; 
import util.MatrixFactory; 

public class TestGaussian {

   @Test
   public void testMeanZero() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0}});
	  Matrix P = MatrixFactory.eye(2);  
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  Matrix mean = gauss.mean(); 
	  
      assertEquals(mean.get(0, 0), 0, 0.01);
	  assertEquals(mean.get(1, 0), 0, 0.01);
	  
   }

}