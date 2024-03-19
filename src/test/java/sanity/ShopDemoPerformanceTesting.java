package sanity;

import extensions.UIActions;
import extensions.Verifications;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import utilities.PerformanceMetric;
import workflows.Webflows;

@Listeners(utilities.Listeners.class)
public class ShopDemoPerformanceTesting extends CommonOps {


    // Verify the performance metric for task duration
    // This test verifies whether the time taken for the task to complete, such as adding a product to the cart,
    // meets the expected threshold of 0.9 seconds.
    @Test
    public void test01_verifyPerformanceMetricTimeOfAddingAProductToCart() throws Exception {
        // Enable performance metric tracking
        performanceHandler.enableMetric();

        // Click on the store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Sort the products by price from highest to lowest
        Webflows.SortProductsByPriceLowToHigh();

        // Add a product to the cart and return to the store
        Webflows.addProductAndReturnToStore(2);

        // go to cart page
        UIActions.mouseHover(products.btn_CartMenu);

        // Print performance metrics
        performanceHandler.getMetricList().forEach(metric -> System.out.println(metric.getName() + " " + metric.getValue()));
        performanceHandler.printMetric(PerformanceMetric.TaskDuration.toString());

        // Verify the performance metric for task duration meets the expected threshold of 0.9 seconds.
        performanceHandler.verifyPerformanceMetricTime(PerformanceMetric.TaskDuration.toString(), 0.3);

    }

    // Verify the performance metric for task duration
// This test verifies whether the time taken for the task to complete, such as sorting products by price,
// meets the expected threshold of 0.001 seconds.
    @Test
    public void test02_verifyPerformanceMetricTimeWithWifiConnection() throws Exception {
        // Enable performance metric tracking
        performanceHandler.enableMetric();
        // Emulate WIFI network connection speed
        connectionHandler.emulateNetwork(100, 7500, 7500, ConnectionType.WIFI);

        // Click on the store button in the top menu
        UIActions.click(topMenu.btn_Store);

        // Sort the products by price from highest to lowest
        Webflows.SortProductsByPriceLowToHigh();

        // Print performance metrics
        performanceHandler.printMetric(PerformanceMetric.TaskDuration.toString());

        // Verify the performance metric for task duration meets the expected threshold of 0.001 seconds.
        performanceHandler.verifyPerformanceMetricTime(PerformanceMetric.TaskDuration.toString(), 0.001);

    }
    @AfterMethod
    public void afterMethod() {
        Webflows.RemoveAllItem(cartPage.ProductRow);
        driver.get(getData("url")); // Navigate back to the Atid Store URL after each test method execution

    }
}
