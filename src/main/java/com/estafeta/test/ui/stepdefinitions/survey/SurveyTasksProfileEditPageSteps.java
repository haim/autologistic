package com.estafeta.test.ui.stepdefinitions.survey;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SurveyTasksProfileEditPageSteps {

  @Step
  public SurveyTasksProfilePageSteps completeExecuting() {
    $(By.id("submitButton")).click();
    return page(SurveyTasksProfilePageSteps.class);
  }

  @Step
  public SurveyTasksProfileEditPageSteps setBeforeLoading(String date, String time) {
    selectDate(date);
    selectTime(time);
    return page(SurveyTasksProfileEditPageSteps.class);
  }

  @Step
  public SurveyTasksProfileEditPageSteps selectDate(String date) {
      $(By.xpath("//input[@id='ActualEndDate']")).setValue(date).pressTab();
    return page(SurveyTasksProfileEditPageSteps.class);
  }

  @Step
  public SurveyTasksProfileEditPageSteps selectTime(String time) {
    $(By.xpath("//input[@id='ActualEndTime']")).setValue(String.valueOf(time));
    return page(SurveyTasksProfileEditPageSteps.class);
  }

  @Step
  public SurveyTasksProfileEditPageSteps openEquipmentTab() {
    $(By.xpath("//li[@id='EquipmentTabHeader']/a")).click();
    $(By.id("allAvailable")).shouldBe(Condition.visible);
    return page(SurveyTasksProfileEditPageSteps.class);
  }

  @Step
  public SurveyTasksProfileEditPageSteps openPhotoTab() {
    $(By.xpath("//li[@id='PhotosTabHeader']/a")).click();
    $(By.id("PhotosTabContent")).shouldBe(Condition.visible);
    return page(SurveyTasksProfileEditPageSteps.class);
  }

  @Step
  public SurveyTasksProfileEditPageSteps clickAllAvailable() {

    $(By.id("allAvailable")).click();
    return page(SurveyTasksProfileEditPageSteps.class);
  }

  @Step
  public SurveyTasksProfileEditPageSteps uploadPhotoToChecklist(String file) {
    $(By.xpath("//div[@id='PhotosTabContent']/div[1]/div[1]//input[@name='fileInput']"))
        .uploadFile(new File(file));
    //    $(By.xpath(
    //            "//a[@class='deleteLink']"))
    //        .waitUntil(Condition.visible, 20000);
    $(By.xpath("//ul[@class='contentBlockPhoto']/li")).waitUntil(Condition.visible, 20000);
    return page(SurveyTasksProfileEditPageSteps.class);
  }

  @Step
  public SurveyTasksProfileEditPageSteps openSurveyTab() {
    $(By.id("SurveyTabHeader")).click();
    $(By.id("Speedometer")).shouldBe(Condition.visible);
    return page(SurveyTasksProfileEditPageSteps.class);
  }

  @Step
  public SurveyTasksProfileEditPageSteps setSpeedometer(String km) {
    $(By.id("Speedometer")).setValue(km);
    return page(SurveyTasksProfileEditPageSteps.class);
  }
}
