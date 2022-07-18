package steps.components;

import com.google.inject.Inject;
import components.PreparationCoursesList;
import io.cucumber.java.ru.Когда;

public class PreparationCoursesListSteps {

  @Inject
  public PreparationCoursesList preparationCoursesList;

  @Когда("Я выбираю самый дешевый курс")
  public void theCheapestCourseShouldBeOpen(){
    preparationCoursesList.findCheapestCourse();
  }

  @Когда("Я выбираю самый дорогой курс")
  public void theMostExpensiveCourseShouldBeOpen(){
    preparationCoursesList.findMostExpensiveCourse();
  }
}
