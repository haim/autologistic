package com.estafeta.test.stepdefinitions;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class HomePageSteps {

   public DriversPageSteps openDriversPage(){
       $(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div/ul[2]/li/a/span")).shouldBe(Condition.visible).click();
       $(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div/ul[2]/li/a/span/../../ul")).shouldBe(Condition.visible).click();
       return page(DriversPageSteps.class);
   }
}
