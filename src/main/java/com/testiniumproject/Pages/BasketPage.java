package com.testiniumproject.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.support.ui.Select;

public class BasketPage {

  private WebDriver driver;
  private String basketUrl = "https://www.gittigidiyor.com/sepetim";

  public BasketPage(WebDriver driver) {
    this.driver = driver;
  }

  public void openBasket(){
    driver.get(basketUrl);
  }

  public void incrementInBasket(){

    // get the drop down list in the basket page
    Select amountSelector = new Select(driver.findElement(By.xpath("//select[@class='amount']")));
    
    // Test will fail when only 1 product is available
    amountSelector.selectByValue("2");

    // the selected value is not being updated instantly, so a little delay is needed
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // refresh the page and check if amount is updated
    driver.get(basketUrl);
    amountSelector = new Select(driver.findElement(By.xpath("//select[@class='amount']")));
    String amountInBasket =amountSelector.getFirstSelectedOption().getAttribute("value");
    
    System.out.println("Product amount in basket: " + amountInBasket);

    if (amountInBasket.equals("2")){
      System.out.println("There are two products in basket");
    }
    else{
      System.out.println("There are not two products in basket. Selecting did not work.");
    }

  }

  public void clearBasket(){
    System.out.println("Deleting the product from basket...");

    // get the element with classname 'btn-delete'
    WebElement deleteButton = driver.findElement(By.xpath("//a[contains(@class,'btn-delete')]"));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", deleteButton);

    // to continue without problems, a small delay was needed
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Get the elements with className "amount" and check the size. if there is not element 
    // with classname "amount" in the page, it means that there are no products in the basket page
    if(driver.findElements(By.className("amount")).size() == 0){
      System.out.println("Basket is empty. Product was deleted from basket");
    }
    else {
      System.out.println("Basket is not empty! Deleting was failed!");
    }

  }

}
