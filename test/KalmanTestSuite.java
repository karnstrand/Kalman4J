package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.filter.*;
import test.util.probability.*; 


@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestGaussian.class,
   TestStateGaussian.class
})
	
public class KalmanTestSuite {   
}  