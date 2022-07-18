package steps.components;

import com.google.inject.Inject;
import components.CourseCardList;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseCardListSteps {

  @Inject
  public CourseCardList courseCardList;

  @Когда("Я выбираю курс {string}")
  public void openCurseByName(String courseName){
    courseCardList.filterByNameGetOne(courseName)
      .clickOnCard();
  }

  @Когда("Я выбираю курс стартующий позже {string}")
    public void chooseCourseWillStartAfterDate(String expectedDate) throws ParseException {
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(expectedDate);
    courseCardList.getCourseStartAfterDate(date)
      .clickOnCard();
  }
}
