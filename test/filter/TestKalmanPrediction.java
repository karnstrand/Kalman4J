package test.filter; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import filter.*; 
import models.process.general.*; 
import models.measurement.CartesianMeasurementModel; 

public class TestKalmanPrediction {

   @Test
   public void testSmallMeasurementNoise() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0},{0},{0}});
	  Matrix P = Matrix.identity(4,  4).times(10);  
	  
	  ProcessModel processModel = new ConstantVelocity(1.0); 
	  MeasurementModel measModel = new CartesianMeasurementModel(0.001); 
	  
	  KalmanPrediction pred = new KalmanPrediction(x, P, 0.0, processModel);  
	  
	  Measurement meas = new Measurement(new double[][]{{1},{1}}, 0.0, measModel);
	  
	  KalmanPosterior post = pred.update(meas); 
	  
	  assertEquals(post.time(), 0.0, 0.01); 
	  assertEquals(post.mean().get(0, 0), 1.0, 0.1); 
	  assertEquals(post.mean().get(1, 0), 1.0, 0.1); 
	  assertTrue(post.cov().det() < pred.cov().det());
  
   }
   
   @Test
   public void testLargeMeasurementNoise() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0},{0},{0}});
	  Matrix P = Matrix.identity(4, 4).times(0.001);  
	  
	  ProcessModel processModel = new ConstantVelocity(1.0); 
	  MeasurementModel measModel = new CartesianMeasurementModel(100); 
	  
	  KalmanPrediction pred = new KalmanPrediction(x, P, 0.0, processModel);  
	  
	  Measurement meas = new Measurement(new double[][]{{1},{1}}, 0.0, measModel);
	  
	  KalmanPosterior post = pred.update(meas); 
	  
	  assertEquals(post.time(), 0.0, 0.01); 
	  assertEquals(post.mean().get(0, 0), 0.0, 0.1); 
	  assertEquals(post.mean().get(1, 0), 0.0, 0.1); 
	  assertTrue(post.cov().det() < pred.cov().det());
  
   }
   
   @Test
   public void testMediumMeasurementNoise() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0},{0},{0}});
	  Matrix P = Matrix.identity(4, 4).times(1);  
	  
	  ProcessModel processModel = new ConstantVelocity(1.0); 
	  MeasurementModel measModel = new CartesianMeasurementModel(1); 
	  
	  KalmanPrediction pred = new KalmanPrediction(x, P, 0.0, processModel);  
	  
	  Measurement meas = new Measurement(new double[][]{{1},{1}}, 0.0, measModel);
	  
	  KalmanPosterior post = pred.update(meas); 
	  
	  assertEquals(post.time(), 0.0, 0.01); 
	  assertEquals(post.mean().get(0, 0), 0.5, 0.1); 
	  assertEquals(post.mean().get(1, 0), 0.5, 0.1); 
	  assertTrue(post.cov().det() < pred.cov().det());
  
   }

}