package extensions;

import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

import annotations.BorderColor;
import driver.DriverFactory;
import listeners.BasicListener;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Optional;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

  private EventFiringWebDriver driver = null;

  @Override
  public void beforeEach(ExtensionContext extensionContext){
    driver = new DriverFactory().getDriver();
    BasicListener listener = new BasicListener();
    listener.setColor(getBorderColor(extensionContext));
    driver.register(listener);
    AccessController.doPrivileged((PrivilegedAction<Void>)
        () -> {
        try {
          Field driverField = extensionContext.getTestClass().get().getDeclaredField("driver");
          driverField.setAccessible(true);
          driverField.set(extensionContext.getTestInstance().get(), driver);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
          throw new Error("Something goes wrong with driver field");
        }
        return null;
      }
    );
  }

  @Override
  public void afterEach(ExtensionContext extensionContext){
    if(driver != null){
      driver.quit();
    }
  }

  private String getBorderColor(ExtensionContext extensionContext){
    Optional<BorderColor> optional = findAnnotation(extensionContext.getTestClass(), BorderColor.class);
    return optional.map(BorderColor::value).orElse("red");
  }
}
