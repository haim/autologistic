package com.estafeta.test.ui.stepdefinitions.blocks;

import com.codeborne.selenide.Condition;
import com.estafeta.test.ui.stepdefinitions.logistics.LogisticTaskProfilePageSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class DocumentStepsBlock {

  @Step
  public LogisticTaskProfilePageSteps addDocument(String type, String file) {
    $(By.xpath("//div[@id='documentEditView']")).shouldBe(Condition.visible);
    $(By.xpath("//span[@aria-owns='DocumentTypeId_listbox']//span[@class='k-input']")).click();
    $$(By.xpath("//ul[@id='DocumentTypeId_listbox']/li")).findBy(Condition.text(type)).click();
    $(By.xpath("//div[@id='documentEditView']//input[@name='fileInput']"))
        .uploadFile(new File(file));
    $(By.xpath("//ul[@class='contentBlockPhoto']/li")).waitUntil(Condition.visible, 20000);
    $(By.xpath("//input[@id='saveDocumentButton']")).click();

    return page(LogisticTaskProfilePageSteps.class);
  }
}