package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import pages.CourseDetailsPage;

public class CourseDetailsPageSteps {

  @Inject
  public CourseDetailsPage courseDetailsPage;

  @Тогда("Страница открыта страница {string}")
  public void pageTitleShouldBeSameAs(String expectedName){
    courseDetailsPage.pageTitleShouldBeSameAs(expectedName);
  }

  @Тогда("Страница курса открыта")
  public void pageIsOpened(){
    courseDetailsPage.pageIsOpened();
  }
}
