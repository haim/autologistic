package com.estafeta.test.ui.test.smoke.admin.transportitems;

import com.estafeta.test.ui.data.admin.transportItems.TransportItemsData;
import com.estafeta.test.ui.helper.BaseTest;
import com.estafeta.test.ui.preconditions.LoginRequired;
import com.estafeta.test.ui.stepdefinitions.admin.transportItems.TransportItemsCreatePageSteps;
import com.estafeta.test.ui.stepdefinitions.admin.transportItems.TransportItemsPageSteps;
import org.testng.annotations.Test;

public class TransportItemsTest extends BaseTest {

  @Test(
      dataProvider = "create_new_transport_item_by_type_car_transporter",
      dataProviderClass = TransportItemsProvider.class)
  @LoginRequired
  public void create_new_transport_item_by_type_car_transporter(TransportItemsData car) {

    TransportItemsPageSteps transportItemsPageSteps = new TransportItemsPageSteps();
    TransportItemsCreatePageSteps transportItemsCreatePageSteps =
        new TransportItemsCreatePageSteps();

    transportItemsPageSteps.open().initCreateNewTransportItem();
    transportItemsCreatePageSteps.fillForms(car).confirmNewTransportItemPopUp(true);
  }

  @Test(
      dataProvider = "create_new_transport_item_by_type_trailer",
      dataProviderClass = TransportItemsProvider.class)
  public void create_new_transport_item_by_type_trailer(TransportItemsData trailer) {
    TransportItemsPageSteps transportItemsPageSteps = new TransportItemsPageSteps();
    TransportItemsCreatePageSteps transportItemsCreatePageSteps =
        new TransportItemsCreatePageSteps();

    transportItemsPageSteps.open().initCreateNewTransportItem();
    transportItemsCreatePageSteps.fillForms(trailer).confirmNewTransportItemPopUp(true);
  }
}
