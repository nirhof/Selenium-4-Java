package utilities;

import helpers.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;


public class CommonOps extends Base {


    // Method Name: getData
    // Method Description: This Method get the data from xml file
    // Method Parameters: String of the node name of the xml whose content is to be retrieved
    // Method Return: String of the content of the specified XML node
    public static String getData(String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("./Configuration/DataConfig.xml"); // Load XML file and set up DocumentBuilder
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            System.out.println("Exception in reading XML file: " + e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    // Method Name : initBrowser
    // Method Description: Initializes a web browser based on the provided browser type.
    // Method Parameters : String of browserType - The type of browser to be initialized (e.g., "chrome", "firefox").
    // Throws : RuntimeException - If an invalid browser type is provided.
    public void initBrowser(String browserType) {
        // Check which browser type is provided
        if (browserType.equalsIgnoreCase("chrome")) // Initialize Chrome WebDriver
            driver = initChromeDriver();
        else if (browserType.equalsIgnoreCase("firefox")) // Initialize Firefox WebDriver
            driver = initFirefoxDriver();
        else if (browserType.equalsIgnoreCase("edge")) // Initialize Edge WebDriver
            driver = initEdgeDriver();
        else if (browserType.equalsIgnoreCase("ie")) // Initialize Internet Explorer WebDriver
            driver = initIEDriver();
        else
            throw new RuntimeException("Invalid browser type"); // Throw an exception for invalid browser types

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(getData("Timeout"))));
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(getData("Timeout"))));
        actions = new Actions(driver);  // Initialize Actions class for performing advanced user interactions
        relativeLocator = new RelativeLocatorHandler(driver);
        screenShot = new ScreenShotHandler(filePath, driver);
        logHandler = new LogHandler(driver);
        logHandler.initLogs();
        insecureHandler = new InsecureHandler(driver);
        insecureHandler.initSecurity();
        tabsWindowsHandler = new TabsWindowsHandler(driver);
        blockHandler = new BlockHandler(driver);
        blockHandler.initNetwork();
        performanceHandler = new PerformanceHandler(driver);
        connectionHandler = new ConnectionHandler(driver);
        pluginsHandler = new PluginsHandler(driver);
        timeZoneHandler = new TimeZoneHandler(driver);
        userAgentHandler = new UserAgentHandler(driver);


        ManagePages.initAtidStoreDemo();  // Initialize components for managing pages
        driver.get(getData("url"));
    }


    // Method Name: initChromeDriver
    // Method Description : Initializes and returns a Chrome WebDriver.
    // Method Return: An instance of Chrome WebDriver.
    public static WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
//        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\JavaCourse\\Projects\\FinalProject\\FinalProject (2)\\FinalProject\\FinalProject\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    // Method Name: initFirefoxDriver
    // Method Description : Initializes and returns a Firefox WebDriver.
    // Method Return: An instance of Firefox WebDriver.
    public static WebDriver initFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    // Method Name: initIEDriver
    // Method Description: Initializes and returns an Internet Explorer WebDriver.
    // Method Return: An instance of Internet Explorer WebDriver.
    public static WebDriver initIEDriver() {
        WebDriverManager.iedriver().setup();
        WebDriver driver = new InternetExplorerDriver();
        return driver;
    }

    // Method Name: initEdgeDriver
    // Method Description : Initializes and returns an Edge WebDriver.
    // Method Return: An instance of Edge WebDriver.
    public static WebDriver initEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        return driver;
    }

    // Method Name : startSession
    // Method Description : Starts a session based on the provided PlatformName from xml file.
    // Method Parameters : String PlatformName - The name of the platform ("web", "mobile", "api", "electron", "desktop").
    @BeforeClass
    @Parameters({"PlatformName"})
    public void startSession(String PlatformName) {
        platform = PlatformName;
        if (platform.equalsIgnoreCase("web"))
            initBrowser(getData("BrowserName"));
        else
            throw new RuntimeException("Invalid platform name");
        softAssert = new SoftAssert();
        screen = new Screen();
        ManageDB.openConnection(getData("DBURL"), getData("DBUserName"), getData("DBPassword"));

    }

    // Method Name : closeSession
    // Method Description: Closes the session.
    @AfterClass
    public void closeSession() {
        if (!platform.equalsIgnoreCase("api")) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            ManageDB.closeConnection();
            if (!platform.equalsIgnoreCase("mobile"))
                driver.quit();

        }
    }

    // Method Name : beforeMethod
    // Method Description: Executes before each test MonteScreenRecorder.startRecord (if platform is not api)
    // Method Parameters : Method - The test being executed.
    @BeforeMethod
    public void beforeMethod(Method method) {
        if (!platform.equalsIgnoreCase("api"))
            try {
                MonteScreenRecorder.startRecord(method.getName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }


    // Method Name : goLoginPage
    // Method Description: Navigates the webdriver to the login page by opening the specified URL.
    public void goLoginPage() {
        driver.get(getData("url"));
    }

}
