package test.models.measurement; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import models.measurement.CartesianMeasurementModel; 

public class TestCartesianMeasurementModel {

	private static double precision = 0.0000001;
	
    @Test
    public void testh() {
      
 	  double sigma = 0.1;
	  CartesianMeasurementModel cartModel = new CartesianMeasurementModel(sigma);
 	  Matrix x = new Matrix(new double[][]{{1},{2},{-2},{4}});
	  Matrix h = cartModel.h(x);
 	  
	  assertEquals(h.get(0,0),1,precision);
	  assertEquals(h.get(1,0),2,precision);
    }
	
    @Test
    public void testhwithw() {
      
 	  double sigma = 0.1;
	  CartesianMeasurementModel cartModel = new CartesianMeasurementModel(sigma);
 	  Matrix x = new Matrix(new double[][]{{1},{2},{-2},{4}});
	  Matrix w = new Matrix(new double[][]{{0.1},{-0.1}});
	  Matrix h = cartModel.h(x,w);
 	  
	  assertEquals(h.get(0,0),1.1,precision);
	  assertEquals(h.get(1,0),1.9,precision);
    }

   @Test
   public void testGetR() {
      
	  double sigma = 0.1;
	  CartesianMeasurementModel cartModel = new CartesianMeasurementModel(sigma);
	  Matrix R = cartModel.getR(null);
	  
      assertEquals(R.get(0, 0), 0.01, precision);
	  assertEquals(R.get(1, 0), 0, precision);
      assertEquals(R.get(0, 1), 0, precision);
	  assertEquals(R.get(1, 1), 0.01, precision);
	  			
   }
   
}