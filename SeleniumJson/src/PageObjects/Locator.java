package PageObjects;

import org.openqa.selenium.By;

public class Locator {
	String name;
	String tag;
	String value;
	By converttoBy() {
		
	    By result = null;
		switch(this.tag) {
		case "id":
			result = By.id(this.value);
			break;
		case "name":
			result = By.name(this.value);
			break;
		case "xpath":
			result = By.xpath(this.value);
			break;
		default:
			result = null;
			break;
		}
		return result;
	}
}
