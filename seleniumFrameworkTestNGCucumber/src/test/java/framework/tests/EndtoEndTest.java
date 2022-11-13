package framework.tests;


import java.time.Duration;
import java.util.List;
import java.util.stream.Collector;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import framework.commons.DriverManager;
import framework.pom.ApplicationGeneric;
import framework.pom.Homepage;
import framework.pom.JoinRewards;
import framework.pom.LoginPage;
import framework.pom.ProductsPage;
import framework.pom.ProfilePage;
import framework.pom.WelcomePage;
import framework.testsdata.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EndtoEndTest extends DriverManager {
	
	@Test// Test for end 2 end flow
	public void end2endFLow()
	{
		
			
		Homepage homepage= new Homepage(driver);
		homepage.homePageURLVerification(TestData.email);
		homepage.verifyBanner();
		homepage.clickOnJoinRewards();
		
		ApplicationGeneric applicationGeneric= new ApplicationGeneric(driver);
		applicationGeneric.waitForLoaderToDisappear();
		
		JoinRewards joinRewards= new JoinRewards(driver);
		joinRewards.normalRegistration(TestData.email, TestData.firstName, TestData.lastName, TestData.password, TestData.address, TestData.monthofBirth, TestData.yearofBirth, TestData.dateofBirth);
		
		applicationGeneric.waitForLoaderToDisappear();
		
		WelcomePage welcomepage= new WelcomePage(driver);
		welcomepage.verifyWelcomeMessage();
		
		applicationGeneric.signOut();

		LoginPage loginpage= new LoginPage(driver);
		loginpage.login(TestData.email, TestData.password);
		
		applicationGeneric.waitForLoaderToDisappear();
		
		ProfilePage profilePage= new ProfilePage(driver);
		profilePage.verifyProfilePage(TestData.profilePageURL);
		profilePage.verifyUserName(TestData.firstName);
		profilePage.verifyEmail(TestData.email);
		
		
		applicationGeneric.navigateToProducts();
		
		ProductsPage productsPage= new ProductsPage(driver);
		productsPage.searchForProduct(TestData.productSearch);
		productsPage.addProductToCart(TestData.productToAdd);
		
		applicationGeneric.openMiniCart();
		applicationGeneric.verifyProductsInCart(TestData.productToAdd);
		
		
		
		
		
		
	}

}
