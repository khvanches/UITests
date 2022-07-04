package listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import waiters.StandardWaiter;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasicListener implements WebDriverListener {
  private WebDriver driver;
  private StandardWaiter waiter = new StandardWaiter(driver);

  public BasicListener(WebDriver driver){
    this.driver = driver;
  }

  @Override
  public void beforeClick(WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; outline: 4px solid red;');", element);
  }

  @Override
  public void afterClick(WebElement element) {
    ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('style', 'background: yellow; outline: 4px solid red;');", element);
  }
}
