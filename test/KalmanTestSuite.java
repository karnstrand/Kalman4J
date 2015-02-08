package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.filter.*;
import test.util.probability.*; 
import test.models.process.general.*; 
import test.models.measurement.*; 

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestGaussian.class,
	TestStateGaussian.class,
	TestConstantVelocity.class,
	TestConstantAcceleration.class,
	TestCartesianMeasurementModel.class,
	TestKalmanPrediction.class,
	TestKalmanPosterior.class
})
	
public class KalmanTestSuite {   
}  