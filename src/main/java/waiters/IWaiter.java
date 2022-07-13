package waiters;

import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;

public interface IWaiter {
  long IMPLICITLY_WAIT_SECOND = Integer.parseInt(System.getProperty(
      "webdriver.timeouts.implicitlywait", "5000")) / 1000;

  boolean waitForCondition(ExpectedCondition condition);
}
