package test.filter; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import filter.*; 
import models.process.general.*; 
import models.measurement.CartesianMeasurementModel; 

public class TestKalmanPosterior {

   @Test
   public void testSmallMeasurementNoise() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0},{0},{0}});
	  Matrix P = Matrix.identity(4,  4).times(1.0);  
	  
	  ProcessModel processModel = new ConstantVelocity(10.0); 
	  MeasurementModel measModel = new CartesianMeasurementModel(0.001); 
	  
	  KalmanPosterior post0 = new KalmanPosterior(x, P, 0.0, processModel);  
	  
	  Measurement meas = new Measurement(new double[][]{{1},{1}}, 1.0, measModel);
	  
	  KalmanPosterior post1 = post0.filter(meas); 
	  
	  assertEquals(post1.time(), 1.0, 0.01); 
	  assertEquals(post1.mean().get(0, 0), 1.0, 0.1); 
	  assertEquals(post1.mean().get(1, 0), 1.0, 0.1); 
	  assertTrue(post1.cov().det() < post0.cov().det());
  
   }
   
   @Test
   public void testLargeMeasurementNoise() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0},{0},{0}});
	  Matrix P = Matrix.identity(4, 4).times(0.00001);  
	  
	  ProcessModel processModel = new ConstantVelocity(0.00001); 
	  MeasurementModel measModel = new CartesianMeasurementModel(100); 
	  
	  KalmanPosterior post0 = new KalmanPosterior(x, P, 0.0, processModel);  
	  
	  Measurement meas = new Measurement(new double[][]{{1},{1}}, 1.0, measModel);
	  
	  KalmanPosterior post1 = post0.filter(meas); 
	  
	  assertEquals(post1.time(), 1.0, 0.01); 
	  assertEquals(post1.mean().get(0, 0), 0.0, 0.1); 
	  assertEquals(post1.mean().get(1, 0), 0.0, 0.1); 
	  assertEquals(post1.cov().det(), post0.cov().det(), 0.1);
  
   }
   
   @Test
   public void testMediumMeasurementNoise() {
      
 	  Matrix x = new Matrix(new double[][]{{0},{0},{0},{0}});
 	  Matrix P = Matrix.identity(4, 4).times(1.000);
	  P.set(2, 2, 0.000001); 
	  P.set(3, 3, 0.000001);  
	  
 	  ProcessModel processModel = new ConstantVelocity(0.0000001); 
 	  MeasurementModel measModel = new CartesianMeasurementModel(1.0); 
	  
 	  KalmanPosterior post0 = new KalmanPosterior(x, P, 0.0, processModel);  
	  
 	  Measurement meas = new Measurement(new double[][]{{1},{1}}, 1.0, measModel);
	  
 	  KalmanPosterior post1 = post0.filter(meas); 
	  
 	  assertEquals(post1.time(), 1.0, 0.01); 
 	  assertEquals(post1.mean().get(0, 0), 0.5, 0.1); 
 	  assertEquals(post1.mean().get(1, 0), 0.5, 0.1); 
 	  assertTrue(post1.cov().det() < post0.cov().det());
  
   }

}