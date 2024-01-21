package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import page.AddCustomerPage;
import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;


public class AddCustomerTest {
	WebDriver driver;
				
	@Test(groups= {"regression"})
	@Parameters({"userName","password","expDashboardHeaderText","expNewCustomerHeaderText",
		"FullName","CompanyName","Email","Phone","Address","City","zipCodeMin","expZipCodeMinText",
		"zipCodeMax","expZipCodeMaxText","ZipCode","Country","Group"})
	public void userShouldBeAbleToAddCustomer(String userName,String password,
			String expDashboardHeaderText,String expNewCustomerHeaderText,String fullName,
			String companyName, String email, String phone,String address,String city,String zipCodeMin,
			String expZipCodeMinText,String zipCodeMax,String expZipCodeMaxText,String zipCode,
			String country, String group) {
		driver=BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(userName);
		loginPage.insertPassword(password);
		loginPage.clickOnSigninButton();
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.validateDashboardPage(expDashboardHeaderText);
		dashboardPage.clickOnCustomerMenuButton();
		dashboardPage.clickOnAddCustomerMenuButton();
		
		AddCustomerPage addCustomerPage = PageFactory.initElements(driver, AddCustomerPage.class);
		addCustomerPage.validateNewCustomerHeaderText(expNewCustomerHeaderText);
		addCustomerPage.insertFullName(fullName);
		addCustomerPage.selectCompanyFromDropdown(companyName);
		addCustomerPage.insertEmail(email);
		addCustomerPage.insertPhone(phone);
		addCustomerPage.insertAddress(address);
		addCustomerPage.insertCity(city);
		addCustomerPage.zipCodeMinValidation(zipCodeMin, expZipCodeMinText);
		addCustomerPage.zipCodeMaxValidation(zipCodeMax, expZipCodeMaxText);
		addCustomerPage.clickOnFullName();
		addCustomerPage.clearZipCode();
		addCustomerPage.zipCode(zipCode);
		addCustomerPage.selectCountryFromDropDown(country);
		addCustomerPage.selectGroup(group);
		addCustomerPage.clickOnSaveButton();
		addCustomerPage.validateInsertedNameAndDelete();
	}
}
