package listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;


public class BasicListener implements WebDriverListener {
  private WebDriver driver;
  private String color;

  public BasicListener(WebDriver driver){
    this.driver = driver;
  }

  @Override
  public void beforeClick(WebElement element) {
    ((JavascriptExecutor) driver).executeScript(
        String.format("arguments[0].setAttribute('style', 'outline: 4px solid %s;');", color), element);
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].addEventListener(\"click\", () => {arguments[0].removeAttribute('style', 'outline');} )", element);
  }

  public void setColor(String color) {
    this.color = color;
  }
}
