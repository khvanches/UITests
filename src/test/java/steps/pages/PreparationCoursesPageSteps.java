package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import pages.PreparationCoursesPage;


public class PreparationCoursesPageSteps {

  @Inject
  public PreparationCoursesPage preparationCoursesPage;

  @Пусть("Я открываю страницу подготовительных курсов")
  public void openMainPage(){
    preparationCoursesPage.open();
  }
}
