import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class SimpleTest {

    private static final long DEFAULT_TIMEOUT = 30000L;
    private static final String PACKAGE_NAME = "com.example.arkadiusz.androidtvtest";
    private UiDevice device;

    @Before
    public void setUp() {
        // Disabling waiting for selector implicit timeout
        Configurator.getInstance().setWaitForSelectorTimeout(0);
        // Initializing UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry
                .getInstrumentation());

        // Starting the app
        Context context = InstrumentationRegistry.getInstrumentation()
                .getContext();
        Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(PACKAGE_NAME)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Waiting for app activity to appear
        device.wait(Until
                        .hasObject(By.pkg(PACKAGE_NAME).depth(0)),
                DEFAULT_TIMEOUT);
    }

    @Test
    public void formTest() {
        // Finding element
        UiObject2 editText = device.findObject(By.text("edit_text"));
        // Sending text to element
        editText.setText("123456");

        // Waiting for element
        BySelector submitButtonSelector = By.text("submit");
        UiObject2 submitButton = device.wait(Until
                        .findObject(submitButtonSelector),
                DEFAULT_TIMEOUT);
        // Clicking on element
        submitButton.click();

        BySelector alertSelector = By.desc("alert");
        // Waiting for alert to appear
        UiObject2 alert = device.wait(Until.findObject(alertSelector),
                DEFAULT_TIMEOUT);
        assertEquals(alert.getText(), "Success!");
    }

    @After
    public void tearDown() {
        // Taking screenshot after test
        device.takeScreenshot(new File("screenshot-file-name.jpg"));
    }
}