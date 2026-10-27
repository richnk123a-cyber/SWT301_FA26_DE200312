package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddRemoveElementsPage extends BasePage {

    private final By addButton =
            By.xpath("//button[text()='Add Element']");

    private final By deleteButtons =
            By.cssSelector(".added-manually");

    public AddRemoveElementsPage(WebDriver driver) {
        super(driver);
    }

    public AddRemoveElementsPage open() {
        navigateTo(
                "https://the-internet.herokuapp.com/add_remove_elements/"
        );
        return this;
    }

    public void addElements(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(
                    "Số phần tử không được âm"
            );
        }

        for (int i = 0; i < number; i++) {
            click(addButton);
        }
    }

    public int getAddedCount() {
        return driver.findElements(deleteButtons).size();
    }
}