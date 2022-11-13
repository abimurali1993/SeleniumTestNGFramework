package framework.tests;


import org.testng.annotations.Test;

import framework.commons.DriverManager;
import framework.pom.ApplicationGeneric;
import framework.pom.ProfilePage;
import framework.testsdata.TestData;

public class VerifyProfilePage extends DriverManager {
	@Test(groups = {"sanity","regression"}, dependsOnMethods = "framework.tests.VerifyLogin.loginTest")
	public void profilePageTest()
	{

		
		VerifyLogin login= new VerifyLogin();
		login.loginTest();
		
		ApplicationGeneric applicationGeneric= new ApplicationGeneric(driver);
		applicationGeneric.waitForLoaderToDisappear();
		
		ProfilePage profilePage= new ProfilePage(driver);
		profilePage.verifyProfilePage(TestData.profilePageURL);
		profilePage.verifyUserName(TestData.firstName);
		profilePage.verifyEmail(TestData.email);

		
	}

}
