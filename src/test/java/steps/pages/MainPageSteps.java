package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import pages.MainPage;

import java.util.Date;

public class MainPageSteps {

  @Inject
  public MainPage mainPage;

  @Пусть("Я открываю главную страницу")
  public void openMainPage(){
    mainPage.open();
  }

}
