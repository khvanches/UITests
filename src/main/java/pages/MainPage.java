package pages;

import annotations.URL;
import org.codehaus.plexus.util.StringUtils;
import org.openqa.selenium.WebDriver;

@URL("/")
public class MainPage extends BasicPageAbs<MainPage> {
  public MainPage(WebDriver driver) {
    super(driver);
  }

}
