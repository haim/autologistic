package com.estafeta.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.estafeta.test.data.DriverData;
import com.estafeta.test.stepdefinitions.DriversPageSteps;
import com.estafeta.test.stepdefinitions.HomePageSteps;
import com.estafeta.test.stepdefinitions.LoginPageSteps;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;


public class DriversTest {

    @BeforeClass
    public static void initSettings() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.browser = "chrome";
        Configuration.reportsFolder = "target/reports";
    }

    @Test
    public void create_new_driver() {

        LoginPageSteps loginPageSteps = open("https://amt2.estafeta.org/Account/LogOn?ReturnUrl=%2f", LoginPageSteps.class);
        HomePageSteps homePageSteps = new HomePageSteps();
        DriversPageSteps driversPageSteps = new DriversPageSteps();
        loginPageSteps.login("test user", "test user");
        assertEquals($(By.xpath("//h1")).getText(), "Welcome to Estafeta Automotive");
        homePageSteps.openDriversPage();
        driversPageSteps.initDriverCreation()
                .fillDriverForm(new DriverData("lname", "fname", "sname",
                        "test@teset.com", "enlname", "enfname", "ensname", "English"));
        driversPageSteps.submitContactCreation();
        driversPageSteps.findDriverAndOpenProfile("lname");
        Assert.assertEquals($(By.id("LastName")).getValue(), "lname");
    }

    @Test
    public void create_driver_with_incorrect_date(){
        LoginPageSteps loginPageSteps = open("https://amt2.estafeta.org/Account/LogOn?ReturnUrl=%2f", LoginPageSteps.class);
        HomePageSteps homePageSteps = new HomePageSteps();
        DriversPageSteps driversPageSteps = new DriversPageSteps();
        loginPageSteps.login("test user", "test user");
        assertEquals($(By.xpath("//h1")).getText(), "Welcome to Estafeta Automotive");
        homePageSteps.openDriversPage();
        driversPageSteps.initDriverCreation()
                .fillDriverForm(new DriverData(null, "fname", "sname",
                        "test@teset.com", "enlname", "enfname", "ensname", "English"));
        driversPageSteps.submitContactCreation();
        Assert.assertEquals(driversPageSteps.getDriverCreationError(), "Fill in the field Last Name");

    }
}
