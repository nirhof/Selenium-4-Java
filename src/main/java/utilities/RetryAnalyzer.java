package utilities;

        import org.testng.IRetryAnalyzer;
        import org.testng.ITestResult;

/**
 * A retry analyzer to retry failed tests up to a specified limit.
 */
public class RetryAnalyzer implements IRetryAnalyzer
{
    int counter = 0;
    int retryLimit =3;

    @Override
    public boolean retry(ITestResult test)
    {
        if (counter < retryLimit){
            counter++;
            System.out.println("Retrying " + test.getName() + " Attempt #" + counter);
            return true;
        }
        return false;
    }
}
