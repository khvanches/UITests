package steps.common;

import com.google.inject.Inject;
import driver.DriverFactory;
import driver.IDriverFactory;
import exceptions.DriverNotSupported;
import io.cucumber.java.ru.Пусть;
import utils.GuiceScoped;

public class CommonPagesSteps {

  @Inject
  private DriverFactory driverFactory;
  @Inject
  private GuiceScoped guiceScoped;

  @Пусть("Я открываю браузер {string}")
  public void initBrowser(String browserName) throws DriverNotSupported {
    guiceScoped.browserName = browserName;
    guiceScoped.driver = driverFactory.getDriver();
  }
}
