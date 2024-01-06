package com.test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonTest {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "F:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		//Navigate to https://www.amazon.in/ 
		driver.get("https://www.amazon.in/");
		
		//Verify landing on the correct page
		wait.until(ExpectedConditions.titleContains("Amazon.in"));
		if (driver.getTitle().contains("Amazon.in")) {
		    System.out.println("Landed on the correct page.");
		} else {
		    System.out.println("Not on the correct page.");
		}
		
		//Print the URL and Title of the Page
		System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());
        
        //Search for "mobile"
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("mobile");
        searchBox.submit();

        //Select 4 stars under customer review filter section
        WebElement starsFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@aria-label='4 Stars & Up']")));
        starsFilter.click();

        //Select the price range between ₹10,000 - ₹20,000
        WebElement priceRangeFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'₹10,000 - ₹20,000')]")));
        priceRangeFilter.click();
        
        //Click on the first search result
        WebElement firstSearchResult = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img[data-image-index='1']")));
        firstSearchResult.click();
        
        //Add the phone to the cart
        Set<String> ids = driver.getWindowHandles();
        Iterator<String> it = ids.iterator();
        String parentId = it.next();
        String childId = it.next();
        driver.switchTo().window(childId);
//        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        WebElement addToCartButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add-to-cart-button")));
        addToCartButton.click();
        
        //Click on the Go to cart button
        WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-labelledby='attach-sidesheet-view-cart-button-announce']")));
        viewCartButton.click();
                
	}
}
