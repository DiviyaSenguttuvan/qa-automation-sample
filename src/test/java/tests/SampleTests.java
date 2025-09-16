package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class SampleTests {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    public void setUpClass() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterClass
    public void tearDownClass() {
        browser.close();
        playwright.close();
    }

    @BeforeMethod
    public void setUp() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod
    public void tearDown() {
        context.close();
    }

    @Test
    public void registrationTest() {
        page.navigate("https://demo.opencart.com/");
        page.click("text=My Account");
        page.click("text=Register");
        page.fill("#input-firstname", "Diviya");
        page.fill("#input-lastname", "Senguttuvan");
        page.fill("#input-email", "testuser" + System.currentTimeMillis() + "@mail.com");
        page.fill("#input-telephone", "9876543210");
        page.fill("#input-password", "Password123");
        page.fill("#input-confirm", "Password123");
        page.check("input[name='agree']");
        page.click("input[value='Continue']");
    }

    @Test
    public void loginTest() {
        page.navigate("https://demo.opencart.com/");
        page.click("text=My Account");
        page.click("text=Login");
        page.fill("#input-email", "your_registered_email@mail.com");
        page.fill("#input-password", "Password123");
        page.click("input[value='Login']");
    }

    @Test
    public void profileUpdateTest() {
        page.navigate("https://demo.opencart.com/index.php?route=account/account");
        page.click("text=Edit your account information");
        page.fill("#input-telephone", "9123456789");
        page.click("input[value='Continue']");
    }

    @Test
    public void logoutTest() {
        page.navigate("https://demo.opencart.com/");
        page.click("text=My Account");
        page.click("text=Logout");
    }
}
