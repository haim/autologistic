package com.estafeta.test.ui.stepdefinitions.transports;

import com.codeborne.selenide.Condition;
import com.estafeta.test.ui.data.transports.transports.TransportData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class TransportCreatePageSteps {

  @Step
  public TransportCreatePageSteps fillForms(TransportData transportData) {
    $(By.name("OwnerId_input"))
        .setValue(transportData.getOwner())
        .$$(By.xpath("//ul[@id='OwnerId_listbox']/li//span"))
        .findBy(Condition.text(transportData.getOwner()))
        .click();

    $(By.name("TransportHeadId_input"))
        .setValue(transportData.getTransport())
        .$$(By.xpath("//ul[@id='TransportHeadId_listbox']/li//span"))
        .findBy(Condition.text(transportData.getTransport()))
        .click();

    $(By.name("TransportPartId_input"))
        .setValue(transportData.getTransportPart())
        .$$(By.xpath("//ul[@id='TransportPartId_listbox']/li//span"))
        .findBy(Condition.text(transportData.getTransportPart()))
        .click();

    $(By.name("FirstDriverId_input"))
        .setValue(transportData.getFirstDriver())
        .$$(By.xpath("//ul[@id='FirstDriverId_listbox']/li//span"))
        .findBy(Condition.text(transportData.getFirstDriver()))
        .click();

    $(By.id("saveButton")).click();
    return page(TransportCreatePageSteps.class);
  }
}
