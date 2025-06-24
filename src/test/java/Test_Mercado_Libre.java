import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Test_Mercado_Libre {
    WebDriver driver;
    String url = "https://www.mercadolibre.com/";

    @Before
    public void antes() {

        Rec_Navegador rec_navegador = new Rec_Navegador();
        driver = rec_navegador.abrirnavegador(url);

    }

    @Test
    public void BuscarPagina() {
        Rec_Global buscar = new Rec_Global(driver);
        String btnMexico = "//*[@id=\"MX\"]";
        driver.findElement(By.xpath(btnMexico)).click();
        buscar.Escribir("playstation 5", "//*[@id=\"cb1-edit\"]");
        String btnBuscar = "/html/body/header/div/div[2]/form/button";
        driver.findElement(By.xpath(btnBuscar)).click();
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.cssSelector(".cookie-consent-banner-opt-out__actions button")))
                    .click();
        } catch (TimeoutException e) {
            // No hacer nada si no aparece el banner
        }
        String btnNuevo = "//*[@id=\"root-app\"]/div/div[2]/aside/section[2]/div[5]/ul/li[1]/a/span[1]";
        driver.findElement(By.xpath(btnNuevo)).click();

        try {
            // Primero hacer clic en la sección de ubicación para expandirla
            WebElement ubicacionSection = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//h3[contains(text(),'Ubicación')]")));
            ubicacionSection.click();

            // Esperar a que carguen las opciones
            Thread.sleep(1000); // Pequeña pausa para que cargue el DOM

            // Localizar y hacer clic en Estado de México usando texto visible
            WebElement estadoMexico = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[contains(text(),'Estado De México')]")));
            estadoMexico.click();

            // Esperar a que se aplique el filtro
            new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("estado-de-mexico"));

        } catch (Exception e) {
            System.out.println("Error al aplicar filtro de ubicación: " + e.getMessage());
        }

        try {
            // Primero hacer clic en la sección de ordenar
            WebElement btnOrdenarPor = new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[contains(text(),'Ordenar por') or contains(text(),'Más relevantes')]")));
            btnOrdenarPor.click();

            // Esperar a que carguen las opciones
            Thread.sleep(1000); // Pequeña pausa para que cargue el DOM

            // Seleccionar "Mayor precio"
            WebElement mayorPrecio = new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("/html/body/main/div/div[2]/section/div[2]/div[2]/div/div/div[2]/div/div/div/div/div/ul/li[3]/div/div/span")));
            mayorPrecio.click();

        } catch (Exception e) {
            System.out.println("Error al aplicar filtro de ordenación: " + e.getMessage());
            e.printStackTrace(); // Esto dará más detalles del error
        }
    //////////////////
        try {
            new WebDriverWait(driver, 10).until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.ui-search-layout__item"))
            );

            List<WebElement> productos = driver.findElements(By.cssSelector("li.ui-search-layout__item"));

            System.out.println("=== Primeros 5 productos ===");

            for (int i = 0; i < Math.min(5, productos.size()); i++) {
                WebElement producto = productos.get(i);

                String titulo = producto.findElement(By.cssSelector("a.poly-component__title")).getText();

                // Buscar línea de precio (puede tener varias partes)
                WebElement precioContenedor = producto.findElement(By.cssSelector("span.andes-money-amount__fraction"));
                String precio = precioContenedor.getText().replace("\n", " ");

                System.out.println((i + 1) + ". " + titulo + " - Precio: " + precio);
            }

        } catch (Exception e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
            e.printStackTrace();
        }


    }


}





