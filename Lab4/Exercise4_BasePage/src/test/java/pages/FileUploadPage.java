package pages;

import java.nio.file.Path;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploadPage extends BasePage {

    private final By fileInput = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By uploadedFile = By.id("uploaded-files");

    public FileUploadPage(WebDriver driver) {
        super(driver);
    }

    public FileUploadPage open() {
        navigateTo("https://the-internet.herokuapp.com/upload");
        return this;
    }

    public String upload(Path filePath) {
        waitForVisibility(fileInput).sendKeys(
                filePath.toAbsolutePath().toString()
        );

        click(uploadButton);

        return getText(uploadedFile);
    }
}