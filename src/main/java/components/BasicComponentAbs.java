package components;

import org.openqa.selenium.*;
import pages.BasicPageAbs;

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
}
