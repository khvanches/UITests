package pages;

import annotations.URL;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import utils.GuiceScoped;

@URL("/")
public class MainPage extends BasicPageAbs<MainPage> {
  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

}
