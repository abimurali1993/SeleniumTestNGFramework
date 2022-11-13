package framework.tests;


import org.testng.annotations.Test;

import framework.commons.DriverManager;
import framework.commons.ExtentReporterNG;
import framework.pom.ApplicationGeneric;
import framework.pom.Homepage;
import framework.pom.LoginPage;
import framework.pom.ProductsPage;
import framework.pom.ProfilePage;
import framework.testsdata.TestData;

public class LoginAddToCartFlowTest extends DriverManager {
	@Test
	public void loginaddToCartTest()
	{
		

						
		Homepage homepage= new Homepage(driver);
		homepage.homePageURLVerification(TestData.homePageURL);
		homepage.verifyBanner();
		homepage.clickOnLoginBtn();
		

		LoginPage loginpage= new LoginPage(driver);
		loginpage.login(TestData.email, TestData.password);
		
		ApplicationGeneric applicationGeneric= new ApplicationGeneric(driver);
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
		applicationGeneric.signOut();
		
		
		
	}

}
