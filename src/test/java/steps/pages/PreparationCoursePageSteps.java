package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import pages.PreparationCoursePage;

public class PreparationCoursePageSteps {

  @Inject
  public PreparationCoursePage preparationCoursePage;

  @Тогда("Страница подготовительного курса открыта")
  public void pageShouldBeOpened(){
    preparationCoursePage.pageIsOpened();
  }
}
