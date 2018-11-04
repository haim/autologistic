package com.estafeta.test.ui.stepdefinitions.logistics;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.stepdefinitions.LoginPageSteps;
import com.estafeta.test.ui.stepdefinitions.blocks.DocumentStepsBlock;
import com.estafeta.test.ui.stepdefinitions.survey.SurveyTasksProfileEditPageSteps;
import com.estafeta.test.ui.stepdefinitions.survey.SurveyTasksProfilePageSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class LogisticTaskProfilePageSteps {

  DocumentStepsBlock documentStepsBlock = new DocumentStepsBlock();

  @Step
  public LogisticTaskProfilePageSteps fillDateAndTime(String date, String time) {
    selectDate(date);
    selectTime(time);
    $(By.id("loadingOverlay")).should(Condition.disappear);
    return page(LogisticTaskProfilePageSteps.class);
  }

  // todo need create separate methods
  @Step
  public LogisticTaskProfilePageSteps save() {
    if($(By.xpath("//input[@id='saveButton']")).isDisplayed()){
    $(By.xpath("//input[@id='saveButton']")).click();}
    if ($(By.xpath("//a[@id='saveButtonTask']")).isDisplayed()){
    $(By.xpath("//a[@id='saveButtonTask']")).click();}
    $(By.id("loadingOverlay")).should(Condition.disappear);
    // todo  - extend verification in which cases should be this checking
    if ($(By.xpath("//div[@id='confirm']")).isDisplayed()) {
      $(By.xpath("//a[@id='confirmOk']")).click();
      $(By.id("loadingOverlay")).should(Condition.disappear);
    }
      return page(LogisticTaskProfilePageSteps.class);
  }

  @Step
  public LogisticTaskProfilePageSteps selectDate(String date) {
    $(By.xpath("//input[@data-role='datepicker']")).setValue(date).pressTab();
    return page(LogisticTaskProfilePageSteps.class);
  }

  @Step
  public LogisticTaskProfilePageSteps selectTime(String time) {
    $(By.xpath("//input[@id='ActualTime']")).setValue(String.valueOf(time));
    return page(LogisticTaskProfilePageSteps.class);
  }

  public LogisticTaskProfilePageSteps switchToDocumentsTabAndAttacheFile(String type, String file) {
    $(By.xpath("//li[@id='documentsTabHeader']")).click();
    $(By.xpath("//a[@id='addDocumentButton']")).click();

    $(By.xpath("//span[@class='k-widget k-dropdown k-header span12']")).click();
    $$(By.xpath("//ul[@id='DocumentTypeId_listbox']/li")).findBy(Condition.text(type)).click();

    $(By.xpath("//input[@name='fileInput']")).uploadFile(new File(file));
    $(By.xpath("//input[@id='saveDocumentButton']")).click();
    $(By.id("loadingOverlay")).waitUntil(Condition.disappear, 20000);
    return page(LogisticTaskProfilePageSteps.class);
  }

  @Step
  public LogisticTaskProfilePageSteps openDocumentsTab() {
    $x("//li[@id='documentsTabHeader']/a").click();
    return page(LogisticTaskProfilePageSteps.class);
  }

  @Step
  public LogisticTaskProfilePageSteps addDocument(String type, String file) {
    $(By.xpath("//a[@id='addDocumentButton']")).click();
    documentStepsBlock.addDocument(type, file);
    $(By.xpath("//div[@id='documentEditView']")).waitUntil(Condition.disappear, 20000);

    return page(LogisticTaskProfilePageSteps.class);
  }

  @Step
  public LoginPageSteps selectCompany(String companyName) {
    $(By.xpath("//span[@class='k-widget k-dropdown k-header span12']")).click();
    $$(By.xpath("//ul[@class='k-list k-reset']/li")).findBy(Condition.text(companyName)).click();
    $(By.xpath("//button[@class='k-button btn-primary pull-right']")).click();
    return page(LoginPageSteps.class);
  }

  @Step
  public void performAllSurveyTasksOfLoadingOrdersToComplete(
      List<SelenideElement> surveyList,
      String photo,
      String beforeDate,
      String beforeTime,
      String speedometer) {
    for (SelenideElement task : surveyList) {
      task.click();
      switchTo().window(2);
      new SurveyTasksProfilePageSteps().startExecuting(); // todo anable
      new SurveyTasksProfileEditPageSteps()
          .setSpeedometer(speedometer)
          .openPhotoTab()
          .uploadPhotoToChecklist(photo)
          .openEquipmentTab()
          .clickAllAvailable()
          .setBeforeLoading(beforeDate, beforeTime)
          .completeExecuting();
      $(By.xpath("//div[@class='loadingContainer']")).waitUntil(Condition.disappear, 20000);
      switchTo().window(2).close();
      switchTo().window(1);
      refresh();
    }
    //    lotProfilePageStep.openLogisticTask
    //    getLinkOfAllSurveyTasks()
  }

  @Step
  public List<SelenideElement> getLinkOfAllSurveyTasks() {

    List<SelenideElement> surveyTasksList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@id='vehiclesGrid']/div[2]//tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));

    System.out.println("Survey amount: " + rows);
    for (int row = 0; row < rows.size(); row++) {
      SelenideElement link = rows.get(row).$(By.xpath("td/a"));

      surveyTasksList.add(link);
    }
    return surveyTasksList;
  }

  @Step
  public List<String> getStatusOfAllServeyTasks() {
    List<String> surveyStatusTasksList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@id='vehiclesGrid']/div[2]//tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));

    System.out.println("Survey amount: " + rows);
    for (int row = 0; row < rows.size(); row++) {
      String status = rows.get(row).$$(By.xpath("td")).get(8).text();

      surveyStatusTasksList.add(status);
    }
    return surveyStatusTasksList;
  }
}
