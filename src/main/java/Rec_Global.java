import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Rec_Global {

    WebDriver ps5;
    public Rec_Global(WebDriver _driver){
        ps5 = _driver;
    }

    public void Escribir(String texto, String xpath){
        ps5.findElement(By.xpath(xpath)).sendKeys(texto);
    }



}
