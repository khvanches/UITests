package listeners;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;


public class BasicListener implements WebDriverEventListener {
  private String color;

  @Override
  public void beforeClickOn(WebElement element, WebDriver driver) {
    ((JavascriptExecutor) driver).executeScript(
        String.format("arguments[0].setAttribute('style', 'outline: 4px solid %s;');", color), element);
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].addEventListener(\"click\", () => {arguments[0].removeAttribute('style', 'outline');} )", element);
  }

  @Override
  public void afterClickOn(WebElement element, WebDriver driver) {
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public void beforeAlertAccept(WebDriver webDriver) {

  }

  @Override
  public void afterAlertAccept(WebDriver webDriver) {

  }

  @Override
  public void afterAlertDismiss(WebDriver webDriver) {

  }

  @Override
  public void beforeAlertDismiss(WebDriver webDriver) {

  }

  @Override
  public void beforeNavigateTo(String s, WebDriver webDriver) {

  }

  @Override
  public void afterNavigateTo(String s, WebDriver webDriver) {

  }

  @Override
  public void beforeNavigateBack(WebDriver webDriver) {

  }

  @Override
  public void afterNavigateBack(WebDriver webDriver) {

  }

  @Override
  public void beforeNavigateForward(WebDriver webDriver) {

  }

  @Override
  public void afterNavigateForward(WebDriver webDriver) {

  }

  @Override
  public void beforeNavigateRefresh(WebDriver webDriver) {

  }

  @Override
  public void afterNavigateRefresh(WebDriver webDriver) {

  }

  @Override
  public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {

  }

  @Override
  public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {

  }

  @Override
  public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

  }

  @Override
  public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

  }

  @Override
  public void beforeScript(String s, WebDriver webDriver) {

  }

  @Override
  public void afterScript(String s, WebDriver webDriver) {

  }

  @Override
  public void beforeSwitchToWindow(String s, WebDriver webDriver) {

  }

  @Override
  public void afterSwitchToWindow(String s, WebDriver webDriver) {

  }

  @Override
  public void onException(Throwable throwable, WebDriver webDriver) {

  }

  @Override
  public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

  }

  @Override
  public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

  }

  @Override
  public void beforeGetText(WebElement webElement, WebDriver webDriver) {

  }

  @Override
  public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

  }
}
