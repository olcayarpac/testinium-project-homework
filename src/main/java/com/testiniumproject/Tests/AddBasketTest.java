package com.testiniumproject.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.ThreadLocalRandom;


public class AddBasketTest {

  private WebDriver driver;

  public AddBasketTest(WebDriver driver) {
    this.driver = driver;
  }

  public void addRandomItemToBasket(){

    // first get the number of products in search page
    int totalItemNumber = driver.findElements(By.xpath("//ul[contains(@class,'products-container')]/*")).size();
    // generate a random number to choose a random product in searh page
    int randomNum = ThreadLocalRandom.current().nextInt(0, totalItemNumber);

    // xpath for 'Sepete Ekle' button of choosen random item in search list
    String xpathAddBasket = String.format("//ul[contains(@class,'products-container')]/li[%s]//div[contains(@class,'buy-now-button')]", String.valueOf(randomNum));
    WebElement addBasketDiv = driver.findElement(By.xpath(xpathAddBasket));
    
    String xpathPrice = String.format("//ul[contains(@class,'products-container')]/li[%s]/a/div/div/div[1]/div[2]/div/div[1]/div[2]/div[2]/p", String.valueOf(randomNum));
    String priceOfItem = driver.findElement(By.xpath(xpathPrice)).getText();
    
    // js script to click element
    // add basket div is not visible by default, unless the mouse is on the product cart
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].setAttribute('style', 'display:block;')", addBasketDiv);
    js.executeScript("arguments[0].click();", addBasketDiv);

    // Selenium could not find any element in basket right after clicking the add basket button,
    // I solved this problem with a little delay
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // compare the actual price and price in basket
    // get element that contains price
    String priceInBasket = driver.findElement(By.xpath("//html/body/div[3]/div[3]/div/div[4]/div[3]/div/div/div/div[2]/div[2]/ul/li/div[2]/p[3]/span[2]")).getText();
   
    if(!priceInBasket.equals(priceOfItem)){
      System.out.println("Actual product price and price in basket are not same");
    }
    else {
      System.out.println("Actual product price and price in basket are same");
    }
  }

}
