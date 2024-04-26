package utilities;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Implements the IAnnotationTransformer interface to modify test annotations dynamically
 * at runtime. This transformer specifically sets a retry analyzer to test methods.
 */
public class AnnotationTransformer implements IAnnotationTransformer
{
    /**
     * Overridden method from IAnnotationTransformer interface that allows dynamic modifications
     * of test method annotations before the test is executed.
     *
     * @param annotation the annotation of the currently considered test method.
     * @param testClass the test class that contains the test method. Not used in this implementation.
     * @param testConstructor the constructor of the test class. Not used in this implementation.
     * @param testMethod the method that is about to be executed. Not used in this implementation.
     */
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
    {
        // Sets the RetryAnalyzer class to the test annotation which enables retry logic for failed tests.
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
