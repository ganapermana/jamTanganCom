import org.openqa.selenium.WebDriver;

public class HelperPage {
    private WebDriver driver;

    // Constructor to initialize driver
    public HelperPage(WebDriver driver) {
        this.driver = driver;
    }
    public void waitSomeSeconds(Integer seconds) {
        System.out.println("waiting for "+seconds+ " seconds");
        try {
            Thread.sleep(seconds * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer priceToInteger(String wordString){
        String priceWithoutRp = wordString.replace("Rp","");
        Integer price = Integer.parseInt(priceWithoutRp.replace(".",""));
        return price;
    }
}
