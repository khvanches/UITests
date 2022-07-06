package components;

import org.openqa.selenium.*;
import pages.BasicPageAbs;

import java.util.function.BiFunction;

public abstract class BasicComponentAbs<T> extends BasicPageAbs<T>  {

  public BasicComponentAbs(WebDriver driver) {
    super(driver);
  }

  protected boolean isElementPresentedInElement(WebElement webElement, By by){
    try{
      webElement.findElement(by);
      return true;
    }
    catch(NoSuchElementException e){
      return false;
    }
  }

  protected BiFunction<WebDriver, WebElement, T> scrollToElement = (WebDriver driver, WebElement element) -> {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    return (T) this;
  };
}
