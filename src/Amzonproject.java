import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amzonproject {
	
	static String Category;
	static String search;

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
System.setProperty("webdriver.chrome.driver", "chromedriver");
        
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		try {	
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from amazonsearch");
			
			while(rs.next()) {
				
			System.out.println(rs.getInt(1)+ " " +rs.getString(2)+ " " +rs.getString(3));
				Category = rs.getString(2);
				search = rs.getString(3);
				
				System.out.println("category" + " " + Category);
				System.out.println("search" + " " + search);
			}
			  	
			}catch (SQLException e) {
				
				e.printStackTrace();
				
			}catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			
			}	
		WebElement aCategory = driver.findElement(By.xpath("//*[@id='searchDropdownBox']"));
		aCategory.sendKeys(Category);
		WebElement aSearch = driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
		aSearch.sendKeys(search);
		WebElement submit = driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]"));
		submit.click();
		List<WebElement> results = driver.findElements(By.xpath("//*[@data-component-type='s-search-result']"));
		System.out.println("Number of mobiles in the page:" +results.size());
		
		List<WebElement> textprint = driver.findElements(By.xpath("//h2[@ class=\"a-size-mini a-spacing-none a-color-base s-line-clamp-2\"]"));
		
		  for (WebElement result : textprint) {
	            System.out.println(result.getText());               
	        }
		
		}
	}
	
