import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class FirstTest {
    static WebDriver driver;

    // Test data
    private final String BASE_URL = "https://next.privat24.ua/mobile";
    private final String CARD_PAN = "4004159115449011";
    private final String CARD_EXP_DATE = "0925";
    private final String CARD_CVV = "123";
    private final String CLIENT_PHONE_NUMBER = "950451017";
    private final String CLIENT_NAME = "OLHA";
    private final String CLIENT_SURNAME = "DEMENTIEVA";

    // UI elements
    By phoneNumber = By.xpath(".//input[@data-qa-node='phone-number']");
    By cardNumberFrom = By.xpath(".//input[@data-qa-node='numberdebitSource']");
    By expDate = By.xpath(".//input[@data-qa-node='expiredebitSource']");
    By cvv = By.xpath(".//input[@data-qa-node='cvvdebitSource']");
    By name = By.xpath(".//input[@data-qa-node='firstNamedebitSource']");
    By surname = By.xpath(".//input[@data-qa-node='lastNamedebitSource']");
    By amount = By.xpath(".//input[@data-qa-node='amount']");
    By btnAddToBasket = By.xpath(".//button[@type='submit']");
    By termsLink = By.xpath(".//a[@href='https://privatbank.ua/terms']");


    By checkCategory = By.xpath(".//div[@data-qa-node='category']");
    By checkDetails = By.xpath(".//div[@data-qa-node='details']");
    By checkCard = By.xpath(".//div[@data-qa-node='card']");
    By checkAmount= By.xpath(".//div[@data-qa-node='amount']");

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void test() throws InterruptedException {

        String amountValue = "500";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get(BASE_URL);

        driver.findElement(phoneNumber).sendKeys(CLIENT_PHONE_NUMBER);;
        driver.findElement(amount).sendKeys(Keys.chord(Keys.CONTROL, "a"), amountValue);

        driver.findElement(cardNumberFrom).sendKeys(CARD_PAN);
        driver.findElement(expDate).sendKeys(CARD_EXP_DATE);
        driver.findElement(cvv).sendKeys(CARD_CVV);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(name).sendKeys(CLIENT_NAME);
        driver.findElement(surname).sendKeys(CLIENT_SURNAME);

        driver.findElement(btnAddToBasket).submit();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        List<WebElement> items = driver.findElements(checkCategory);
        Assert.assertEquals("", 1, items.size());
        Assert.assertEquals("", amountValue + " UAH", driver.findElement(checkAmount).getText());
        Assert.assertTrue(driver.findElement(checkDetails).getText().contains(CLIENT_PHONE_NUMBER));
    }

}
