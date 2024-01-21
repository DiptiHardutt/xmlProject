package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	static WebDriver driver;
	static String browser;
	static String environment;
	
	// You can read the file with the following:-
	// Buffered Reader /Input Stream /File Reader /Scanner
	public static void readConfig() {
		try {
			Properties prop = new Properties();
			InputStream fi = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(fi);
			browser = prop.getProperty("browser");
			environment = prop.getProperty("url");
							
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static WebDriver init() {
		readConfig();
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else {
			System.out.println("please enter a valid browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(environment);
		return driver;
		
	}
	
	public static void tearDown() {
		driver.close();
		driver.quit();
	}

	
	
}
