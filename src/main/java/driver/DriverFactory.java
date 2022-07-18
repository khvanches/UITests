package driver;

import com.google.inject.Inject;
import driver.impl.ChromeWebDriver;
import driver.impl.FirefoxWebDriver;
import driver.impl.OperaWebDriver;
import exceptions.DriverNotSupported;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.GuiceScoped;

import java.util.Locale;

public class DriverFactory implements IDriverFactory{
  public GuiceScoped guiceScoped;
  @Inject
  public DriverFactory(GuiceScoped guiceScoped) {this.guiceScoped = guiceScoped;}

  @Override
  public EventFiringWebDriver getDriver(){
    switch (guiceScoped.browserName.toLowerCase(Locale.ROOT)) {
      case "chrome" : return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
      case "firefox": return new EventFiringWebDriver(new FirefoxWebDriver().newDriver());
      case "opera": return new EventFiringWebDriver(new OperaWebDriver().newDriver());
      default:
        try {
          throw new DriverNotSupported(guiceScoped.browserName);
        } catch (DriverNotSupported ex) {
          ex.printStackTrace();
          return null;
        }
    }
  }
}
