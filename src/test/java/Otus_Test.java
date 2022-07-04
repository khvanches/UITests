import annotations.BorderColor;
import components.CourseCardList;
import exceptions.DriverNotSupported;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
@BorderColor("blue")
public class Otus_Test {

  public WebDriver driver;

  @Test
  public void click_earliest_course_test() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    CourseCardList cards = new CourseCardList(driver);
    cards.getEarliest()
      .clickOnCard();
  }

  @Test
  public void actions_open_oldest_course_test() {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    CourseCardList cards = new CourseCardList(driver);
    cards.getOldest()
      .actionsClickOnCard();
  }
}
