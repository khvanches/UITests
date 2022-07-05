package driver;

import driver.impl.ChromeWebDriver;
import driver.impl.FirefoxWebDriver;
import driver.impl.OperaWebDriver;
import exceptions.DriverNotSupported;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class DriverFactory implements IDriverFactory{
  private String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);

  @Override
  public WebDriver getDriver() throws DriverNotSupported {
    switch (browserType) {
      case "chrome" : return new ChromeWebDriver().newDriver();
      case "firefox": return new FirefoxWebDriver().newDriver();
      case "opera": return new OperaWebDriver().newDriver();
      default:
        try {
          throw new DriverNotSupported(this.browserType);
        } catch (DriverNotSupported ex) {
          ex.printStackTrace();
          return null;
        }
    }
  }
}
