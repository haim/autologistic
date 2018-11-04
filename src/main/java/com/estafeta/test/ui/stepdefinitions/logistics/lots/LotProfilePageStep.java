package com.estafeta.test.ui.stepdefinitions.logistics.lots;

import com.estafeta.test.ui.stepdefinitions.logistics.LogisticTaskProfilePageSteps;
import com.estafeta.test.ui.stepdefinitions.logistics.lotting.blocks.LotEditViewSteps;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class LotProfilePageStep {

  @Step
  public LotProfilePageStep approveLot() {
    initEditMode();

    return page(LotProfilePageStep.class);
  }

  @Step
  public LotEditViewSteps initEditMode() {
    $x("//a[@class='k-button'][text()= 'Change']").click();
    return page(LotEditViewSteps.class);
  }

  @Step
  public LotProfilePageStep openTasksTab() {
    $x("//a[text()= 'Tasks']").click();
    return page(LotProfilePageStep.class);
  }

  @Step
  public LogisticTaskProfilePageSteps openLogisticTask(String taskType, String company) {
    $x("//div[@id='logisticTasksGrid']//tbody[@role='rowgroup']//td[2][text()='"
            + taskType
            + "']/../td[4][text()='"
            + company
            + "']/../td[1]/a")
        .click();
    return page(LogisticTaskProfilePageSteps.class);
  }

  @Step
  public String getLogisticTaskStatus(String taskType, String company) {
    return $x("//div[@id='logisticTasksGrid']//tbody[@role='rowgroup']//td[2][text()='"
            + taskType
            + "']/../td[4][text()='"
            + company
            + "']/../td[3]")
        .text();
  }

  @Step
  public int getLogisticTasks() {
    return $$x("//div[@id='logisticTasksGrid']//tbody/tr").size();
  }

  @Step
  public String ifTaskExist(String taskType, String company) {
    return $x("//div[@id='logisticTasksGrid']//tbody[@role='rowgroup']//td[2][text()='"
            + taskType
            + "']/../td[4][text()='"
            + company
            + "']/../td[3]")
        .text();
  }
}
