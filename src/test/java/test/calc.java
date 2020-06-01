package test;


import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.lang.model.element.Element;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


public class calc {
    @Test
    public void test() throws MalformedURLException {

        InputStreamReader fileInputStream;
        //инициализируем специальный объект Properties
        //типа Hashtable для удобной работы с данными
        Properties prop = new Properties();

        try {
            //обращаемся к файлу и получаем данные
            fileInputStream = new InputStreamReader(this.getClass().getResourceAsStream("/CONFIG.properties"), "UTF-8");
            prop.load(fileInputStream);





        } catch (Exception e) {
            System.out.println("Ошибка в программе: файл  не обнаружен");
            e.printStackTrace();
        }

        String var1 = prop.getProperty("input");
        String os = prop.getProperty("os");
        String ten = prop.getProperty("ten");
        String ws = prop.getProperty("Ws");
        String pit = prop.getProperty("pit");
        String isxod = prop.getProperty("isxod");
        String rez = prop.getProperty("rez");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", os);
        caps.setCapability("platformVersion", ten);
        caps.setCapability("deviceName", ws);
        caps.setCapability("app", pit);
        AppiumDriver driver = new AppiumDriver<>(new URL("http://localhost"
                + ":9999"),
                caps);

        WebElement element = driver.findElementByName(isxod);
        element.sendKeys(var1);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String app;
        if (element.getAttribute("Name").equals(rez))
        {

            app = "Тест пройден 2+2 равно 3";
        }
        else {
            app = "Тест не пройден 2+2 не равно 3";
        }
        try(FileWriter writer = new FileWriter("Результат.txt", true))
        {
            writer.append(app);

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }






        Assertions.assertEquals(element.getText(),rez);



    }
}
