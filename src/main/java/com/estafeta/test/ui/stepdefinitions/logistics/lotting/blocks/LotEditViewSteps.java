package com.estafeta.test.ui.stepdefinitions.logistics.lotting.blocks;

import com.codeborne.selenide.Condition;
import com.estafeta.test.ui.stepdefinitions.logistics.lots.LotsPageSteps;
import com.estafeta.test.ui.stepdefinitions.logistics.lotting.LottingSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class LotEditViewSteps {

  BorderCrossingBlock borderCrossingBlock = new BorderCrossingBlock();

  @Step
  public LotsPageSteps closeLotEditViewWindow() {
    $(By.xpath("//div[@id='lotEditView']/div/button[@class='close']")).click();
    return page(LotsPageSteps.class);
  }

  @Step
  public String getVinId(String vin) {
    return $$(By.xpath("//div[@id='warehouseVehiclesGrid']//tbody[@role='rowgroup']/tr/td[1]"))
        .findBy(Condition.text(vin))
        .getText();
  }

  @Step
  public LotEditViewSteps addBorderCrossing(
      String borderCrossingName, String borderCrossingDate, String borderCrossingTime) {
    $(By.xpath("//input[@id='addBorderCrossingCompanyButton']")).click();
    borderCrossingBlock.selectComapy(borderCrossingName);
    $(By.xpath(
            "//ul[@id='sortable']//label[text()='Border Crossing']/../..//label[contains(text(), '"
                + borderCrossingName
                + "')]"))
        .waitUntil(Condition.visible, 20000);
    updateExistBorderCrossingInOperations(
        borderCrossingName, borderCrossingDate, borderCrossingTime);
    return page(LotEditViewSteps.class);
  }

  public LotEditViewSteps addBorderCrossing2(
      String borderCrossingName, String date, String time, String crossingTime) {
    $(By.xpath("//input[@id='addBorderCrossingCompanyButton']")).click();
    borderCrossingBlock.selectComapy(borderCrossingName);

    return page(LotEditViewSteps.class);
  }

  @Step
  public LotEditViewSteps fillFields(
      String truckType,
      String truck,
      String loadingDate,
      String loadingTime,
      String warrantyType,
      String borderCrossingName,
      String borderCrossingDate,
      String borderCrossingTime,
      String unloadingName,
      String date) {
    fillTruckType(truckType);
    fillTruck(truck);
    fillLoadingDate(loadingDate, loadingTime);
    selectWarrentyType(warrantyType);
    addBorderCrossing(borderCrossingName, borderCrossingDate, borderCrossingTime);
    updateExistUnloadingOperations(unloadingName, date);
    return page(LotEditViewSteps.class);
  }

  @Step
  public LotEditViewSteps fillTruckType(String truckType) {
    $(By.xpath("//span[@aria-owns='LotTransportTypeId_listbox']")).click();
    $$(By.xpath("//ul[@id='LotTransportTypeId_listbox']/li"))
        .findBy(Condition.text(truckType))
        .waitUntil(Condition.visible, 2000)
        .click();
    return page(LotEditViewSteps.class);
  }

  @Step
  public LotEditViewSteps fillTruck(String truck) {
    $(By.xpath("//input[@name='TransportId_input']")).setValue(truck);
    $$(By.xpath("//ul[@id='TransportId_listbox']/li/span"))
        .findBy(Condition.text(truck))
        .waitUntil(Condition.visible, 20000)
        .click();
    return page(LotEditViewSteps.class);
  }

  @Step
  public LotEditViewSteps fillLoadingDate(String date, String time) {
    selectDate(date);
    selectTime(time);
    return page(LotEditViewSteps.class);
  }

  @Step
  public LotEditViewSteps selectWarrentyType(String warrantyType) {
    $(By.xpath("//span[@aria-owns='CustomsWarrantyTypeId_listbox']")).click();
    $(By.xpath("//ul[@id='CustomsWarrantyTypeId_listbox']/li")).waitUntil(Condition.visible, 20000);
    $$(By.xpath("//ul[@id='CustomsWarrantyTypeId_listbox']/li"))
        .findBy(Condition.text(warrantyType))
        .click();
    return page(LotEditViewSteps.class);
  }

  @Step
  public LottingSteps updateExistBorderCrossingInOperations(
      String borderCrossingName, String date, String crossingTime) {

    $(By.xpath(
            "//ul[@id='sortable']//label[text()='Border Crossing']/../..//label[contains(text(), '"
                + borderCrossingName
                + "')]/../../div[@class='dateDiv']/span/span/input"))
        .setValue(date)
        .pressTab();
    $(By.xpath(
            "//ul[@id='sortable']//label[text()='Border Crossing']/../..//label[contains(text(), '"
                + borderCrossingName
                + "')]/../../..//input[@name='DurationTimeString']"))
        .setValue(crossingTime)
        .shouldHave(Condition.value(crossingTime));

    return page(LottingSteps.class);
  }

  @Step
  public LottingSteps updateExistUnloadingOperations(String unloadingName, String date) {
    $(By.xpath(
            "//ul[@id='sortable']//label[text()='Unloading']/../..//label[contains(text(), '"
                + unloadingName
                + "')]/../../div[4]//input"))
        .setValue(date)
        .pressTab();
    return page(LottingSteps.class);
  }

  @Step // todo  - create  separate api for unloading, loading, bordercrossing
  public LottingSteps updateExistUnloadingOperations2(
      String companyName, String operationType, String date, String time) {
    setOperationDate(companyName, operationType, date);
    setOperationTime(companyName, operationType, time);

    return page(LottingSteps.class);
  }

  private LotEditViewSteps setOperationDate(String companyName, String operationType, String date) {
    $(By.xpath(
            "//li[@data-company-name='"
                + companyName
                + "']//label[text()='"
                + operationType
                + "']/../..//input[@data-role='datepicker']"))
        .setValue(date);
    ;
    return page(LotEditViewSteps.class);
  }

  private LotEditViewSteps setOperationTime(String companyName, String operationType, String time) {
    $(By.xpath(
            "//li[@data-company-name='"
                + companyName
                + "']//label[text()='"
                + operationType
                + "']/../..//input[@class='hasTimeEntry']"))
        .setValue(time);
    return page(LotEditViewSteps.class);
  }

  @Step
  public LottingSteps selectDate(String date) {
    $(By.xpath("//input[@id='LoadingDate']")).setValue(date).pressTab();
    return page(LottingSteps.class);
  }

  @Step
  public LottingSteps selectTime(String time) {
    $(By.xpath("//input[@id='LoadingTime']")).setValue(String.valueOf(time));
    return page(LottingSteps.class);
  }

  @Step
  public LottingSteps approveRoute() {
    $(By.xpath("//input[@id='actionId-3']")).click();
    $x("//div[@id='loadingOverlay']").waitUntil(Condition.disappear, 20000);
    $x("//div[@id='lotEditView']").waitUntil(Condition.disappear, 20000);
    $x("//div[@id='loadingOverlay']").waitUntil(Condition.disappear, 20000);
    return page(LottingSteps.class);
  }
}
