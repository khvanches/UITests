package extensions;

import driver.DriverFactory;
import listeners.BasicListener;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;


import java.lang.reflect.Field;


public class UIExtension implements BeforeEachCallback, AfterEachCallback {

  private WebDriver driver = null;

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    driver = new DriverFactory().getDriver();
    driver = new EventFiringDecorator(new BasicListener(driver)).decorate(driver);
    try {
      Field driverField = extensionContext.getTestClass().get().getDeclaredField("driver");
      driverField.setAccessible(true);
      driverField.set(extensionContext.getTestInstance().get(), driver);
      } catch (NoSuchFieldException ex) {
        throw new Error("Something goes wrong with driver field");
      }
    }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    if(driver != null){
      driver.close();
      driver.quit();
    }
  }
}
