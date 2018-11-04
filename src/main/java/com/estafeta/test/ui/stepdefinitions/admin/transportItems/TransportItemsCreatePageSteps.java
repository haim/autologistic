package com.estafeta.test.ui.stepdefinitions.admin.transportItems;

import com.codeborne.selenide.Condition;
import com.estafeta.test.ui.data.admin.transportItems.TransportItemsData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class TransportItemsCreatePageSteps {

  @Step
  public TransportItemsCreatePageSteps fillForms(TransportItemsData itemsData) {
    $(By.id("IsActive")).setSelected(itemsData.isActive());
    $(By.id("IsVerified")).setSelected(itemsData.isActive());
    $(By.id("TransportItemTypeId")).selectOptionContainingText(itemsData.getType());
    $(By.id("Name")).setValue(itemsData.getNumber());
    $(By.name("CarrierId_input"))
        .setValue(itemsData.getCarrier())
        .$$(By.xpath("//ul[@id='CarrierId_listbox']/li//span"))
        .findBy(Condition.text(itemsData.getCarrier()))
        .click();

    if (itemsData.isTrailer() == true) {
      $(By.id("IsPart")).setSelected(itemsData.isTrailer());
      $(By.id("partTransportItemTypeSection"))
          .waitUntil(Condition.visible, 20000)
          .$(
              By.xpath(
                  "//div[@id='partTransportItemTypeSection']/div[2]/div/span/span/span[@class='k-input']"))
          .click();
      $$(By.xpath("//ul[@id='PartTransportItemTypeId_listbox']/li"))
          .findBy(Condition.text(itemsData.getTransportItemPartType()))
          .click();
    }

    $(By.name("BrandId_input"))
        .setValue(itemsData.getBrand())
        .$$(By.xpath("//ul[@id='BrandId_listbox']/li"))
        .findBy(Condition.text(itemsData.getBrand()))
        .click();
    $(By.name("ModelId_input"))
        .setValue(itemsData.getModel())
        .$$(By.xpath("" + "//ul[@id='ModelId_listbox']/li"))
        .findBy(Condition.text(itemsData.getModel()))
        .click();
    $(By.xpath("//div[@id='DescriptionSection']//div[2]/div/span/span/span[@class='k-input']"))
        .click();
    $$(By.xpath("//ul[@id='DescriptionId_listbox']/li"))
        .findBy(Condition.text(itemsData.getDescription()))
        .click();

    $(By.id("saveButton")).click();
    return page(TransportItemsCreatePageSteps.class);
  }

  @Step
  public TransportItemsPageSteps confirmNewTransportItemPopUp(boolean yes) {
    $(By.id("confirm")).shouldBe(Condition.visible).$(By.id("confirmOk")).click();
    return page(TransportItemsPageSteps.class);
  }

  @Step
  public TransportItemsCreatePageSteps select(String roleName) {
    $$(By.xpath("//ul/li/label/span")).findBy(Condition.text(roleName)).click();
    return page(TransportItemsCreatePageSteps.class);
  }
}
