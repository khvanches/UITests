package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import waiters.StandardWaiter;

import java.util.List;
import java.util.function.BiFunction;

public abstract class BasicPageAbs<T> {

  protected WebDriver driver;
  protected StandardWaiter waiter;

  public BasicPageAbs(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    waiter = new StandardWaiter(driver);
  }

}
