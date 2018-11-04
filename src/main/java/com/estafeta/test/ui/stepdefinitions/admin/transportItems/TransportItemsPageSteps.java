package com.estafeta.test.ui.stepdefinitions.admin.transportItems;

import com.codeborne.selenide.Selenide;
import com.estafeta.test.ui.data.admin.transportItems.TransportItemsData;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class TransportItemsPageSteps {
  static TransportItemsCreatePageSteps transportItemsCreatePageSteps =
      new TransportItemsCreatePageSteps();

  @Step
  public static TransportItemsPageSteps createTransportItem(TransportItemsData transportItemsData) {
    open();
    initCreateNewTransportItem();
    transportItemsCreatePageSteps.fillForms(transportItemsData);
    transportItemsCreatePageSteps.confirmNewTransportItemPopUp(
        true); // TODO need remove , as for new  TransportItem should not display confirmation
    return page(TransportItemsPageSteps.class);
  }

  @Step
  public static TransportItemsPageSteps open() {
    Selenide.open("/Admin/TransportItems/List");
    return page(TransportItemsPageSteps.class);
  }

  @Step
  public static TransportItemsCreatePageSteps initCreateNewTransportItem() {
    $x("//a[@href='/Admin/TransportItems/Create']").click();
    return page(TransportItemsCreatePageSteps.class);
  }
}
