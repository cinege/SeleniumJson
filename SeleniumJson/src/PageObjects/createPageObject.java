package PageObjects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

import Selenium.Driver;


public class createPageObject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path fileName = Paths.get("C:\\Tamas\\java\\ws\\SeleniumJson\\src\\PageObjects\\login.json");
		String content = "";
        try {
			content = Files.readString(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String url = "https://teveclub.hu";
        String user = "Minciteve";
        String pwd = "gyereide";
        
		Gson gson = new Gson();
		PageObject loginpage = gson.fromJson(content, PageObject.class);
		Driver d = new Driver("firefox", true);
		loginpage.execute(d, "login", new String[]{url, user, pwd});
		//System.out.println(loginpage.Functions[0].actions[1].method);
		
		
	}

}
