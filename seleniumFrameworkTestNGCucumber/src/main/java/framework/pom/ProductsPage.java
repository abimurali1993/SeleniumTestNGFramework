package framework.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.commons.BaseActions;

public class ProductsPage extends BaseActions {
	
	WebDriver driver;
	public ProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id = "react-form-field-search")
	WebElement searchBox;
	
	@FindBy(css = ".clickable")
	WebElement searchBtn;
	
	@FindBy(className = "product-card")
	List<WebElement> productsCard;
	
	@FindBy(css = "li[class*='next'] a")
	WebElement nextBtn;
	
	
	By productName=By.className("figure-caption");
	By products=By.className("products__card--figure");
	By addToCartBtn= By.className("btn-primary");
	
	public void searchForProduct(String productSearch )
	{
		sendText(searchBox, productSearch);
		normalClick(searchBtn);
	}
	
	public void addProductToCart(String productToAdd)
	{

		waitForAllElementPresence(10, products);
		
		do
		{
		productsCard.stream().filter(temp-> temp.findElement(productName).getText().contains(productToAdd))
		.filter(temp->temp.findElements(addToCartBtn).size()>0)
		.forEach(temp->temp.findElement(addToCartBtn).click());
		
		normalClick(nextBtn);
		waitForAllElementPresence(10, products);
		}while (nextBtn.getAttribute("aria-disabled").equals("false"));
	}
	
	
	
	
	
	
	


}
