package hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import utils.GuiceScoped;

public class Hooks {

  @Inject
  private GuiceScoped guiceScoped;

  @After
  public void afterScenario(){
    if (guiceScoped.driver != null) {
      guiceScoped.driver.quit();
    }
  }
}
