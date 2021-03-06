package test.util.probability; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import util.probability.Gaussian; 

public class TestGaussian {

	private static double precision = 0.0000001;

   @Test
   public void testMeanZero() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0}});
	  Matrix P = Matrix.identity(2, 2);  
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  Matrix mean = gauss.mean(); 
	  
      assertEquals(0,mean.get(0, 0), precision);
	  assertEquals(0,mean.get(1, 0), precision);
	  			
   }
   
   @Test
   public void testNegativeMean() {
      
	  Matrix x = new Matrix(new double[][]{{-2},{-3},{-1}});
	  Matrix P = Matrix.identity(3, 3);  
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  Matrix mean = gauss.mean(); 
	  
      assertEquals(-2,mean.get(0, 0), precision);
	  assertEquals(-3,mean.get(1, 0), precision);
	  assertEquals(-1,mean.get(2, 0), precision);
   }
   
   @Test
   public void testPositiveMean() {
      
	  Matrix x = new Matrix(new double[][]{{5}});
	  Matrix P = new Matrix(new double[][]{{0.1}});
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  Matrix mean = gauss.mean(); 
	  
      assertEquals(5.0,mean.get(0, 0), precision);
   }
   
   @Test
   public void testGetP() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0}});
	  Matrix P = new Matrix(new double[][]{{0.1,0.3},{0.3,0.5}});
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  Matrix p = gauss.cov(); 
	  
      assertEquals(0.1, p.get(0, 0), precision);
	  assertEquals(0.3, p.get(1, 0), precision);
	  assertEquals(0.3, p.get(0, 1), precision);
	  assertEquals(0.5, p.get(1, 1), precision);
   }
   
   @Test
   public void testGetDimension() {
      
	  Matrix x = new Matrix(new double[][]{{3},{-2},{3},{4}});
	  Matrix P = new Matrix(new double[][]{{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}});
	  
	  Gaussian gauss = new Gaussian(x, P); 
	  
	  int dim = gauss.dimension(); 
	  
      assertEquals(4,dim);

   }
   
   @Test
   public void testDensity() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0},{0},{0}});
	  Matrix P = new Matrix(new double[][]{{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}});
	  
	  Gaussian gauss = new Gaussian(x, P)	; 
	  Matrix point = new Matrix(new double[][]{{0},{0},{0},{0}});
	  
	  double density = gauss.density(point);
	  double expectedDensity  = Math.pow((2 * Math.PI), -2);
	  
      assertEquals(expectedDensity, density, precision);

   }

}