package test; 

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.util.probability.TestGaussian; 

public class TestKalman4J {
    public static void main(String[] args) {
	Result result = JUnitCore.runClasses(TestGaussian.class);
	for (Failure failure : result.getFailures()) {
	    System.out.println(failure.toString());
	}
	System.out.println(result.wasSuccessful());
    }
}  