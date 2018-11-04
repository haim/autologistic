package com.estafeta.test.ui;

import com.codeborne.selenide.Condition;
import com.estafeta.test.ui.data.admin.transportItems.TransportItemsData;
import com.estafeta.test.ui.data.lots.LotData;
import com.estafeta.test.ui.data.transports.transports.TransportData;
import com.estafeta.test.ui.data.users.UserData;
import com.estafeta.test.ui.data.vehicles.VehicleData;
import com.estafeta.test.ui.helper.BaseTest;
import com.estafeta.test.ui.helper.DateAndTime;
import com.estafeta.test.ui.helper.TestData;
import com.estafeta.test.ui.stepdefinitions.logistics.lots.LotsPageSteps;
import com.estafeta.test.ui.stepdefinitions.logistics.lotting.blocks.LotEditViewSteps;
import com.estafeta.test.ui.test.smoke.lots.LotsDataProvider2;
import one.util.streamex.StreamEx;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.estafeta.test.ui.stepdefinitions.LoginPageSteps.loginAs;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class QuickTest extends BaseTest {

  private static int count_words(String str) {
    int count = 0;
    if (!(" ".equals(str.substring(0, 1))) || !(" ".equals(str.substring(str.length() - 1)))) {
      for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == ' ') {
          count++;
        }
      }
      count = count + 1;
    }
    return count; // returns 0 if string starts or ends with space " ".
  }

  @Test // StreamEx
  public void lotsForInportsWithVehicels() throws IOException {
    TestData testData = new TestData();
    List<String> generatedLotsNumber = testData.generateLotsNumber(3);
    List<String> generatedVinsNumber = testData.generateVinsNumber(6);

    int amountVinsForEachLot = generatedVinsNumber.size() / generatedLotsNumber.size();
    System.out.println("vins for each lots: " + amountVinsForEachLot);

    List<VehicleData> vehicleListData = new ArrayList<>();

    for (int i = 0; i < generatedVinsNumber.size(); i++) {
      vehicleListData.add(new VehicleData.Builder().vin(generatedVinsNumber.get(i)).build());
    }
    System.out.println("Main cars list: " + vehicleListData.size());

    List<List<VehicleData>> listOfVehList =
        StreamEx.ofSubLists(vehicleListData, amountVinsForEachLot).toList();

    List<LotData> lotDataList = new ArrayList<>();
    for (int i = 0; i < generatedLotsNumber.size(); i++) {
      lotDataList.add(
          new LotData.Builder()
              .lotNumber(generatedLotsNumber.get(i))
              .vehicleData(listOfVehList.get(i))
              .build());
    }

    lotDataList.forEach(item -> System.out.println(item.getVehicleData().size()));

    System.out.println(
        vehicleListData.stream().map(VehicleData::getVin).collect(Collectors.toList()).size());
  }

  @Test
  public void tableTest() throws IOException {

    TestData testData = new TestData();
    List<String> generatedLotsNumber = testData.generateLotsNumber(3);
    List<String> generatedVinsNumber = testData.generateVinsNumber(6);

    int amountVinsForEachLot = generatedVinsNumber.size() / generatedLotsNumber.size();
    System.out.println("vins for each lots: " + amountVinsForEachLot);

    List<LotData> lotData = new ArrayList<>();
    List<VehicleData> vehicleData1 = new ArrayList<>();

    int rw = 0;
    for (int i = 0; i < generatedLotsNumber.size(); i++) {
      for (int ii = 0; ii <= generatedVinsNumber.size(); ii = ii - amountVinsForEachLot) {
        vehicleData1.add(new VehicleData.Builder().vin(generatedVinsNumber.get(ii + rw)).build());
        if (vehicleData1.size() == amountVinsForEachLot) break;
      }
      rw = rw + amountVinsForEachLot;
      lotData.add(
          new LotData.Builder()
              .lotNumber(generatedLotsNumber.get(i))
              .vehicleData(vehicleData1)
              .build());
      System.out.println("Lot number: " + lotData.get(i).getLotNumber() + " added success");
    }
  }

  @Test // java.lang.UnsupportedOperationException
  public void test() throws IOException {
    TestData testData = new TestData();
    List<String> generatedLotsNumber = testData.generateLotsNumber(3);
    List<String> generatedVinsNumber = testData.generateVinsNumber(6);

    int amountVinsForEachLot = generatedVinsNumber.size() / generatedLotsNumber.size();
    System.out.println("vins for each lots: " + amountVinsForEachLot);

    List<VehicleData> vehicleListData = new ArrayList<>();

    for (int i = 0; i < generatedVinsNumber.size(); i++) {
      vehicleListData.add(new VehicleData.Builder().vin(generatedVinsNumber.get(i)).build());
    }
    System.out.println("Main cars list: " + vehicleListData.size());

    List<List<VehicleData>> vList = new ArrayList<>();
    int counter = 0;
    for (VehicleData x : vehicleListData) {
      if (counter == 0) {
        vList.add(Collections.singletonList(x));
      } else {
        vList.get(vList.size() - 1).add(x);
      }
      counter += 1;
      counter %= amountVinsForEachLot;
    }

    System.out.println("List of List Cars: " + vList.stream().collect(Collectors.toList()).size());
  }

  @Test
  public void tttest() throws IOException {
    TestData testData = new TestData();
    List<String> generatedLotsNumber = testData.generateLotsNumber(3);
    List<String> generatedVinsNumber = testData.generateVinsNumber(6);

    int amountVinsForEachLot = generatedVinsNumber.size() / generatedLotsNumber.size();
    System.out.println("vins for each lots: " + amountVinsForEachLot);

    List<VehicleData> vehicleListData = new ArrayList<>();

    for (int i = 0; i < generatedVinsNumber.size(); i++) {
      vehicleListData.add(new VehicleData.Builder().vin(generatedVinsNumber.get(i)).build());
    }
    System.out.println(vehicleListData.size());

    List<List<VehicleData>> vList = new ArrayList<>();
    boolean first = true;
    for (VehicleData x : vehicleListData) {
      if (first) {
        vList.add(new ArrayList<VehicleData>(Collections.singleton(x)));
      } else {
        vList.get(vList.size() - 1).add(x);
      }
      first = !first;
    }

    for (List<VehicleData> l : vList) {
      System.out.println(l.stream().collect(Collectors.toList()));
    }
  }

  @Test(
      dataProvider = "create_lot_by_excel_renault_cars2",
      dataProviderClass = LotsDataProvider2.class)
  public void create_lot_by_excel_renault_cars2(
      LotData renaultDataForImportLotsForm,
      LotData lotDataForApprove,
      List<LotData> lotDataList,
      List<String> generatedLotsNumber,
      List<String> generatedVinsNumber,
      List<VehicleData> vehicleListData,
      UserData admin,
      UserData driver,
      TransportItemsData trailer,
      TransportItemsData car,
      TransportData newTransport) throws InterruptedException {
    /** login as admin - method static */
    loginAs(admin,true);

    LotsPageSteps lotsPageSteps = new LotsPageSteps();
    lotsPageSteps.open().findLotBySearchAndOpen("DU-47725").initEditMode();
    String company = "Terminal Avtologistika";
    String type = "Unloading";

    //
    // System.out.println($$(By.xpath("//div[@id='lotEditView']//ul[@class='ui-sortable']/li")).size());
    //    $(By.partialLinkText("Terminal Avtologistika"));

    //    $(By.xpath("//li[@class='unloading-company-row']"))
    //        .shouldHave(Condition.attribute("data-company-name", company))
    //
    // .$(By.xpath("//input[@data-role='datepicker']")).setValue(DateAndTime.getDatePlusDays(2)).pressTab();
    //
    //    $(By.xpath("//li[@class='unloading-company-row']"))
    //        .shouldHave(Condition.attribute("data-company-name", company))
    //        .$(By.xpath("//input[@name='UnloadingTime']"))
    //        .setValue(DateAndTime.getDatePlusDays(2));
    //
    //    $(By.xpath("//input[@id='actionId-3']")).click();

    $(By.xpath(
            "//li[@data-company-name='"
                + company
                + "']//label[text()='"
                + type
                + "']/../..//input[@data-role='datepicker']"))
        .setValue(DateAndTime.getDatePlusDays(1));
    $(By.xpath(
            "//li[@data-company-name='"
                + company
                + "']//label[text()='"
                + type
                + "']/../..//input[@class='hasTimeEntry']"))
        .setValue("03:00");
    System.out.println("asd");
  }

  private LotEditViewSteps setOperationDate(String companyName, String operationType, String date) {
    $(By.xpath("//ul[@class='ui-sortable']"))
        .$$(By.xpath("/li"))
        .findBy(Condition.attribute("data-company-name", companyName))
        .$(By.xpath("//div[2]/label"))
        .shouldHave(Condition.text(operationType))
        .$(By.xpath("/..//input[@class='k-input']"))
        .setValue(date)
        .pressTab();
    return page(LotEditViewSteps.class);
  }

  private LotEditViewSteps setOperationTime(String companyName, String operationType, String time) {
    $(By.xpath("//ul[@id='sortable']/li"))
        .shouldHave(Condition.attribute("data-company-name", companyName))
        .$(By.xpath("//div[2]"))
        .shouldHave(Condition.text(operationType))
        .$(By.xpath("/..//input[@class='hasTimeEntry']"))
        .setValue(time)
        .pressTab();
    return page(LotEditViewSteps.class);
  }
}
