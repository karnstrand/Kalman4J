package test.models.process.general; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import filter.*; 
import models.process.general.*; 

public class TestConstantAcceleration {
	
	private double precision = 0.000001;

   @Test
   public void testProcessMatrix() {
	  
	  ProcessModel model = new ConstantAcceleration(1.0); 
	  
      Matrix F = model.getF(null, 2); 
   
   	  assertEquals(1.0,F.det(), precision);
   	
   }
   
   @Test
   public void testNoiseMatrix() {
	  
	  ProcessModel model = new ConstantAcceleration(1.0); 
	  
      Matrix Q = model.getQ(null, 2); 
   
   	  assertEquals(0,Q.det(), precision);
   	
   }
   
   @Test
   public void testPredict() {
   
	  ProcessModel model = new ConstantAcceleration(1.0); 
	  
	  Matrix x0 = new Matrix(new double[][]{{0},{0},{0},{0},{1},{1}}); 
      Matrix x1 = model.predict(x0, 2.0); 
   
	  assertEquals(2,x1.get(0, 0), 0.01);
	  assertEquals(2,x1.get(1, 0), 0.01);
	  assertEquals(2,x1.get(2, 0), 0.01);
	  assertEquals(2,x1.get(3, 0), 0.01);
	  assertEquals(1,x1.get(4, 0), 0.01);
	  assertEquals(1,x1.get(5, 0), 0.01);
   	
   }

}