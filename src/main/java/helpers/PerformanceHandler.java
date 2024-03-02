package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v118.performance.model.Metric;
import org.openqa.selenium.devtools.v120.performance.Performance;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import static org.openqa.selenium.devtools.v118.performance.Performance.getMetrics;

public class PerformanceHandler extends DevToolsHandler {

    private final HashMap<String, Number> data = new HashMap<>();

    public PerformanceHandler(WebDriver driver) {

        super(driver);
    }

    public void enableMetric() {
        tool.send(Performance.enable(Optional.of(Performance.EnableTimeDomain.TIMETICKS)));
    }

    public List<Metric> getMetricList() {
        return tool.send(getMetrics());
    }

    public void printMetric(String metricName) {
        for (Metric metric : performanceHandler.getMetricList()) {
            data.put(metric.getName(), metric.getValue());
        }
        System.out.println("Performance metric : " + metricName);
        System.out.println(data.get(metricName));
    }

    public void VerifyPerformanceMetricTime(String performanceMetricName, double expectedPerformanceTime) {
        Number actualPerformanceTimeNumber = data.get(performanceMetricName);

        // Convert to double for comparison
        double actualPerformanceTime = actualPerformanceTimeNumber.doubleValue();

        Assert.assertTrue(actualPerformanceTime < expectedPerformanceTime,
                "Actual performance time: " + actualPerformanceTime +
                        " is not less than expected performance time: " + expectedPerformanceTime);
    }
}
