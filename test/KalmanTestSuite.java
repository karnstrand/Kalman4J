package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.util.probability.*; 
import test.models.process.general.*; 
import test.models.measurement.*; 

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestGaussian.class,
	test.filter.TestStateGaussian.class,
	TestConstantVelocity.class,
	TestConstantAcceleration.class,
	TestCartesianMeasurementModel.class,
	test.filter.TestKalmanPrediction.class,
	test.filter.TestKalmanPosterior.class,
	test.smoothing.TestKalmanPrediction.class,
	test.smoothing.TestKalmanPosterior.class,
	test.smoothing.TestKalmanSmoothed.class
})
	
public class KalmanTestSuite {   
}  