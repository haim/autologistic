package com.estafeta.test.ui.stepdefinitions.transports;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.data.transports.transports.TransportData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class TransportsPageSteps {
  static TransportCreatePageSteps transportCreatePageSteps = new TransportCreatePageSteps();

  @Step
  public static TransportsPageSteps createTransport(TransportData transportData) {
    open();
    initCreateNewTransport();
    transportCreatePageSteps.fillForms(transportData);
    return page(TransportsPageSteps.class);
  }

  @Step
  public static TransportsPageSteps open() {
    Selenide.open("/Transports/List");
    return page(TransportsPageSteps.class);
  }

  @Step
  public static TransportCreatePageSteps initCreateNewTransport() {
    $(By.id("createButton")).click();
    return page(TransportCreatePageSteps.class);
  }

  @Step
  public TransportsPageSteps searchTransport(String transportData) {
    $(By.id("searchText")).setValue(transportData);
    $x("//i[@class='icon-search']").click();
    $(By.id("loadingOverlay")).waitUntil(Condition.disappear, 20000);
    return page(TransportsPageSteps.class);
  }

  @Step
  public List<TransportData> getTransportsDataFromGrid() {

    List<TransportData> transportDataList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@class='k-grid-content']/table[@role='grid']/tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));

    for (int row = 0; row < rows.size(); row++) {
      String board = rows.get(row).$$(By.xpath("td")).get(0).text();
      String truck = rows.get(row).$$(By.xpath("td")).get(1).text();
      String trailer = rows.get(row).$$(By.xpath("td")).get(2).text();
      String firstDriver = rows.get(row).$$(By.xpath("td")).get(3).text();
      String owner = rows.get(row).$$(By.xpath("td")).get(7).text();

      transportDataList.add(
          new TransportData.Builder()
              .board(board)
              .transport(truck)
              .transportPart(trailer)
              .firstDriver(firstDriver)
              .owner(owner)
              //              .creationDate(creationDate)
              .build());
    }
    return transportDataList;
  }
}
