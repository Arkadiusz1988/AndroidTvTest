import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.*;
import com.example.arkadiusz.androidtvtest.MainActivity;
import org.junit.*;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

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
  public void checkWifiConnected() throws UiObjectNotFoundException {

    mDevice.pressHome();

    UiObject start = mDevice.findObject(new UiSelector().textStartsWith("Wi-Fi is not"));

    if (!(start.waitForExists(9000))) {
      UiObject allAppsButton = mDevice.findObject(new UiSelector().description("Apps"));
      allAppsButton.waitForExists(10);
      mDevice.pressDPadUp();
      mDevice.pressDPadRight();
      mDevice.pressDPadRight();
      mDevice.pressDPadRight();
      mDevice.pressDPadCenter();
      mDevice.pressDPadCenter();
      UiObject networkAndInternet =
          mDevice.findObject(new UiSelector().textContains("Network & Internet"));
      networkAndInternet.waitForExists(5000);
      //        mDevice.pressDPadDown();
      mDevice.pressDPadCenter();
      UiObject notification = mDevice.findObject(new UiSelector().textStartsWith("Wi-fi"));
      notification.waitForExists(5000);
      Assert.assertEquals(true, notification.waitForExists(5000));

      //        if(mDevice.findObject(new UiSelector().text("Videos by Google")).exists()){
      //            mDevice.pressDPadRight();
      //            mDevice.pressDPadRight();
      //        }
      //        UiObject appsTab = mDevice.findObject(new UiSelector().text("AndroidTvTest"));
      //        appsTab.click();
        } else {
                Assert.assertFalse("wifi is not connected",true);
        }

    }

    @Test
    public void checkWifiNotConnected() throws UiObjectNotFoundException {

        mDevice.pressHome();
        UiObject start = mDevice.findObject(new UiSelector().textStartsWith("Wi-Fi is not"));

    if (start.waitForExists(9000)) {

      UiObject allAppsButton = mDevice.findObject(new UiSelector().description("Apps"));
      allAppsButton.waitForExists(10);
      mDevice.pressDPadUp();
      mDevice.pressDPadUp();
      mDevice.pressDPadRight();
      mDevice.pressDPadCenter();
      UiObject settings = mDevice.findObject(new UiSelector().textContains("Settings"));
      settings.waitForExists(5000);
      mDevice.pressDPadCenter();
      UiObject networkAndInternet =
          mDevice.findObject(new UiSelector().textContains("Network & Internet"));
      networkAndInternet.waitForExists(5000);
      mDevice.pressDPadCenter();
      UiObject notification = mDevice.findObject(new UiSelector().textStartsWith("AndroidWifi"));
      notification.waitForExists(5000);
      Assert.assertEquals(true, notification.waitForExists(5000));
      //        mDevice.pressDPadCenter();
      //        mDevice.pressDPadDown();
      //        mDevice.pressDPadDown();

      //        mDevice.pressDPadRight();
      //        mDevice.pressDPadRight();
      //        mDevice.pressDPadCenter();
      //        mDevice.pressDPadCenter();
      //        UiObject networkAndInternet = mDevice.findObject(new
      // UiSelector().textContains("Network & Internet"));
      //        networkAndInternet.waitForExists(5000);
      //        mDevice.pressDPadCenter();
      //        UiObject notification = mDevice.findObject(new
      // UiSelector().textStartsWith("Wi-fi"));
      //        notification.waitForExists(5000);
      //        Assert.assertEquals(true,notification.waitForExists(5000));
     }
    }

    @Test
    public void checkAppsGoogleContent() throws UiObjectNotFoundException, InterruptedException, IOException {

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
        System.out.println(mDevice.takeScreenshot(new File("screenshot-file-name")));
        mDevice.takeScreenshot(new File("/sdcard/Download/test.png"));
        mDevice.pressDPadRight();
        mDevice.pressDPadCenter();
        UiObject allAppsButton2 = mDevice.findObject(new UiSelector().textContains("Google Zeitgeist"));
        allAppsButton2.clickAndWaitForNewWindow();
        mDevice.pressDPadRight();
        mDevice.pressDPadCenter();
        sleep(3000);
        File path = new File("screenshot-file-name.png");
//        mDevice.executeShellCommand("adb shell screencap -p /media/arkadiusz/7A8C460A8C45C0FD/screencap.png");
//        mDevice.executeShellCommand("adb pull /media/arkadiusz/7A8C460A8C45C0FD/screencap.png");
        sleep(3000);
        int SDK_VERSION = android.os.Build.VERSION.SDK_INT;
        if (SDK_VERSION >= 17) {
            mDevice.takeScreenshot(path);
        }
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
        mDevice.takeScreenshot(new File("screenshot-file-name.png"));
    }
}
