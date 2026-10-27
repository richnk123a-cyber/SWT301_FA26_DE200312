package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MultipleWindowsPage extends BasePage {

    private final By clickHereLink = By.linkText("Click Here");
    private final By heading = By.cssSelector("h3");

    public MultipleWindowsPage(WebDriver driver) {
        super(driver);
    }

    public MultipleWindowsPage open() {
        navigateTo("https://the-internet.herokuapp.com/windows");
        return this;
    }

    public String openNewWindowAndGetHeading() {
        String originalWindow = driver.getWindowHandle();

        click(clickHereLink);

        wait.until(
                ExpectedConditions.numberOfWindowsToBe(2)
        );

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        try {
            return getText(heading);
        } finally {
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }
}