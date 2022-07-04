package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.CourseDetailsPage;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static utils.DateParsing.getDateFromString;

public class CourseCardList extends BasicComponentAbs<CourseCardList> {
  private WebElement actualCourse;
  private Actions actions;

  public CourseCardList(WebDriver driver) {
    super(driver);
    actions = new Actions(driver);
  }

  @FindBy(css = ".lessons__new-item")
  private List<WebElement> courses;

  public List<WebElement> filterByName(String filter){
    List<WebElement> result = courses.stream()
      .filter(x -> getCourseName(x).contains(filter))
      .collect(Collectors.toList());
    if (!result.isEmpty()){
      return result;
    } else {
      try {
        throw new Exception(String.format("There are no any course that contains %s in the name", filter));
      } catch (Exception ex) {
        ex.printStackTrace();
        return null;
      }
    }
  }

  public WebElement filterByNameGetOne(String filter){
    return filterByName(filter).stream()
      .findFirst()
      .orElse(null);
  }

  public CourseCardList getEarliest(){
    actualCourse = courses.stream()
      .filter(x1 -> getDate(x1) != null)
      .reduce((x1,x2) -> getDate(x1).before(getDate(x2)) ? x1 : x2)
      .orElse(null);
    return this;
  }

  public CourseCardList getOldest(){
    actualCourse = courses.stream()
      .filter(x1 -> getDate(x1) != null)
      .reduce((x1,x2) -> getDate(x1).after(getDate(x2)) ? x1 : x2)
      .orElse(null);
    return this;
  }

  public CourseDetailsPage clickOnCard(){
    if (actualCourse != null) {
       actualCourse.click();
      return new CourseDetailsPage(driver);
    } else {
      try {
        throw new Exception(String.format("Actual course wasn't chosen! It's possible to click on all courses!"));
      } catch (Exception ex) {
        ex.printStackTrace();
        return null;
      }
    }
  }

  public CourseDetailsPage actionsClickOnCard(){
    return actionsClickOnCard("red");
  }

  public CourseDetailsPage actionsClickOnCard(String color){

    if (actualCourse != null) {
        actions.moveToElement(actualCourse)
          .clickAndHold()
          .perform();
        colorBorder(actualCourse, color);
        actions.pause(2000)
          .perform();
        unColorBorder(actualCourse);
        actions.release(actualCourse)
          .perform();
      return new CourseDetailsPage(driver);
    } else {
      try {
        throw new Exception(String.format("Actual course wasn't chosen! It's possible to click on all courses!"));
      } catch (Exception ex) {
        ex.printStackTrace();
        return null;
      }
    }
  }

  private String getCourseName(WebElement webElement) {
    return webElement.findElement(By.className(".lessons__new-item-title")).getText();
  }

  public Date getDate(WebElement webElement){
    WebElement lessonStart;
    WebElement lessonTime;

    if (isElementPresentInElement(webElement, By.className("lessons__new-item-start"))) {
      lessonStart = webElement.findElement(By.className("lessons__new-item-start"));
    } else {
      lessonStart = null;
    }

    if (isElementPresentInElement(webElement, By.className("lessons__new-item-time"))) {
      lessonTime = webElement.findElement(By.className("lessons__new-item-time"));
    } else {
      lessonTime = null;
    }

    if (lessonStart != null) {
      return getDateFromString(lessonStart.getText().substring(2));
    } else {
      if (lessonTime != null) {
        String str = lessonTime.getText();
        if (str.matches(".*\\d.*")) {
          return getDateFromString(str);
        } else {
          return null;
        }
      } else {
        return null;
      }
    }
  }
}
