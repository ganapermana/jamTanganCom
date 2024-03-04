import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

import java.util.Objects;

public class TransactionPage {
    private WebDriver driver;
    HelperPage helperPage = new HelperPage(this.driver);    //to call wait function

    // Constructor to initialize driver
    public TransactionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchWatchAccordingToPrice(String keyword, String priceRange){
        searchWatch(keyword);
        setPriceRange(priceRange);
    }

    public void addWatchToCart(){
        selectFirstWatchResult();
        clickBeliSekarang();
        helperPage.waitSomeSeconds(1);
        clickLanjutkanButtonCart();
    }

    public void isiAlamat(String phoneNumber, String address){
        helperPage.waitSomeSeconds(1);
        clickIsiAlamatButton();
        fillAlamatBuyer(phoneNumber, address);
        clickSimpanAlamatButton();
        clickLanjutkanButtonCart();
    }

    public void selectShippingOptions(){
        helperPage.waitSomeSeconds(1);
        clickSelectCourier();
        clickPilihPembayaranButton();
    }

    public void selectPaymentOptions(){
        selectVirtualAccount();
    }

    public void checkTotalPrice(){
//        Assert.assertEquals(true, validatePayment());
        Assert.assertEquals("different boolean result", true, validatePayment());
    }

    public void searchWatch(String keyword){
        WebElement inputSearchBar = driver.findElement(By.xpath("//input[@aria-label='search-input']"));
        inputSearchBar.sendKeys(keyword);
        inputSearchBar.sendKeys(Keys.RETURN);
    }

    public void setPriceRange(String priceRange){
        WebElement inputPriceMax = driver.findElement(By.xpath("//input[@data-testid='filter-input-price-max']"));
        WebElement buttonTerapkan = driver.findElement(By.xpath("//button[@data-testid='filter-input-submit']"));
        inputPriceMax.sendKeys(priceRange);
        helperPage.waitSomeSeconds(2);
        buttonTerapkan.click();
    }

    public void selectFirstWatchResult(){
        WebElement firstResultWatch = driver.findElement(By.xpath("//div[@data-testid='product-card']"));
        firstResultWatch.click();
    }

    public void clickBeliSekarang(){
        WebElement buttonBeliSekarang = driver.findElement(By.xpath("//button[@id='btn-buy-now']"));
        buttonBeliSekarang.click();
    }

    public void clickLanjutkanButtonCart(){
        WebElement buttonLanjutkan = driver.findElement(By.xpath("//button[@data-testid='cart-btn-summary-cta']"));
        buttonLanjutkan.click();
    }

    public void clickIsiAlamatButton(){
        WebElement buttonIsiAlamatPopup = driver.findElement(By.xpath("//button[@aria-label='delete-item']/following-sibling::button"));
        helperPage.waitSomeSeconds(1);
        buttonIsiAlamatPopup.click();
    }

    public void fillAlamatBuyer(String phonenumber, String address){
        WebElement inputPhoneNumber = driver.findElement(By.xpath("//input[@aria-label='Phone']"));
        inputPhoneNumber.sendKeys(phonenumber);
        WebElement inputCity = driver.findElement(By.xpath("//input[@aria-label='City or district']"));
        inputCity.sendKeys("Jakarta");
        helperPage.waitSomeSeconds(3);
        WebElement dropdownCityOptions = driver.findElement(By.xpath("//div[@data-testid='input-dropdown']//li[1]"));
        dropdownCityOptions.click();
        WebElement inputPostalCode = driver.findElement(By.xpath("//input[@aria-label='Postal code']"));
        WebElement inputAddressDetail = driver.findElement(By.xpath("//textarea[@aria-label='Address detail']"));
        inputPostalCode.sendKeys("10000");
        inputAddressDetail.sendKeys(address);
        helperPage.waitSomeSeconds(1);
    }

    public void clickSimpanAlamatButton(){
        WebElement buttonSimpanAlamat = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonSimpanAlamat.click();
    }

    public void clickSelectCourier(){
        WebElement dropdownCourierOptions = driver.findElement(By.xpath("//button[@aria-label='Choose shipping method']"));
        dropdownCourierOptions.click();
        WebElement labelCourierChoice = driver.findElement(By.xpath("//div[@data-testid='shipping-method-dropdown']//li[1]"));
        labelCourierChoice.click();
        helperPage.waitSomeSeconds(2);
    }

    public void clickPilihPembayaranButton(){
        WebElement buttonPilihPembayaran = driver.findElement(By.xpath("//button[contains(text(),'Pilih Pembayaran')]"));
        buttonPilihPembayaran.click();
        helperPage.waitSomeSeconds(5);
    }

    public void selectVirtualAccount(){
        WebElement buttonVirtualAccount = driver.findElement(By.xpath("//div[@data-testid='payment-item-VirtualAccount']"));
        buttonVirtualAccount.click();
        helperPage.waitSomeSeconds(1);
    }

    public Integer getTotalHarga(){
        WebElement labelTotalHarga = driver.findElement(By.xpath("//div[contains(text(),'Total Harga')]/following-sibling::div"));
        return helperPage.priceToInteger(labelTotalHarga.getText());
    }

    public Integer getTotalOngkir(){
        WebElement labelTotalOngkir = driver.findElement(By.xpath("//div[contains(text(),'Total Ongkos Kirim')]/following-sibling::div"));
        return helperPage.priceToInteger(labelTotalOngkir.getText());
    }

    public Integer getAsuransiPengiriman(){
        WebElement labelAsuransiPengiriman = driver.findElement(By.xpath("//div[contains(text(),'Asuransi Pengiriman')]/following-sibling::div"));
        return helperPage.priceToInteger(labelAsuransiPengiriman.getText());
    }

    public Integer getTotalBelanja(){
        WebElement labelTotalBelanja = driver.findElement(By.xpath("//div[contains(text(),'Total Belanja')]/following-sibling::div/div[contains(text(),'Rp')]"));
        return helperPage.priceToInteger(labelTotalBelanja.getText());
    }

    public Boolean validatePayment(){
        Integer totalExpected = getTotalHarga() + getTotalOngkir() + getAsuransiPengiriman();
        System.out.println("total harga "+getTotalHarga());
        System.out.println("total ongkir "+ getTotalOngkir());
        System.out.println("asuransi pengiriman "+ getAsuransiPengiriman());
        System.out.println(getTotalBelanja()+" "+totalExpected);
        return Objects.equals(getTotalBelanja(), totalExpected);
    }

}
