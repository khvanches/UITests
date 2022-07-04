package driver.impl;

import exceptions.DriverNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

public interface IDriver {
  boolean HEADLESS = Boolean.parseBoolean(System.getProperty("webdriver.headless"));

  public WebDriver newDriver();

  default void downloadLocalWebDriver(DriverManagerType driverManagerType) throws DriverNotSupported {

    String browserVersion = System.getProperty("browser.version", "");

    if (!browserVersion.isEmpty()) {

      switch (driverManagerType) {
        case CHROME:
          WebDriverManager.chromedriver().config().setChromeDriverVersion(browserVersion);
          break;
        case OPERA:
          WebDriverManager.operadriver().config().setOperaDriverVersion(browserVersion);
          break;
        case FIREFOX:
          WebDriverManager.firefoxdriver().config().setFirefoxVersion(browserVersion);
          break;
        default:
          throw new DriverNotSupported(driverManagerType);
      }
    }

    WebDriverManager.getInstance(driverManagerType).setup();
  }
}
