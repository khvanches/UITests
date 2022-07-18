package utils;

import com.google.inject.AbstractModule;
import data.BrowserData;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped extends AbstractModule {

  public String browserName;
  public WebDriver driver;

}
