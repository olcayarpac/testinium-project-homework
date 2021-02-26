package com.testiniumproject.Pages;
import org.openqa.selenium.WebDriver;

public class HomePage {
  
  private WebDriver driver;
  private String homePageUrl = "https://www.gittigidiyor.com";

  public HomePage(WebDriver driver){
    this.driver = driver;
    openHomePage();
  }

  public void openHomePage(){
    driver.get(homePageUrl);
  }

  public void checkHomePageOpened(){

    String url = driver.getCurrentUrl();
    if (url.equals(homePageUrl)){
      System.out.println("Home opened");
    }
  }
 
}
