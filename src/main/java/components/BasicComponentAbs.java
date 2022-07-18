package components;

import org.openqa.selenium.*;
import pages.BasicPageAbs;
import utils.GuiceScoped;

import java.util.function.BiFunction;

public abstract class BasicComponentAbs<T> extends BasicPageAbs<T>  {

  public BasicComponentAbs(GuiceScoped guiceScoped) {
    super(guiceScoped);
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
