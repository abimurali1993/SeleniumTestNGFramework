package framework.tests;


import org.testng.annotations.Test;

import framework.commons.DriverManager;
import framework.pom.ApplicationGeneric;
import framework.pom.ProductsPage;
import framework.testsdata.TestData;

public class VerifyAddtoCart extends DriverManager {
	@Test(groups = "regression", dependsOnMethods = "framework.tests.VerifyLogin.loginTest")
	public void loginaddToCartTest()
	{
		

		
		VerifyLogin login= new VerifyLogin();
		login.loginTest();	
	
		ApplicationGeneric applicationGeneric= new ApplicationGeneric(driver);
		applicationGeneric.navigateToProducts();
		
		ProductsPage productsPage= new ProductsPage(driver);
		productsPage.searchForProduct(TestData.productSearch);
		productsPage.addProductToCart(TestData.productToAdd);
		
		applicationGeneric.openMiniCart();
		applicationGeneric.verifyProductsInCart(TestData.productToAdd);
		applicationGeneric.signOut();
		
	}

}
