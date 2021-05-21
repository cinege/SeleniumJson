package Selenium;


import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class StartBrowser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\windows\\chromedriver.exe");
			ChromeOptions chromeoptions = new ChromeOptions();
			//if (!this.visible) {chromeoptions.addArguments("--headless");}
			chromeoptions.addArguments("--disable-notifications");
			ChromeDriver driver = new ChromeDriver(chromeoptions);
			//Driver browser = new Driver("chrome",true);
			driver.get("https://www.google.com");
		} catch (SessionNotCreatedException e){
		    System.out.println("Please download the chromedriver ");
			//System.out.println();
			e.printStackTrace();
		} catch (IllegalStateException e2) {
			e2.printStackTrace();
		}
	}
}
