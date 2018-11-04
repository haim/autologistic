package com.estafeta.test.ui.stepdefinitions.logistics.lotting.blocks;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class BorderCrossingBlock {

  @Step
  public BorderCrossingBlock selectComapy(String compay) {
    $(By.xpath("//input[@name='BorderCrossingCompanyId_input']")).setValue(compay);
    $$(By.xpath("//ul[@id='BorderCrossingCompanyId_listbox']/li/div[2]/span"))
        .findBy(Condition.text(compay))
        .click();
    $(By.xpath("//input[@value='OK']")).click();
    return page(BorderCrossingBlock.class);
  }
}
