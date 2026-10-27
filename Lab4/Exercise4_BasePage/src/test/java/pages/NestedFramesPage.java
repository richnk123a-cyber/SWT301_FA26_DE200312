package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NestedFramesPage extends BasePage {

    public NestedFramesPage(WebDriver driver) {
        super(driver);
    }

    public NestedFramesPage open() {
        navigateTo(
                "https://the-internet.herokuapp.com/nested_frames"
        );
        return this;
    }

    public String getMiddleText() {
        driver.switchTo().defaultContent();

        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                        "frame-top"
                )
        );

        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                        "frame-middle"
                )
        );

        try {
            return driver.findElement(By.tagName("body"))
                    .getText()
                    .trim();
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}