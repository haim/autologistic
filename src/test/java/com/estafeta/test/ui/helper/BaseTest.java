package com.estafeta.test.ui.helper;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.GlobalTextReport;
import com.estafeta.test.ui.data.users.UserData;
import com.estafeta.test.ui.listeners.AmtListener;
import com.estafeta.test.ui.preconditions.LoginRequired;
import com.estafeta.test.ui.preconditions.PrepareTest;
import com.estafeta.test.ui.stepdefinitions.HomePageSteps;
import com.estafeta.test.ui.stepdefinitions.LoginPageSteps;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static org.openqa.selenium.remote.BrowserType.CHROME;

/**
 * @author dnikiforov
 * @project estafeta
 */
@Listeners({GlobalTextReport.class, AmtListener.class})
public class BaseTest {

  protected LoginPageSteps loginPageSteps;
  protected HomePageSteps homePageSteps;

  private Log log = LogFactory.getLog(this.getClass());

  @BeforeClass
  public void initSettings() {}

  @BeforeMethod
  public void setUp() throws IOException {}

  @BeforeTest
  public void beforeTest() {
    init();
  }

  @AfterTest
  public void tearDown() {
    SelenideLogger.removeListener("AllureSelenide");
    WebDriverRunner.closeWebDriver();
  }

  @PrepareTest
  public void setUpConfig(Method method) throws InterruptedException {
    //    clearBrowserCache();
    loginPageSteps = new LoginPageSteps();

    if (method.isAnnotationPresent(LoginRequired.class)) {
      String login = method.getAnnotation(LoginRequired.class).login();
      String password = method.getAnnotation(LoginRequired.class).password();
      String company = method.getAnnotation(LoginRequired.class).company();
      homePageSteps =
          loginPageSteps
              .open()
              .loginAs(
                  new UserData.Builder().login(login).password(password).company(company).build(),
                  true);
    }
  }

  public void init() {
    SelenideLogger.addListener(
        "AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));

    //    Configuration.remote = "http://192.168.0.42:4444/wd/hub"; // remote
    //        String RemoteHost = Configuration.remote = "http://localhost:4444/wd/hub"; // Selenoid
    // local
    //    Configuration.baseUrl = "https://amt1.estafeta.org";
    //        Configuration.baseUrl = "https://autotest1.estafeta.org";
    //        Configuration.baseUrl = "https://95.158.40.114:8181";
    //    String baseUrl = Configuration.baseUrl = "https://amt2.estafeta.org";
    Configuration.baseUrl = "https://autotest1.estafeta.org";
    String browserType = Configuration.browser = CHROME;
    Configuration.captureJavascriptErrors = true;
    Configuration.fastSetValue = false;
    Configuration.timeout = 10000;
    Configuration.reportsFolder = "target/reports";
    Configuration.browserPosition = "30x0"; // for two monitors
    Configuration.browserSize = "1920x1200";
    Configuration.startMaximized = false;
    Configuration.clickViaJs = true; // fast click
    DesiredCapabilities capabilities = new DesiredCapabilities();
    //        capabilities.setBrowserName("internet explorer");
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("enableVideo", false); // recod video each test
    Configuration.browserCapabilities = capabilities;
  }

  @Attachment(type = "image/png")
  public byte[] screenshot() throws IOException {
    File screenshot = Screenshots.getLastScreenshot();
    return Files.toByteArray(screenshot);
  }
}
