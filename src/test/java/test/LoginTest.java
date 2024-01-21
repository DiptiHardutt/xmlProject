package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;


public class LoginTest {
	WebDriver driver;
	
	//Login Data
	String userName;
	String password;
	String expDashboardHeaderText;
	String expAlertUserNameText;
	String expAlertPasswordText;
	
	@Test(groups={"smoke","regression"})
	@Parameters({"userName","password","expDashboardHeaderText"})
	public void validUserShouldBeAbleToLogin(String userName,String password,String expDashboardHeaderText) {
		//we have to make init method static so we can call our method by class name directly
		//and we have to return Webdriver in init method so we can transfer our driver here by following:-
		driver = BrowserFactory.init();
		
		//Create object for Login Page class to call the intractable methods and use PageFactory not new keyword
		//create parameterized constructor for LoginPage
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(userName);
		loginPage.insertPassword(password);
		loginPage.clickOnSigninButton();
		
		//Create object for Dashboard Page class
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.validateDashboardPage(expDashboardHeaderText);
		BrowserFactory.tearDown();
	}
	
	@Test(groups= {"regression"})
	@Parameters({"alertUserNameText","userName","alertPasswordText"})
	public void validateLoginAlertMsg(String expAlertUserNameText,String userName,String expAlertPasswordText) {
		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.clickOnSigninButton();
		loginPage.validateUserAlertMsg(expAlertUserNameText);
		loginPage.insertUserName(userName);
		loginPage.clickOnSigninButton();
		loginPage.validatePasswordAlertMsg(expAlertPasswordText);
		BrowserFactory.tearDown();
		
	}
}
