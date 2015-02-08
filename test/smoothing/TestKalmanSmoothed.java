package test.smoothing; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import filter.Measurement;
import filter.MeasurementModel;
import filter.StateGaussian;
import filter.ProcessModel;
import smoothing.*; 
import models.process.general.*; 
import models.measurement.CartesianMeasurementModel; 

public class TestKalmanSmoothed {

   @Test
   public void testSmoothing() {
      
	  Matrix x = new Matrix(new double[][]{{0},{0},{0},{0}});
	  Matrix P = Matrix.identity(4,  4).times(100000.0);  
	  
	  ProcessModel processModel = new ConstantVelocity(0.001); 
	  MeasurementModel measModel = new CartesianMeasurementModel(0.001); 
	  
	  KalmanPosterior post0 = new KalmanPosterior(x, P, 0.0, processModel);  
	  
	  Measurement meas = new Measurement(new double[][]{{1},{1}}, 1.0, measModel);
	  
	  KalmanPosterior post1 = post0.filter(meas);
	  KalmanSmoothed smooth1 = post1.getSmoother(); 
	  KalmanSmoothed smooth0 = smooth1.smooth(); 
	  
	  assertEquals(0.0, smooth0.time(), 0.01); 
	  assertEquals(1.0, smooth1.time(), 0.01); 
	  
	  assertEquals(0.5, smooth0.mean().get(0, 0), 0.01); 
	  assertEquals(0.5, smooth0.mean().get(1, 0), 0.01);
	  assertEquals(0.5, smooth0.mean().get(2, 0), 0.01); 
	  assertEquals(0.5, smooth0.mean().get(3, 0), 0.01); 
	  
	  assertTrue(smooth0.cov().det() < post0.cov().det());
  
   }
   

}