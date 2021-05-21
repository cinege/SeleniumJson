package Selenium;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;


public class Driver implements WebDriver{
	String browser;
	boolean visible;
	public WebDriver driver;
	//WebDriverWait scheduler;
	public FluentWait<WebDriver> fluentwait;
	
	public Driver(String browser, boolean visible) {
		this.browser = browser;
		this.visible = visible;
		this.fluentwait = null;
	}
	public void openBrowser() {
		System.out.println("Browser opening");
		if (this.browser == "firefox") {
				System.setProperty("webdriver.gecko.driver","C:\\windows\\geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(!this.visible);
				this.driver = new FirefoxDriver(options);
		}
		else if (this.browser == "chrome") {
				System.setProperty("webdriver.chrome.driver", "C:\\windows\\chromedriver.exe");
				ChromeOptions chromeoptions = new ChromeOptions();
				if (!this.visible) {chromeoptions.addArguments("--headless");}
				this.driver = new ChromeDriver(chromeoptions);
		}
		this.fluentwait = new FluentWait<>(this.driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(200))
				.ignoring(Exception.class);
		        //.ignoring(NoSuchElementException.class);
				//.ignoring(UnhandledAlertException.class)
				//ElementNotInteractableException
	}
	
 	public void login(String user, String pw) {
 		this.driver.get("https://sabadat-pro.itcs.houston.dxccorp.net/HUN-Sabadat");
 		this.driver.findElement(By.id("USER")).sendKeys(user+Keys.TAB+pw+Keys.RETURN);
 	}
	
	public void waitandclick(By locator) {
		this.fluentwait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public void waitandfill(By locator, String input) {
		WebElement elem = this.fluentwait.until(ExpectedConditions.elementToBeClickable(locator));
		elem.clear();
		elem.sendKeys(input);

	}
	public void waitandselect(String id, int index) throws InterruptedException {
		this.fluentwait.until(ExpectedConditions.elementToBeSelected(By.id(id)));
		this.fluentwait.wait(1);
		this.select(id,index);
	}
	
	public void waitandselect(String id, String entry) throws InterruptedException{
		this.fluentwait.until(ExpectedConditions.elementToBeSelected(By.id(id)));
		this.fluentwait.wait(1);
		this.select(id,entry);
	}
	
	public void waituntilabsent(By locator) throws InterruptedException {
		boolean present = true;
		while (present) {
			TimeUnit.SECONDS.sleep(1);
			try {
				this.findElement(locator);
				System.out.println("sajnos");
			} catch (Exception e) {
				present = false;
			}
		}	
	}
	public String getselectedfieldvalue(String id) {
		return new Select(this.find(id)).getFirstSelectedOption().getText();
	} 
	public void click(String id) {find(id).click();}
	public void fill(String id, String input) {find(id).sendKeys(input);}
	public void select(String id, int index) {new Select(this.find(id)).selectByIndex(index);}
	public void select(String id, String entry) {new Select(this.find(id)).selectByVisibleText(entry);}
	public WebElement find(String id) {return this.driver.findElement(By.id(id));}
	
	//original
	@Override public void close() {this.driver.close();}
	@Override public WebElement findElement(By arg0) {return this.driver.findElement(arg0);}
	@Override public List<WebElement> findElements(By arg0) {return this.driver.findElements(arg0);}
	@Override public void get(String url) {driver.get(url);}
	@Override public String getCurrentUrl() {return this.driver.getCurrentUrl();}
	@Override public String getPageSource() {return this.driver.getPageSource();}
	@Override public String getTitle() {return this.driver.getTitle();}
	@Override public String getWindowHandle() {return this.driver.getWindowHandle();}
	@Override public Set<String> getWindowHandles() {return this.driver.getWindowHandles();}
	@Override public Options manage() {return this.driver.manage();}
	@Override public Navigation navigate() {return this.driver.navigate();}
	@Override public void quit() {this.driver.quit();}
	@Override public TargetLocator switchTo() {return this.driver.switchTo();}
}
