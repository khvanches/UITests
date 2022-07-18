package data;

public enum BrowserData {
  CHROME("chrome");

  private String name;

  BrowserData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
