package com.estafeta.test.ui.stepdefinitions.logistics.transportOrders;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.data.transportOrder.TransportOrderData;
import com.estafeta.test.ui.stepdefinitions.logistics.transportOrders.blocks.SearchTransportOrderBlock;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author dnikiforov
 * @project amt
 */
public class TransportOrdersSteps {

  SearchTransportOrderBlock searchTransportOrderBlock = new SearchTransportOrderBlock();

  private String LOGIN_PAGE_URL = "/TransportOrders/List";

  @Step
  public TransportOrdersSteps open() {
    Selenide.open(LOGIN_PAGE_URL);
    return this;
  }

  @Step
  public TransportOrdersSteps searchTransportOrdersByVins(List<String> vin) {
    searchTransportOrderBlock.searchInGrid(vin);
    $(By.id("loadingOverlay")).waitUntil(Condition.disappear, 20000);
    return page(TransportOrdersSteps.class);
  }

  @Step
  public List<TransportOrderData> getOrdersVinFromGrid() {

    List<TransportOrderData> orders = new ArrayList<>();
    for (String s :
        $(By.xpath("//div[@class='k-grid-content']/table[@role='grid']"))
            .shouldBe(Condition.visible)
            .$$(By.xpath("//tr/td[4]"))
            .texts()) {

      orders.add(new TransportOrderData.Builder().vin(s).build());
    }
    return orders;
  }

  @Step
  public List<TransportOrderData> getOrdersDataFromGrid() {

    List<TransportOrderData> transportOrderDataList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@class='k-grid-content']/table[@role='grid']/tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));
    int row_count = rows.size();

    for (int row = 0; row < row_count; row++) {
      List<SelenideElement> columns_row = rows.get(row).$$(By.xpath("td"));

      String number = columns_row.get(1).scrollTo().text();
      String vin = columns_row.get(3).scrollTo().text();
      String currentWarehouse = columns_row.get(8).scrollTo().text();
      String consignor = columns_row.get(9).scrollTo().text();
      String status = columns_row.get(18).scrollTo().text();

      transportOrderDataList.add(
          new TransportOrderData.Builder()
              .number(number)
              .vin(vin)
              .currentWarehouse(currentWarehouse)
              .consignor(consignor)
              .status(status)
              .build());
    }
    return transportOrderDataList;
  }

  @Step
  public List<String> getOrdersStatusFromGrid() {
    return $$(By.xpath("//div[@class='k-grid-content']/table[@role='grid']//tr/td[19]")).texts();
  }
}
