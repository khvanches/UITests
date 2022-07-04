import components.CourseCardList;
import exceptions.DriverNotSupported;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class Otus_Test {

  public WebDriver driver;

//  @Test
//  public void click_earliest_course_test() throws DriverNotSupported, InterruptedException {
//    MainPage mainPage = new MainPage(driver);
//    mainPage.open();
//    CourseCardList cards = new CourseCardList(driver);
//    cards.getEarliest()
//      .clickOnCard();
//    Thread.sleep(1000);
//  }

  @Test
  public void actions_open_oldest_course_test() throws InterruptedException {
    Actions actions = new Actions(driver);
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    CourseCardList cards = new CourseCardList(driver);
    cards.getOldest()
      .actionsClickOnCard("blue");
    Thread.sleep(1000);
  }
}
