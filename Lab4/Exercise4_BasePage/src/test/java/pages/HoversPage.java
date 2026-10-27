package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HoversPage extends BasePage {

    private final By figures = By.cssSelector("#content .figure");

    public HoversPage(WebDriver driver) {
        super(driver);
    }

    public HoversPage open() {
        navigateTo("https://the-internet.herokuapp.com/hovers");
        return this;
    }

    public String hoverAndGetCaption(int index) {
        List<WebElement> users = driver.findElements(figures);

        if (index < 0 || index >= users.size()) {
            throw new IllegalArgumentException(
                    "Index người dùng không hợp lệ: " + index
            );
        }

        WebElement user = users.get(index);

        new Actions(driver)
                .moveToElement(user)
                .perform();

        WebElement caption = user.findElement(
                By.cssSelector(".figcaption h5")
        );

        return wait.until(
                ExpectedConditions.visibilityOf(caption)
        ).getText();
    }
}