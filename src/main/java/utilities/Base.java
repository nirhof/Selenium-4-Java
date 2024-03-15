package utilities;

import helpers.*;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Base {


    // General
    protected static WebDriverWait wait;
    protected static Actions actions;
    protected static SoftAssert softAssert;
    protected static Screen screen;
    protected static String platform;
    protected static String filePath = "./Images/";
    protected static RelativeLocatorHandler relativeLocator;
    protected static ScreenShotHandler screenShot;
    protected static LogHandler logHandler;
    protected static DevTools tool;
    protected static InsecureHandler insecureHandler;
    protected static TabsWindowsHandler tabsWindowsHandler;
    protected static BlockHandler blockHandler;
    protected static PerformanceHandler performanceHandler;
    protected static ConnectionHandler connectionHandler;
    protected static PluginsHandler pluginsHandler;
    protected static TimeZoneHandler timeZoneHandler;
    protected static UserAgentHandler userAgentHandler;


    // Web
    protected static WebDriver driver;

    // Database
    protected static Connection con;
    protected static Statement stmt;
    protected static ResultSet rs;


    // Page Objects - Web

    protected static pageObjects.atidStore.StoreHome atidstoreHome;
    protected static pageObjects.atidStore.TopMenu topMenu;
    protected static pageObjects.atidStore.StorePage storePage;
    protected static pageObjects.atidStore.AboutPage aboutPage;
    protected static pageObjects.atidStore.Products products;
    protected static pageObjects.atidStore.CartPage cartPage;

    protected static pageObjects.atidStore.CheckoutPage checoutPage;


}
