package com.estafeta.test.ui.stepdefinitions.logistics.lotting.blocks;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class ImportLotsBlock {

  @Step
  public ImportLotsBlock filldData(
      String client, String shippingAgent, String from, String to, String file) {
    $(By.xpath("//input[@name='ClientCompanyId_input']")).setValue(client);
    $$(By.xpath("//ul[@id='ClientCompanyId_listbox']/li")).findBy(Condition.text(client)).click();
    $(By.xpath("//input[@name='ShippingAgentId_input']")).setValue(shippingAgent);
    $$(By.xpath("//ul[@id='ShippingAgentId_listbox']/li"))
        .findBy(Condition.text(shippingAgent))
        .click();
    $(By.xpath("//input[@name='FromId_input']")).val(from);
    $$(By.xpath("//ul[@id='FromId_listbox']/li")).findBy(Condition.text(from)).click();
    $(By.xpath("//input[@name='ToId_input']")).val(to);
    $$(By.xpath("//ul[@id='ToId_listbox']/li")).findBy(Condition.text(to)).click();
    $(By.xpath("//input[@name='fileInput']")).uploadFile(new File(file));
    $(By.xpath("//li[@name='FileIds']")).waitUntil(Condition.visible, 20000);
    $(By.xpath("//input[@id='okButton']")).click();
    return page(ImportLotsBlock.class);
  }
}
