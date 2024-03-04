import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {
    WebDriver driver;

    public void runChrome (){
        System.setProperty("webdriver.chrome.driver", "/Users/ganapermana/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.jamtangan.com/");
        driver.manage().window().fullscreen();
    }

    public static void main(String[] args) {
        MainPage tools = new MainPage();
        tools.runChrome();

        String username = "shawty@mailnesia.com";
        String password = "P@ssw0rd1";
        String keywordSearch = "casio";
        String priceRange = "10000000";
        String phoneNumber = "082213332334";
        String buyerAddress = "Gana-Candidate QA";

        LoginPage loginPage = new LoginPage(tools.driver);
        TransactionPage transactionPage = new TransactionPage(tools.driver);

        loginPage.doLogin(username, password);

        transactionPage.searchWatchAccordingToPrice(keywordSearch, priceRange);
        transactionPage.addWatchToCart();
        transactionPage.isiAlamat(phoneNumber,buyerAddress);
        transactionPage.selectShippingOptions();
        transactionPage.selectPaymentOptions();
        transactionPage.checkTotalPrice();
        tools.driver.quit();
    }
}
