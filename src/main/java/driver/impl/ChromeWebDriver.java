package driver.impl;

import exceptions.DriverNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.logging.Level;

public class ChromeWebDriver implements IDriver {

  @Override
  public WebDriver newDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--no-sandbox")
      .addArguments("--homepage=about:blank");

    chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    chromeOptions.setHeadless(HEADLESS);

    LoggingPreferences loggingPreference = new LoggingPreferences();
    loggingPreference.enable(LogType.PERFORMANCE, Level.INFO);
    chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, loggingPreference);

    try {
      downloadLocalWebDriver(DriverManagerType.CHROME);
    } catch (DriverNotSupported ex) {
      ex.printStackTrace();
    }

    return new ChromeDriver(chromeOptions);
  }
}
