package waiters;

import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

public interface IWaiter {
  Duration IMPLICITLY_WAIT_SECOND = Duration.ofSeconds(Integer.parseInt(System
      .getProperty("webdriver.timeouts.implicitlywait", "3000")) / 1000);

  boolean waitForCondition(ExpectedCondition condition);
}
