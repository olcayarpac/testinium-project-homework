package com.testiniumproject;

import com.testiniumproject.Pages.HomePage;
import com.testiniumproject.Pages.LoginPage;
import com.testiniumproject.Pages.SearchPage;
import com.testiniumproject.Pages.BasketPage;

import com.testiniumproject.Tests.AddBasketTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class App {

  public static void main(String[] args) {

    // email and password that will be used for login 
    String email = "denemeolcay00@gmail.com";
    String password = "deneme123";

    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();


    HomePage homePage = new HomePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    SearchPage searchPage = new SearchPage(driver);

    // check whether home page was opened or not
    homePage.checkHomePageOpened();

    // do login
    loginPage.doLogin(email, password);
    //check login status
    if(!loginPage.isLoggedIn()){ 
      System.out.println("Login process failed!"); 
      }
     
    else { 
      System.out.println("Login successful");
    }
    
    // search "bilgisayar"
    searchPage.search("bilgisayar");
    // move to second page
    searchPage.goSearchResultsPageTwo();
    
    // check if the moving was successful or not
    if (searchPage.getCurrentPageNumber() != 2){
      System.out.println("Second search page could not opened!");

    }
    else {
      System.out.println("Second search page opened");
    }

    AddBasketTest addBasketTest = new AddBasketTest(driver);
    // choose a random item and add it into basket
    addBasketTest.addRandomItemToBasket();

    BasketPage basketPage = new BasketPage(driver);
    // open basket page
    basketPage.openBasket();
    // increment product amount in basket
    basketPage.incrementInBasket();
    // clear the basket 
    basketPage.clearBasket();
  }
}
