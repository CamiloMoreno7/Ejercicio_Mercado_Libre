import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Rec_Navegador {
    private static final Logger logger = LoggerFactory.getLogger(Rec_Navegador.class);
    WebDriver driver;
    public ChromeDriver abrirnavegador(String url) {


        // Opciones para el navegador (opcional)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("-incognito");

            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\USUARIO\\Documents\\Curso Java\\Ejercicio_Mercado_Libre\\src\\main\\resources\\chromedriver.exe");
            driver = new ChromeDriver(options);
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
            return(ChromeDriver) driver;

    }
}
