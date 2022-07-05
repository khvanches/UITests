package pages;

import org.codehaus.plexus.util.StringUtils;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasicPageAbs<MainPage> {
  private final String site = StringUtils.stripEnd(System.getProperty("webdriver.base.url"), "/");


  public MainPage(WebDriver driver) {
    super(driver);
  }

  public void open() {
    driver.get(site);
  }
}
