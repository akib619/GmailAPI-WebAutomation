package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import utils.ConfigReader;
import utils.Config;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected Browser browser;
    protected String baseUrl;

    @BeforeMethod
    public void setUp() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
        
        baseUrl = ConfigReader.readJson("src/main/java/resources/config.json", Config.class).getUrl();
        browser.goTo(baseUrl);

        browser.waitForPageToLoad();
    }

    @AfterMethod
    public void tearDown() {
        if (browser.isStarted()) {
            browser.quit();
        }
    }
}