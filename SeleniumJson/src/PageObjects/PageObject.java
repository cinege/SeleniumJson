package PageObjects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.openqa.selenium.By;

import Selenium.Driver;

public class PageObject {
    String Title;
    Locator[] Locators;
    Function[] Functions;
    
    public String execute(Driver d, String functionName, String[] arguments) {
    	Function f = getFunction(functionName);
    	HashMap<String, String> args = getArguments(f.arguments, arguments);
    	for (Action a : f.actions) {
    		By locator = getLocator(a.locator).converttoBy();
    		switch (a.method) {
    		case "click":
    			d.waitandclick(locator);
    		}
    		
    	}
    	return "";
    }
    
    Function getFunction(String name) {
    	for (Function function : this.Functions) {
    		if (function.name.equals(name)) {
    			return function;
    		}
    	}
    	return null;
    }
    
    Locator getLocator(String name) {
    	for (Locator locator : this.Locators) {
    		if (locator.name.equals(name)) {
    			return locator;
    		}
    	}
    	return null;
    }    
    HashMap<String, String> getArguments(String[] argumentnames, String[] arguments) {
    	HashMap<String, String> args = new HashMap<String, String>();
    	for (int i = 0; i < arguments.length; i++) {
    		args.put(argumentnames[i], arguments[i]);
    	}
    	return args;
    }
}

