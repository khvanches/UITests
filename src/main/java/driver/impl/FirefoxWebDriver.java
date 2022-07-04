package driver.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class FirefoxWebDriver implements IDriver {

  @Override
  public WebDriver newDriver() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments("--no-sandbox")
      .addArguments("--homepage=about:blank");

    firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    firefoxOptions.setHeadless(HEADLESS);
    firefoxOptions.setLogLevel(FirefoxDriverLogLevel.INFO);


    return new FirefoxDriver(firefoxOptions);
  }
}
