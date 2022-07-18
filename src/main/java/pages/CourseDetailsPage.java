package pages;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import utils.GuiceScoped;


public class CourseDetailsPage extends BasicPageAbs<CourseDetailsPage> {



  @Inject
  public CourseDetailsPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public CourseDetailsPage pageTitleShouldBeSameAs(String expectedPageName) {
    String pageName = getPageName();
    assertThat(expectedPageName.equals(pageName))
      .as(String.format("Wrong course page has been opened! Expected page name is: %s   Current page name is: %s",
        expectedPageName, pageName))
      .isTrue();;
    return this;
  }

  private String getPageName() {
    if(waiter.elementIsPresented(By.className("course-header2__title"))) {
      return guiceScoped.driver.findElement(By.className("course-header2__title")).getText();
    } else if(waiter.elementIsPresented(By.cssSelector("[field='tn_text_1613574457579']"))) {
      return guiceScoped.driver.findElement(By.cssSelector("[field='tn_text_1613574457579']")).getText();
    } else {
      try {
        throw new Exception("Course name hasn't been found on a page! Please, recheck page and elements");
      } catch (Exception ex) {
        ex.printStackTrace();
        return null;
      }
    }
  }

  public CourseDetailsPage pageIsOpened() {
    assertThat(getPageName() != null)
      .as(String.format("Current page doesn't contains any title that required for Course page"))
      .isTrue();
    return this;
  }
}
