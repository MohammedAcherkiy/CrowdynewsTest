package Twitter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUtil {

	public static void waitForElementVisible(WebDriver driver, By by) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	}

	public static void click(WebDriver driver, By by) {
		WebElement twitterIcon = driver.findElement(by);
		twitterIcon.click();
	}

}
