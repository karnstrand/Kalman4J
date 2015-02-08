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
   
   	  assertEquals(F.det(), 1.0, precision);
   	
   }
   
   @Test
   public void testNoiseMatrix() {
	  
	  ProcessModel model = new ConstantAcceleration(1.0); 
	  
      Matrix Q = model.getQ(null, 2); 
   
   	  assertEquals(0,Q.det(), precision);
   	
   }

}