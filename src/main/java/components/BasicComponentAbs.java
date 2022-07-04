package components;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.apache.tools.ant.taskdefs.Java;
import org.openqa.selenium.*;
import pages.BasicPageAbs;

public abstract class BasicComponentAbs<T> extends BasicPageAbs<T>  {

  public BasicComponentAbs(WebDriver driver) {
    super(driver);
  }

  protected boolean isElementPresentInElement(WebElement webElement, By by){
    try{
      webElement.findElement(by);
      return true;
    }
    catch(NoSuchElementException e){
      return false;
    }
  }

  public T colorBorder(WebElement element, String color) {
    ((JavascriptExecutor) driver).executeScript(
      String.format("arguments[0].setAttribute('style', 'border: 4px solid %s;');", color), element);
    return (T)this;
  }

  public T unColorBorder(WebElement element) {
    String color = element.getCssValue("border-color");
    ((JavascriptExecutor)driver).executeScript(
      String.format("arguments[0].removeAttribute('style', 'border: 4px solid %s;');", color), element);
    return (T)this;
  }
}
