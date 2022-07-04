package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import waiters.StandardWaiter;

import java.util.List;

public abstract class BasicPageAbs<T> {

  protected WebDriver driver;
  protected StandardWaiter waiter;

  public BasicPageAbs(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    waiter = new StandardWaiter(driver);
  }

  protected WebElement findElement(By locator) {
    return driver.findElement(locator);
  }

  protected List<WebElement> findElements(By locator) {
    return driver.findElements(locator);
  }
}
