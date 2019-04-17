import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.*;
import com.example.arkadiusz.androidtvtest.MainActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(AndroidJUnit4.class)
public class MyUiAutomatorTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule
            = new ActivityTestRule<>(MainActivity.class);

    private UiDevice mDevice;

    @Before
    public void setUp() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();
        mDevice.wait(Until.hasObject(By.pkg(getLauncherPackageName()).depth(0)), 1000);
    }

    @Test
    public void checkAppsGoogle() throws UiObjectNotFoundException {
        mDevice.pressHome();
        UiObject allAppsButton = mDevice.findObject(new UiSelector().description("Apps"));
        allAppsButton.clickAndWaitForNewWindow();
        mDevice.pressDPadDown();
        if(mDevice.findObject(new UiSelector().text("AndroidTvTest")).exists()){
            mDevice.pressDPadLeft();
            mDevice.pressDPadLeft();
        }
        UiObject appsTab = mDevice.findObject(new UiSelector().textContains("Videos by Google"));
        appsTab.click();
    }

    @Test
    public void checkApps() throws UiObjectNotFoundException {

        mDevice.pressHome();
        UiObject allAppsButton = mDevice.findObject(new UiSelector().description("Apps"));
        allAppsButton.clickAndWaitForNewWindow();
        mDevice.pressDPadDown();
        if(mDevice.findObject(new UiSelector().text("Videos by Google")).exists()){
            mDevice.pressDPadRight();
            mDevice.pressDPadRight();
        }
        UiObject appsTab = mDevice.findObject(new UiSelector().text("AndroidTvTest"));
        appsTab.click();
    }

    @Test
    public void checkAppsGoogleContent() throws UiObjectNotFoundException {

        mDevice.pressHome();
        UiObject allAppsButton = mDevice.findObject(new UiSelector().description("Apps"));
        allAppsButton.clickAndWaitForNewWindow();
        mDevice.pressDPadDown();
        if(mDevice.findObject(new UiSelector().text("AndroidTvTest")).exists()){
            mDevice.pressDPadLeft();
            mDevice.pressDPadLeft();
        }
        UiObject appsTab = mDevice.findObject(new UiSelector().textContains("Videos by Google"));
        appsTab.click();
        UiObject allAppsButton1 = mDevice.findObject(new UiSelector().textContains("Zeitgeist"));
        allAppsButton1.clickAndWaitForNewWindow();
        mDevice.pressDPadRight();
        mDevice.pressDPadCenter();
        UiObject allAppsButton2 = mDevice.findObject(new UiSelector().textContains("Google Zeitgeist"));
        allAppsButton2.clickAndWaitForNewWindow();
        mDevice.pressDPadRight();
        mDevice.pressDPadCenter();
    }

    private String getLauncherPackageName() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

    @After
    public void tearDown() {
        mDevice.takeScreenshot(new File("/media/arkadiusz/7A8C460A8C45C0FD/projekty_AndroidTv/screenshot-file-name.png"));
    }
}
