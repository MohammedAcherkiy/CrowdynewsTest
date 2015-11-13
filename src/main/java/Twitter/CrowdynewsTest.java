package Twitter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CrowdynewsTest {
	WebDriver driver;

	@BeforeMethod

	// Cross Browser testing Firefox, Chrome, Safari
	public void SetUp() throws IOException {
		Properties pro = new Properties();
		FileInputStream file = null;
		try {
			file = new FileInputStream(
					"/Users/mohammedacherkiy/Documents/workspace2/Twitter/src/main/java/Twitter/file.properties");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		pro.load(file);
		if (pro.getProperty("browser").equals("firefox")) {
			driver = new FirefoxDriver();
			driver.get("http://example.crowdynews.com/crowdynews/usa/politics/");
			driver.manage().window().maximize();
		} else {
			if (pro.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "/Users/mohammedacherkiy/Downloads/chromedriver");
				driver = new ChromeDriver();
				driver.get("http://example.crowdynews.com/crowdynews/usa/politics/");
				driver.manage().window().maximize();

			} else {
				if (pro.getProperty("browser").equals("safari")) {

					driver = new SafariDriver();
					driver.get("http://example.crowdynews.com/crowdynews/usa/politics/");
					driver.manage().window().maximize();
				}
			}
		}

	}

	// This test will fail because the text is not the same as the text in the
	// item
	@Test()

	public void VeifyDefaultTextInsideTwitterDialogIsTheSameAsTheTitleOfTheItem() {
		// Wait for the page to load and elements to be visible
		WebUtil.waitForElementVisible(driver, By.cssSelector(".cnList"));

		// selecting All elements from the home page and store them in
		// divElement variable

		WebElement divElement = driver.findElement(By.cssSelector(".cnList"));

		List<WebElement> twiiterIcone = new ArrayList<WebElement>();

		// Finding items that have # of tweets >5

		twiiterIcone = divElement.findElements(By.cssSelector(".kudos-count"));

		for (int i = 0; i < twiiterIcone.size(); i++) {

			if (twiiterIcone.size() > 5) {
				// Click on the item that has # of tweets >5
				twiiterIcone.get(i).click();
				break;
			}

			System.out.println("Can't find item that has more than 5 tweets");

		}
		// Wait for the twitter shareIcone to be visible
		WebUtil.waitForElementVisible(driver, By.cssSelector(".icon-twitter.share-twitter"));

		// Click on the Twitter Icon

		WebUtil.click(driver, By.cssSelector(".icon-twitter.share-twitter"));

		// handling the Child Window

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> id = ids.iterator();
		String parent = id.next();
		String childWindow = id.next();
		driver.switchTo().window(childWindow);

		// wait for the elements on the child window to be visible

		WebUtil.waitForElementVisible(driver, By.id("status"));
		// Store the text found inside Twitter dialog in a text variable.
		String text = driver.findElement(By.id("status")).getText();

		// Switching to the parent Window
		driver.switchTo().window(parent);

		// Wait for the title to be visible
		WebUtil.waitForElementVisible(driver, By.xpath("//div[@class='cnoverlay-articletitle']"));
		// Store the title of the item in text2 variable.

		String text2 = driver.findElement(By.xpath("//div[@class='cnoverlay-articletitle']")).getText();
		// Assert that text1 is equal to text2
		// This test will fail because text 1 is not the same as text2
		Assert.assertEquals(text, text2);
	}

	@Test
	public void VerifyThatTheFiveAuthorsOfTheFiveTweetsAreTheSame() {

		// Wait for the page to load and elements to be visible
		WebUtil.waitForElementVisible(driver, By.cssSelector(".cnList"));

		// selecting All elements from the home page and store them in
		// divElement variable

		WebElement divElement = driver.findElement(By.cssSelector(".cnList"));

		List<WebElement> twiiterIcone = new ArrayList<WebElement>();

		// Finding items that have # of tweets >5

		twiiterIcone = divElement.findElements(By.cssSelector(".kudos-count"));

		for (int i = 0; i < twiiterIcone.size(); i++) {

			if (twiiterIcone.size() > 5) {
				// Click on the item that has # of tweets >5
				twiiterIcone.get(i).click();
				break;
			}

			System.out.println("Can't find item that has more than 5 tweets");

		}

		// Selecting all the web elements from the second page
		WebElement divElement2 = driver.findElement(By.xpath("//div[@id='cnoverlay-contentContainer']"));
		List<WebElement> allAuthorsName = new ArrayList<WebElement>();
		// Store the web elements of the five authors in the variable all
		allAuthorsName = divElement2.findElements(By.cssSelector(".username>a"));
		for (int i = 0; i < allAuthorsName.size(); i++) {
			String authorName = allAuthorsName.get(i).getText();
			// System.out.println(authorName);
			// finding the name of the author on the item
			String authorName2 = driver.findElement(By.xpath("//div[@class='cnoverlay-author']")).getText();
			// System.out.println(authorName2);
			// Assert that the five author names on the 5 tweets is the same as
			// the author's name
			// on the item
			Assert.assertTrue(authorName.equalsIgnoreCase(authorName2));

		}
	}

	@Test
	public void VerifyTheFiveURLSareTheSameAsTheLinkOnTheItem() {
		// Wait for the page to load and elements to be visible
		WebUtil.waitForElementVisible(driver, By.cssSelector(".cnList"));

		// selecting All elements from the home page and store them in
		// divElement variable

		WebElement divElement = driver.findElement(By.cssSelector(".cnList"));

		List<WebElement> twiiterIcone = new ArrayList<WebElement>();

		// Finding items that have # of tweets >5

		twiiterIcone = divElement.findElements(By.cssSelector(".kudos-count"));

		for (int i = 0; i < twiiterIcone.size(); i++) {

			if (twiiterIcone.size() > 5) {
				// Click on the item that has # of tweets >5
				twiiterIcone.get(i).click();
				break;
			}

			System.out.println("Can't find item that has more than 5 tweets");

		}

		WebElement divElement3 = driver.findElement(By.cssSelector(".cnList"));
		List<WebElement> url = new ArrayList<WebElement>();
		url = divElement3.findElements(By.xpath("//span[@class='link'][1]"));
		for (int i = 0; i < url.size(); i++) {
			String x1 = url.get(0).getText();
			String x2 = url.get(1).getText();
			String x3 = url.get(2).getText();
			String x4 = url.get(3).getText();
			String x5 = url.get(4).getText();
			Assert.assertEquals(x1, x2);
			Assert.assertEquals(x2, x3);
			Assert.assertEquals(x3, x4);
			Assert.assertEquals(x4, x5);
		}

		// Clicking on the link and handling the new window
		WebElement click2 = driver.findElement(By.cssSelector(".link>a"));
		click2.click();
		Set<String> ids2 = driver.getWindowHandles();
		Iterator<String> it2 = ids2.iterator();
		String parent1 = it2.next();
		String child1 = it2.next();
		driver.switchTo().window(child1);
		String url1 = driver.getCurrentUrl();
		System.out.println(url1);

		// Switching back to the parent window
		driver.switchTo().window(parent1);

		// Clicking on the link to original article and handling a new window
		WebElement click = driver.findElement(By.cssSelector(".cnClickSource"));
		click.click();

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		String url2 = driver.getCurrentUrl();
		System.out.println();
		// Assert that the urls from both links are the same.
		Assert.assertEquals(url1, url2);

	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}

}
