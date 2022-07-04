package driver;

import exceptions.DriverNotSupported;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
  WebDriver getDriver() throws DriverNotSupported;
}
