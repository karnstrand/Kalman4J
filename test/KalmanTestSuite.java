package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.filter.*;
import test.util.probability.*; 
import test.models.process.general.*; 

@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestGaussian.class,
   TestStateGaussian.class,
   TestConstantVelocity.class
})
	
public class KalmanTestSuite {   
}  