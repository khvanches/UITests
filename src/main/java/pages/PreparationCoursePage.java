package pages;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GuiceScoped;

public class PreparationCoursePage extends BasicPageAbs<PreparationCoursesPage> {

  @FindBy(css = ".preparatory-intro__title")
  private WebElement courseTitle;

  @Inject
  public PreparationCoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public PreparationCoursePage pageIsOpened() {
    assertThat(courseTitle != null)
      .as("Current page doesn't contain any title..")
      .isTrue();
    return this;
  }
}
