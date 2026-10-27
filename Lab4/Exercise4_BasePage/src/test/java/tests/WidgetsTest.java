package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Common web widgets")
class WidgetsTest extends BaseTest {

    @ParameterizedTest(name = "Select {0}")
    @ValueSource(strings = {"Option 1", "Option 2"})
    void dropdownSelect(String option) {
        DropdownPage page = new DropdownPage(driver).open();
        page.select(option);
        assertEquals(option, page.getSelected());
    }

    @Test
    void checkboxToggle() {
        CheckboxesPage page = new CheckboxesPage(driver).open();
        page.check(0);
        page.uncheck(1);
        assertAll(() -> assertTrue(page.isChecked(0)), () -> assertFalse(page.isChecked(1)));
    }

    @Test
    void jsAlerts() {
        JsAlertsPage page = new JsAlertsPage(driver).open();

        page.acceptAlert();
        assertEquals("You successfully clicked an alert", page.getResult());

        page.dismissConfirm();
        assertEquals("You clicked: Cancel", page.getResult());

        page.answerPrompt("SWT301");
        assertEquals("You entered: SWT301", page.getResult());
    }

    @ParameterizedTest(name = "Dynamic loading example {0}")
    @ValueSource(ints = {1, 2})
    void dynamicLoading(int example) {
        String text = new DynamicLoadingPage(driver).open(example).startAndGetResult();
        assertEquals("Hello World!", text);
    }

    @Test
    void nestedFrames() {
        NestedFramesPage page =
                new NestedFramesPage(driver).open();

        assertEquals("MIDDLE", page.getMiddleText());
    }

    @Test
    void multipleWindows() {
        MultipleWindowsPage page =
                new MultipleWindowsPage(driver).open();

        assertEquals(
                "New Window",
                page.openNewWindowAndGetHeading()
        );
    }

    @Test
    void hovers() {
        HoversPage page = new HoversPage(driver).open();

        assertTrue(
                page.hoverAndGetCaption(0).contains("user1")
        );
    }

    @Test
    void fileUpload() {
        FileUploadPage page = new FileUploadPage(driver).open();

        Path file = Path.of(
                "src/test/resources/upload-test.txt"
        );

        assertEquals(
                "upload-test.txt",
                page.upload(file)
        );
    }

    @Test
    void addRemoveElements() {
        AddRemoveElementsPage page =
                new AddRemoveElementsPage(driver).open();

        page.addElements(3);

        assertEquals(3, page.getAddedCount());
    }
}
