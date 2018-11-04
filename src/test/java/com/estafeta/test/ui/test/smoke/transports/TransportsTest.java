package com.estafeta.test.ui.test.smoke.transports;

import com.estafeta.test.ui.data.admin.transportItems.TransportItemsData;
import com.estafeta.test.ui.data.transports.transports.TransportData;
import com.estafeta.test.ui.data.users.UserData;
import com.estafeta.test.ui.data.vehicles.VehicleData;
import com.estafeta.test.ui.helper.BaseTest;
import com.estafeta.test.ui.helper.DateAndTime;
import com.estafeta.test.ui.stepdefinitions.logistics.LogisticTaskProfilePageSteps;
import com.estafeta.test.ui.stepdefinitions.logistics.lots.LotProfilePageStep;
import com.estafeta.test.ui.stepdefinitions.logistics.lots.LotsPageSteps;
import com.estafeta.test.ui.stepdefinitions.transports.TransportCreatePageSteps;
import com.estafeta.test.ui.stepdefinitions.transports.TransportsPageSteps;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.estafeta.test.ui.stepdefinitions.LoginPageSteps.loginAs;
import static com.estafeta.test.ui.stepdefinitions.admin.transportItems.TransportItemsPageSteps.createTransportItem;
import static com.estafeta.test.ui.stepdefinitions.admin.users.UsersPageSteps.createUser;
import static org.assertj.core.api.Assertions.assertThat;

public class TransportsTest extends BaseTest {

  @Test(
      invocationCount = 20,
      dataProvider = "transport_test",
      dataProviderClass = TransportsDataProvider.class)
  public void transport_test(
      UserData admin,
      UserData driver,
      TransportItemsData trailer,
      TransportItemsData car,
      TransportData newTransport,
      VehicleData vehicleListData) throws InterruptedException {
    loginAs(admin,true);
    createUser(driver);
    createTransportItem(car);
    createTransportItem(trailer);
    //    createTransport(newTransport);

    TransportsPageSteps transportsPageSteps = new TransportsPageSteps();
    TransportCreatePageSteps transportCreatePageSteps = new TransportCreatePageSteps();

    transportsPageSteps.open().initCreateNewTransport();
    transportCreatePageSteps.fillForms(newTransport);

    transportsPageSteps.searchTransport(newTransport.getTransport());
    assertThat(transportsPageSteps.getTransportsDataFromGrid().get(0).getTransport())
        .isEqualToIgnoringCase(newTransport.getTransport());
  }

  @Test
  //  @LoginRequired
  public void searchOrder() {
    String photo =
        "/home/dnikiforov/Dropbox/code/avtologistika/estafeta/autologistic/estafeta/src/test/resources/photo.png";

    loginAs("exkqiQLdVzumT", "AVxmHgKNqY");
    LotsPageSteps lotsPageSteps = new LotsPageSteps();

    lotsPageSteps.open().findLotBySearchAndOpen("DU-90747").openTasksTab();

    LotProfilePageStep lotProfilePageStep = new LotProfilePageStep();

    lotProfilePageStep.openLogisticTask("Loading", "Avtologistika - Romania");
    switchTo().window(1);

    LogisticTaskProfilePageSteps logisticTaskProfilePageSteps = new LogisticTaskProfilePageSteps();
    logisticTaskProfilePageSteps.performAllSurveyTasksOfLoadingOrdersToComplete(
        logisticTaskProfilePageSteps.getLinkOfAllSurveyTasks(),
        photo,
        DateAndTime.getDatePlusDays(0),
        DateAndTime.getTime(0),
        "3");
  }
}
