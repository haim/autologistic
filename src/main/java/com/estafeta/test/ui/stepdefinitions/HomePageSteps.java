package com.estafeta.test.ui.stepdefinitions;

import com.codeborne.selenide.Condition;
import com.estafeta.test.ui.stepdefinitions.vehicles.VehiclesGeneralSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class HomePageSteps {

  @Step
  public DriversPageSteps openDriversPage() {
    $(By.xpath("//*[@id='wrap']/div[1]/div/div/ul[2]/li/a/span"))
        .shouldBe(Condition.visible)
        .click();
    $(By.xpath("//*[@id='wrap']/div[1]/div/div/ul[2]/li/a/span/../../ul"))
        .shouldBe(Condition.visible)
        .click();
    return page(DriversPageSteps.class);
  }

  @Step
  public String getWelcomeMessage() {
    return $(By.xpath("//h1")).getText();
  }

  @Step
  public VehiclesGeneralSteps openVehiclesPage() {
    $(By.xpath("//span[contains(text(),'Vehicles')]")).shouldBe(Condition.visible).click();
    $$(By.xpath("//ul[@class='dropdown-menu']/li/a"))
        .findBy(Condition.text("Vehicles"))
        .shouldBe(Condition.visible)
        .click();
    return page(VehiclesGeneralSteps.class);
  }

  @Step
  public LoginPageSteps openLottingPage() {
    $(By.xpath("//span[contains(text(),'Logistics')]")).shouldBe(Condition.visible).click();
    $$(By.xpath("//ul[@class='dropdown-menu']/li/a"))
        .findBy(Condition.text("Lotting"))
        .shouldBe(Condition.visible)
        .click();
    return page(LoginPageSteps.class);
  }
}
