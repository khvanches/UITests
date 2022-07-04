package exceptions;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverNotSupported extends Exception {

    public DriverNotSupported(DriverManagerType driverType) {
        super(String.format("Browser type %s doesn't supported by our tests", driverType.name()));
    }

  public DriverNotSupported(String driverType) {
    super(String.format("Browser type %s doesn't supported by our tests", driverType));
  }
}
