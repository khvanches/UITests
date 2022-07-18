package pages;

import annotations.URL;
import com.google.inject.Inject;
import utils.GuiceScoped;

@URL("/online")
public class PreparationCoursesPage extends BasicPageAbs<PreparationCoursesPage> {

  @Inject
  public PreparationCoursesPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

}
