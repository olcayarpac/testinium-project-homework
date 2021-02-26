package com.testiniumproject.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class LoginPage {
  
  private WebDriver driver;
  private String loginPageUrl = "https://www.gittigidiyor.com/uye-girisi";

  public LoginPage(WebDriver driver){
    this.driver = driver;
    openLoginPage();
  }

  public void openLoginPage(){
    driver.get(loginPageUrl);
  }

  public void doLogin(String email, String password) {
    System.out.println("Login process started");
    openLoginPage();
    // fill the login form and submit
    driver.findElement(By.name("kullanici")).sendKeys(email);
    driver.findElement(By.name("sifre")).sendKeys(password);
    driver.findElement(By.name("formlogin")).submit();
  }

  public boolean isLoggedIn(){
    this.openLoginPage();
    // if there is no formLogin element in page, it means we are logged in
    if (driver.findElements(By.name("formlogin")).isEmpty()){
      return true;
    }
    else return false;
  }

}
