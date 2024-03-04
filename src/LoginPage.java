import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    HelperPage helperPage = new HelperPage(this.driver);

    // Constructor to initialize driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void doLogin(String username, String password){
        clickMasukButton();
        inputCredentials(username, password);
    }

    public void clickMasukButton() {
        WebElement buttonOkeLogin = driver.findElement(By.xpath("//button[contains(@class,'driver-close-btn driver-close-only-btn')]"));
        WebElement buttonMasuk = driver.findElement(By.xpath("//button[@id='login-button']"));
        buttonOkeLogin.click();
        buttonMasuk.click();
    }

    public void inputUsername(String username) {
        WebElement inputUsername = driver.findElement(By.xpath("//input[@name='username']"));
        inputUsername.sendKeys(username);
    }

    public void inputPassword(String password) {
        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='password']"));
        inputPassword.sendKeys(password);
    }

    public void inputCredentials(String username, String password) {
        WebElement buttonLoginMasuk = driver.findElement(By.xpath("//div[@data-testid='login-form']//button[contains(text(),'Masuk')]"));
        inputUsername(username);
        inputPassword(password);
        buttonLoginMasuk.click();
        helperPage.waitSomeSeconds(1);
    }
}
