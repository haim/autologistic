package com.estafeta.test.ui.stepdefinitions.logistics.lots;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.data.lots.LotData;
import com.estafeta.test.ui.stepdefinitions.logistics.lots.blocks.SearchLotsBlock;
import io.qameta.allure.Step;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class LotsPageSteps {

  SearchLotsBlock searchLotsBlock = new SearchLotsBlock();

  @Step
  public LotsPageSteps open() {
    Selenide.open("/Lots/List");
    return page(LotsPageSteps.class);
  }

  @Step
  public LotsPageSteps searchTransportOrdersByVins(List<String> vin) throws InterruptedException {
    searchLotsBlock.searchLotInGridByVin(vin);
    Thread.sleep(10000);
    return page(LotsPageSteps.class);
  }

  @Step
  public String getLotStatusFromFrid(String lotNumber) {
    return $(By.xpath(
            "//tbody[@role='rowgroup']/tr/td/a[contains(text(),'" + lotNumber + "')]/../../td[2]"))
        .text();
  }

  @Step
  public LotProfilePageStep findLotBySearchAndOpen(String lotNumber) {
    searchLotsBlock.searchLotInGridByLotNumber(lotNumber);
    openLot(lotNumber);
    return page(LotProfilePageStep.class);
  }

  @Step
  public String findLotBySearchAndGetStatus(String lotNumber) {
    searchLotsBlock.searchLotInGridByLotNumber(lotNumber);
    return getLotStatusFromFrid(lotNumber);
  }

  @Step
  public LotsPageSteps findLotBySearch(String lotNumber) {
    searchLotsBlock.searchLotInGridByLotNumber(lotNumber);
    return page(LotsPageSteps.class);
  }

  @Step("Search '{0}'")
  public LotsPageSteps searchInGrid(List<String> vin) {
    SelenideElement s =
        $(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"));
    if (s.is(Condition.exist)) {
      $x("//a[@title='Clear filters']").click();
    }

//    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//    clipboard.setContents(new StringSelection(String.valueOf(vin)), null);
//
//    $("#mineKWidget > input").clear();
//    $("#mineKWidget > input").sendKeys(Keys.CONTROL, "V");

    String join = "'" + StringUtils.join(vin.toArray(), "','") +"'";
    System.out.println(join);

    executeJavaScript("$('#vehiclesMultiSelect').data('kendoMultiSelect').setDataSource(["+join+"]);");
    executeJavaScript("$('#vehiclesMultiSelect').data('kendoMultiSelect').value(["+join+"]);");

    $(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"))
        .waitUntil(Condition.visible, 2000);
    $$(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"))
        .shouldHaveSize(vin.size());

    $(By.xpath("//a[@title = 'Apply filters']")).click();
    $(By.id("loadingOverlay")).waitUntil(Condition.disappear, 20000);
    return page(LotsPageSteps.class);
  }

  @Step
  public LotProfilePageStep openLot(String lotnumber) {
    $(By.xpath("//table[@role='grid']//tr/td/a")).shouldHave(Condition.text(lotnumber)).click();
    return page(LotProfilePageStep.class);
  }

  @Step
  public List<LotData> getLotsDataFromGrid() {

    List<LotData> lotDataList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@class='k-grid-content']/table[@role='grid']/tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));

    for (int row = 0; row < rows.size(); row++) {
      String lotNUmber = rows.get(row).$$(By.xpath("td")).get(0).text();
      String state = rows.get(row).$$(By.xpath("td")).get(1).text();
      String truckType = rows.get(row).$$(By.xpath("td")).get(2).text();
      String truck = rows.get(row).$$(By.xpath("td")).get(5).text();
      String loadingPoint = rows.get(row).$$(By.xpath("td")).get(6).text();

      lotDataList.add(
          new LotData.Builder()
              .lotNumber(lotNUmber)
              .state(state)
              .truckType(truckType)
              .truck(truck)
              .loadingPoint(loadingPoint)
              //              .creationDate(creationDate)
              .build());
    }
    return lotDataList;
  }
}
