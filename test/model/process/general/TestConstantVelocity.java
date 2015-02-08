package test.models.process.general; 

import org.junit.* ;
import static org.junit.Assert.* ;
import Jama.Matrix; 
import filter.*; 
import util.MatrixFactory; 
import models.process.general.*; 

public class TestConstantVelocity {

   @Test
   public void testProcessMatrix() {
	  
	  ProcessModel model = new ConstantVelocity(1.0); 
	  
      Matrix F = model.getF(null, 4.12); 
   
   	  assertEquals(F.det(), 1.0, 0.01);
   	
   }
   
   @Test
   public void testNoiseMatrix() {
   
	  ProcessModel model = new ConstantVelocity(1.0); 
	  
      Matrix Q = model.getQ(null, 1.0); 
   
   	  assertEquals(Q.det(), 0.0, 0.01);
	  assertEquals(Q.get(0, 0), 0.25, 0.01);
	  assertEquals(Q.get(1, 1), 0.25, 0.01);
	  assertEquals(Q.get(2, 2), 1.0, 0.01);
	  assertEquals(Q.get(3, 3), 1.0, 0.01);
	  assertEquals(Q.get(0, 2), 0.5, 0.01);
	  assertEquals(Q.get(1, 3), 0.5, 0.01);
   	
   }
   
   @Test
   public void testPredict() {
   
	  ProcessModel model = new ConstantVelocity(1.0); 
	  
	  Matrix x0 = new Matrix(new double[][]{{0},{0},{1},{1}}); 
      Matrix x1 = model.predict(x0, 1.0); 
   
	  assertEquals(x1.get(0, 0), 1, 0.01);
	  assertEquals(x1.get(1, 0), 1, 0.01);
	  assertEquals(x1.get(2, 0), 1, 0.01);
	  assertEquals(x1.get(3, 0), 1, 0.01);
   	
   }

}