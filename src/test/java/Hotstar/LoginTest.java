package Hotstar;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import Hotstar.LoginRepository;

public class LoginTest {
	WebDriver driver;

	@Test
	public void loginTest() throws IOException, InterruptedException, SQLException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		LoginRepository lr = new LoginRepository(driver);
		driver.get(lr.url());
		lr.login().click();
		lr.email().click();
		//Thread.sleep(5000);
		lr.fb().click();
		lr.fb().click();

		String currentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();

		for (String switchWindow : window) {
			driver.switchTo().window(switchWindow);
		}
		System.out.println(window.size());
		// driver.switchTo().window(window.iterator().next());
		lr.fbUser().sendKeys(lr.userName());
		lr.fbPass().sendKeys(lr.password());
		lr.fbLogin().click();

		driver.switchTo().window(currentWindow);
		//Thread.sleep(3000);
		System.out.println(driver.getTitle());
		try {
			int windowSize = 1;
			Set<String> w = driver.getWindowHandles();
			assertEquals(w.size(), windowSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@AfterTest
	public void close() {
		driver.close();
	}

}
