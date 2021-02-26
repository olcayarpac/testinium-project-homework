package com.testiniumproject.Pages;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class SearchPage {

  private WebDriver driver;

  public SearchPage(WebDriver driver) {
    this.driver = driver;
  }

  public void search(String itemToSearch) {
    driver.findElement(By.name("k")).sendKeys(itemToSearch);
    driver.findElement(By.name("k")).submit();
  }

  public void goSearchResultsPageTwo() {

    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    WebElement pageTwoButton = driver.findElement(By.xpath("//div[contains(@class,'pager')]/ul/li[2]/a"));


    
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // at first I was trying to click the button with "pageTwoButton.click()", however it didn't 
    // worked properly because of overlapping html elements. I changed the clickability of overlapping element
    // by using a js script. Below code was fixing that problem
    /*
    WebElement elementToHide = driver.findElement(By.className("policy-alert"));
    js.executeScript("arguments[0].setAttribute('style', 'pointer-events:none;')", elementToHide);
    */

    // then I used another js script to click pageTwoButton, which is more precise solution for that
    js.executeScript("arguments[0].click();", pageTwoButton);

  }

  public int getCurrentPageNumber() {
    return Integer.parseInt(driver.findElement(By.xpath("//li[@class='selected']/a")).getText());
  }

}
