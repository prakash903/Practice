package Hotstar;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginRepository {
	WebDriver driver;
	static String connectionHost = "jdbc:mysql://localhost:3306/seleniumpractice?autoReconnect=true&useSSL=false";
	static String hostId = "root";
	static String hostPassword = "Prakash@123";

	@FindBy(xpath = "//*[@class='signIn']")
	WebElement loginButton;

	@FindBy(xpath = "//*[@class='email-fb-button']")
	WebElement emailButton;

	@FindBy(xpath = "//*[@class='fb-btn']")
	WebElement fbButton;

	@FindBy(xpath = "//*[@id='email']")
	WebElement fbUserName;

	@FindBy(xpath = "//*[@id='pass']")
	WebElement fbPassword;

	@FindBy(xpath = "//*[@name='login']")
	WebElement fbLoginButton;

	public LoginRepository(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static ResultSet database() throws SQLException {
		Connection con = DriverManager.getConnection(connectionHost, hostId, hostPassword);
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from logincredential where logintype = 'User1'");
		rs.next();
		return rs;
	}

	private static Properties fileProperty() throws IOException {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream("datafile.properties");
		prop.load(fs);
		return prop;
	}

	public String url() throws IOException {
		return LoginRepository.fileProperty().getProperty("URL");
	}

	public String userName() throws SQLException {
		return LoginRepository.database().getString("loginId");

	}

	public String password() throws SQLException {
		return LoginRepository.database().getString("loginpassword");
	}

	public WebElement login() {
		return loginButton;
	}

	public WebElement email() {
		return emailButton;
	}

	public WebElement fb() {
		return fbButton;
	}

	public WebElement fbUser() {
		return fbUserName;
	}

	public WebElement fbPass() {
		return fbPassword;
	}

	public WebElement fbLogin() {
		return fbLoginButton;
	}

}
