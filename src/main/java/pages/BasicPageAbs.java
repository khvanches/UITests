package pages;

import annotations.URL;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import utils.GuiceScoped;
import waiters.StandardWaiter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public abstract class BasicPageAbs<T> {

  protected GuiceScoped guiceScoped;
  protected StandardWaiter waiter;
  protected Actions actions;

  protected final static Logger LOGGER = Logger.getLogger(BasicPageAbs.class.getName());
  public BasicPageAbs(GuiceScoped guiceScoped) {
    this.guiceScoped = guiceScoped;
    PageFactory.initElements(guiceScoped.driver, this);
    this.waiter = new StandardWaiter(guiceScoped.driver);
    this.actions = new Actions(guiceScoped.driver);
  }

  public T open(){
    guiceScoped.driver.get(getBaseUrl() + getPageUrl());

    return (T)page(getClass());
  }

  private String getBaseUrl(){
    return StringUtils.stripEnd(System.getProperty("webdriver.base.url"), "/");
  }

  private String getPageUrl(){
    URL url = getClass().getAnnotation(URL.class);
    if (url != null) {
      return url.value().replaceAll("/+$","");
    }
    return "";
  }

  private <T> T page(Class<T> clazz) {
    try{
      Constructor constructor = clazz.getConstructor(GuiceScoped.class);

      return convertInstanceOfObject(constructor.newInstance(guiceScoped), clazz);
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
    try {
      return clazz.cast(o);
    } catch (ClassCastException e) {
      return null;
    }
  }
}
