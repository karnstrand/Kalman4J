package test.util.probability; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import util.probability.Gaussian; 
import util.MatrixFactory; 

public class TestGaussian {

	private static double precision = 0.0000001;

   @Test
   public void testMeanZero() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0}});
	  Matrix P = MatrixFactory.eye(2);  
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  Matrix mean = gauss.mean(); 
	  
      assertEquals(mean.get(0, 0), 0, precision);
	  assertEquals(mean.get(1, 0), 0, precision);
	  			
   }
   
   @Test
   public void testNegativeMean() {
      
	  Matrix x = new Matrix(new double[][]{{-2},{-3},{-1}});
	  Matrix P = MatrixFactory.eye(3);  
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  Matrix mean = gauss.mean(); 
	  
      assertEquals(mean.get(0, 0), -2, precision);
	  assertEquals(mean.get(1, 0), -3, precision);
	  assertEquals(mean.get(2, 0), -1, precision);
   }
   
   @Test
   public void testPositiveMean() {
      
	  Matrix x = new Matrix(new double[][]{{5}});
	  Matrix P = new Matrix(new double[][]{{0.1}});
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  Matrix mean = gauss.mean(); 
	  
      assertEquals(mean.get(0, 0), 5, precision);
   }
   
   @Test
   public void testGetP() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0}});
	  Matrix P = new Matrix(new double[][]{{0.1,0.3},{0.3,0.5}});
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  Matrix p = gauss.cov(); 
	  
      assertEquals(p.get(0, 0), 0.1, precision);
	  assertEquals(p.get(1, 0), 0.3, precision);
	  assertEquals(p.get(0, 1), 0.3, precision);
	  assertEquals(p.get(1, 1), 0.5, precision);
   }
   
   @Test
   public void testGetDimension() {
      
	  Matrix x = new Matrix(new double[][]{{3},{-2},{3},{4}});
	  Matrix P = new Matrix(new double[][]{{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}});
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  int dim = gauss.dimension(); 
	  
      assertEquals(dim, 4);

   }
   
   @Test
   public void testDensity() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0},{0},{0}});
	  Matrix P = new Matrix(new double[][]{{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}});
	  
	  Gaussian gauss = new Gaussian(x, P)	; 
	  Matrix point = new Matrix(new double[][]{{0},{0},{0},{0}});
	  
	  double density = gauss.density(point);
	  
	  double result  = Math.pow((2 * Math.PI), -2);
	  
      assertEquals(density, result ,precision);

   }

}