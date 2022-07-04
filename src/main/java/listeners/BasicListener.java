package listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import waiters.StandardWaiter;


public class BasicListener implements WebDriverListener {
  private WebDriver driver;
  private StandardWaiter waiter = new StandardWaiter(driver);
  private String color;

  public BasicListener(WebDriver driver){
    this.driver = driver;
  }

  @Override
  public void beforeClick(WebElement element) {
    ((JavascriptExecutor) driver).executeScript(
      String.format("arguments[0].setAttribute('style', 'outline: 4px solid %s;');", color), element);
  }

  @Override
  public void afterClick(WebElement element) {
    ((JavascriptExecutor)driver).executeScript(
      String.format("arguments[0].removeAttribute('style', 'outline: 4px solid %s;');", color), element);
  }

  public void setColor(String color) {
    this.color = color;
  }
}
