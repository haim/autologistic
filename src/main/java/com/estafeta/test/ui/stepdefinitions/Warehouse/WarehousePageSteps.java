package com.estafeta.test.ui.stepdefinitions.Warehouse;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.data.warehouse.WarehouseData;
import com.estafeta.test.ui.stepdefinitions.logistics.lots.blocks.SearchLotsBlock;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class WarehousePageSteps {

  SearchLotsBlock searchLotsBlock = new SearchLotsBlock();

  @Step
  public WarehousePageSteps searchVehicalsByVin(List<String> vins) {
    searchLotsBlock.searchLotInGridByVin(vins);
    $(By.id("loadingOverlay")).waitUntil(Condition.disappear, 20000);
    return page(WarehousePageSteps.class);
  }

  @Step
  public List<String> getVehicesWarehouseStatusFromGrid() {
    return $$(By.xpath("//div[@class='k-grid-content']/table[@role='grid']//tr/td[11]")).texts();
  }

  @Step
  public WarehousePageSteps open() {
    Selenide.open("/VehiclesInWarehouses/List");
    return page(WarehousePageSteps.class);
  }

  @Step
  public List<WarehouseData> getWarehouseDataFromGrid() {

    List<WarehouseData> warehouseDataList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@class='k-grid-content']/table[@role='grid']/tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));
    int row_count = rows.size();

    for (int row = 0; row < row_count; row++) {
      List<SelenideElement> columns_row = rows.get(row).$$(By.xpath("td"));

      String vin = columns_row.get(0).text();
      String warehouse = columns_row.get(10).text();
      String transportOrder = columns_row.get(11).text();
      String consignee = columns_row.get(14).text();

      warehouseDataList.add(
          new WarehouseData.Builder()
              .vin(vin)
              .transportOrder(transportOrder)
              .warehouse(warehouse)
              .consignee(consignee)
              .build());
    }
    return warehouseDataList;
  }
}
