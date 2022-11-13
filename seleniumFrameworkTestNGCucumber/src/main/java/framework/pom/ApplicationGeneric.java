package framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import framework.commons.BaseActions;

public class ApplicationGeneric extends BaseActions {

	WebDriver driver;

	public ApplicationGeneric(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a[data-fun*='SignOut']")
	WebElement signOutBtn;
	
	@FindBy(id ="navbarDropdown0" )
	WebElement productsMenu;
	
	@FindBy(id="mini-cart")
	WebElement cartIcon;
	
	@FindBy(className = "minicart-products")
	WebElement miniCartProducts;
	
	@FindBy(className = "product-title")
	WebElement productTitle;

	By loader = By.className("pie");

	public void waitForLoaderToDisappear() {
		waitForElementInvisible(15, loader);
	}

	public void signOut() {
		normalClick(signOutBtn);
	}
	
	
	public void navigateToProducts()
	{
		normalClick(productsMenu);

	}
	
	public void  openMiniCart()
	{
		normalClick(cartIcon);
	}
	
	public void verifyProductsInCart(String productToAdd)
	{
		waitForElementPresence(10, miniCartProducts);
		softAssertTrue(productTitle, productToAdd);
	}
}
