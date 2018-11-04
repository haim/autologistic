package com.estafeta.test.ui.stepdefinitions.survey;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.data.survey.SurveyTaskData;
import com.estafeta.test.ui.data.vehicles.VehicleData;
import io.qameta.allure.Step;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class SurveyTasksPageSteps {

  @Step
  public SurveyTasksPageSteps open() {
    Selenide.open("/SurveyTasks/List/New");
    return page(SurveyTasksPageSteps.class);
  }

  @Step
  public SurveyTasksPageSteps searchSurveyTasksinGrid(List<String> vin) {
    SelenideElement s =
        $(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"));
    if (s.is(Condition.exist)) {
      $x("//a[@title='Clear filters']").click();
    }

    $(By.id("loadingOverlay")).waitUntil(Condition.disappear, 20000);
    String join = "'" + StringUtils.join(vin.toArray(), "','") + "'";
    System.out.println(join);

    executeJavaScript(
        "$('#vehiclesMultiSelect').data('kendoMultiSelect').setDataSource([" + join + "]);");
    executeJavaScript("$('#vehiclesMultiSelect').data('kendoMultiSelect').value([" + join + "]);");

    $(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"))
        .waitUntil(Condition.visible, 20000);
    $$(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"))
        .shouldHaveSize(vin.size());

    $(By.xpath("//a[@title = 'Apply filters']")).click();
    $(By.id("loadingOverlay")).waitUntil(Condition.disappear, 20000);
    return page(SurveyTasksPageSteps.class);
  }

  @Step
  public List<SelenideElement> getLinkOfAllSurveyTasks() {

    List<SelenideElement> surveyTasksList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@id='grid']//tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));

    System.out.println("Survey amount: " + rows);
    for (int row = 0; row < rows.size(); row++) {
      SelenideElement link = rows.get(row).shouldBe(Condition.visible).$(By.xpath("td/a"));
      System.out.println("link: " + link.text());

      surveyTasksList.add(link);
    }
    return surveyTasksList;
  }

  @Step
  public List<SurveyTaskData> getLinkOfAllSurveyTasks2() {

    List<SurveyTaskData> surveyTasksList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@id='grid']//tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));

    System.out.println("Survey amount: " + rows);
    for (int row = 0; row < rows.size(); row++) {
      String link = rows.get(row).shouldBe(Condition.visible).$(By.xpath("td/a")).getAttribute("href");

      surveyTasksList.add(new SurveyTaskData.Builder().link(link).build());
    }
    return surveyTasksList;
  }

  @Step
  public List<String> getStatusOfAllServeyTasks() {
    List<String> surveyStatusTasksList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@id='grid']//tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));

    System.out.println("Survey amount: " + rows);
    for (int row = 0; row < rows.size(); row++) {
      String status = rows.get(row).$$(By.xpath("td")).get(4).text();

      surveyStatusTasksList.add(status);
    }
    return surveyStatusTasksList;
  }

  @Step
  public void performSurveyTasksFromGrid(
          List<SurveyTaskData> surveyList,
          String photo,
          String beforeDate,
          String beforeTime,
          String speedometer) {
    for (SurveyTaskData link : surveyList) {
     // System.out.println("survey tasks clicked:  "+surveyList.get(i).getText());
      Selenide.open(link.getLink());
      //surveyList.remove(surveyList.get(i));
//      switchTo().window(1);
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
//      switchTo().window(1).close();
//      switchTo().window(0);
     // refresh();
      new SurveyTasksPageSteps().open();
      refresh();
    }
  }
  @Step
  public void performSurveyTasksFromGrid2(
          List<String> vehicleDataList,
          List<SelenideElement> surveyList,
          String photo,
          String beforeDate,
          String beforeTime,
          String speedometer) {

    for (SelenideElement task : surveyList) {
      task.click();
//      switchTo().window(1);
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
//      switchTo().window(1).close();
//      switchTo().window(0);
      refresh();
      new SurveyTasksPageSteps().open();
      refresh();
    }
  }
}
