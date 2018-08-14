package com.estafeta.test.stepdefinitions;

import com.codeborne.selenide.Condition;
import com.estafeta.test.data.DriverData;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class DriversPageSteps {

    public String getDriverCreationError(){
      return  $(By.id("lastNameRequiredValidationMessage")).shouldBe(Condition.visible).getText();
    }

    public DriversPageSteps initDriverCreation(){
        $(By.xpath("//a[@class='k-button' and text()='Create']")).shouldBe(Condition.visible).click();
        return page(DriversPageSteps.class);
    }

    public DriversPageSteps returnToDriversPage(){
        $(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div/ul[2]/li/a/span")).shouldBe(Condition.visible).click();
        $(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div/ul[2]/li/a/span/../../ul")).shouldBe(Condition.visible).click();
    return  page(DriversPageSteps.class);
    }

    public DriversPageSteps submitContactCreation(){
        $(By.id("saveButton")).shouldBe(Condition.visible).click();
        return page(DriversPageSteps.class);
    }


    public void fillDriverForm(DriverData driverData){

        $(By.id("LastName")).setValue(driverData.getLastName());
        $(By.id("FirstName")).setValue(driverData.getFirstName());
        $(By.id("SecondName")).setValue(driverData.getSecondName());
        $(By.id("Email")).setValue(driverData.getEmail());
        $(By.id("EnglishLastName")).setValue(driverData.getEnglishLastName());
        $(By.id("EnglishFirstName")).setValue(driverData.getEnglishFristName());
        $(By.id("EnglishSecondName")).setValue(driverData.getEnglishSecondName());
        $(By.xpath("//select[@id='CultureId']")).selectOption(driverData.getLanguage());

    }

    public DriversPageSteps findDriverAndOpenProfile(String name){
        $(By.id("searchText")).shouldBe(Condition.visible).setValue(name);
        $(By.xpath("//i[@class='icon-search']")).click();
        $(By.xpath("//tbody[@role='rowgroup']/tr/td/a/span")).shouldHave(Condition.text(name)).click();
        return page(DriversPageSteps.class);
    }

}
