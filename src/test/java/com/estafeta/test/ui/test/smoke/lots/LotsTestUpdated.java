package com.estafeta.test.ui.test.smoke.lots;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.data.admin.transportItems.TransportItemsData;
import com.estafeta.test.ui.data.lots.LotData;
import com.estafeta.test.ui.data.survey.SurveyTaskData;
import com.estafeta.test.ui.data.transportOrder.TransportOrderData;
import com.estafeta.test.ui.data.transports.transports.TransportData;
import com.estafeta.test.ui.data.users.UserData;
import com.estafeta.test.ui.data.vehicles.VehicleData;
import com.estafeta.test.ui.data.warehouse.WarehouseData;
import com.estafeta.test.ui.helper.BaseTest;
import com.estafeta.test.ui.helper.DateAndTime;
import com.estafeta.test.ui.stepdefinitions.Warehouse.WarehousePageSteps;
import com.estafeta.test.ui.stepdefinitions.logistics.LogisticTaskProfilePageSteps;
import com.estafeta.test.ui.stepdefinitions.logistics.lots.LotProfilePageStep;
import com.estafeta.test.ui.stepdefinitions.logistics.lots.LotsPageSteps;
import com.estafeta.test.ui.stepdefinitions.logistics.lotting.LottingSteps;
import com.estafeta.test.ui.stepdefinitions.logistics.transportOrders.TransportOrdersSteps;
import com.estafeta.test.ui.stepdefinitions.survey.SurveyTasksPageSteps;
import com.estafeta.test.ui.stepdefinitions.survey.SurveyTasksProfileEditPageSteps;
import com.estafeta.test.ui.stepdefinitions.transports.TransportsPageSteps;
import com.estafeta.test.ui.stepdefinitions.vehicles.VehiclesGeneralSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static com.estafeta.test.ui.stepdefinitions.LoginPageSteps.logOut;
import static com.estafeta.test.ui.stepdefinitions.LoginPageSteps.loginAs;
import static com.estafeta.test.ui.stepdefinitions.admin.transportItems.TransportItemsPageSteps.createTransportItem;
import static com.estafeta.test.ui.stepdefinitions.admin.users.UsersPageSteps.createUser;
import static com.estafeta.test.ui.stepdefinitions.transports.TransportsPageSteps.createTransport;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LotsTestUpdated extends BaseTest {

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
      UserData logisticsOperator,
      UserData warehouseSurveyorRomania,
      UserData warehouseSurveyorTerminal,
      TransportItemsData trailer,
      TransportItemsData car,
      TransportData newTransport)
      throws InterruptedException {
    String vehicl =
        "src/test/resources/RenaultVehicles.xlsx";
    String photo =
        "src/test/resources/photo.png";

    /** login as admin - method static */
    loginAs(admin, true);
    System.out.println("admin: " + admin.getLogin());
    /** create new driver - method static */
    createUser(driver);
    /** create new logistics operator - method static */
    createUser(logisticsOperator);

    System.out.println("driver: " + driver.getLogin());
    /** create new driver - method static */
    createUser(warehouseSurveyorRomania);
    System.out.println(
        "warehouseSurveyorRomani: "
            + warehouseSurveyorRomania.getLogin()
            + " pass: "
            + warehouseSurveyorRomania.getPassword());
    createUser(warehouseSurveyorTerminal);
    System.out.println(
        "warehouseSurveyorTerminal: "
            + warehouseSurveyorTerminal.getLogin()
            + " pass: "
            + warehouseSurveyorTerminal.getPassword());
    /** create new car - method static */
    createTransportItem(car);
    System.out.println("car: " + car.getNumber());
    /** create new trailer - method static */
    createTransportItem(trailer);
    System.out.println("trailer: " + trailer.getNumber());
    /** create new transport for lotting - method static */
    createTransport(newTransport);
    new TransportsPageSteps().searchTransport(newTransport.getTransport());
    Assertions.assertThat(
            new TransportsPageSteps().getTransportsDataFromGrid().get(0).getTransport())
        .isEqualToIgnoringCase(newTransport.getTransport());
    System.out.println("newTransport: " + newTransport.getTransport());

    /**
     * 1) Импорт новых автомобилей в систему (excel-файл 1). Пункт Автомобили -> Автомобили ->
     * кнопка Импорт.
     */
    VehiclesGeneralSteps vehiclesGeneralSteps = new VehiclesGeneralSteps();
    vehiclesGeneralSteps.open().importVehicles(vehicl);

    /**
     * проверка, что все автомобили появились в системе (Автомобили -> Автомобили -> поиск по VIN-у)
     */
    vehiclesGeneralSteps.searchInGrid(
        vehicleListData.stream().map(VehicleData::getVin).collect(Collectors.toList()));

    //    TODO - add assert by each vins - сейчас проблемa не всегда корректно формируеться
    assertThat(vehiclesGeneralSteps.getVehicleDataFromGrid().size())
        .isEqualTo(vehicleListData.size());

    /**
     * код модели автомобиля в системе(в гриде, на форме - тут на обсуждение) совпадает с кодом
     * модели в файле
     */

    // TODO  - add assert by code model.

    /** 2) Импорт файла с лотами */
    logOut();
    loginAs(logisticsOperator, false);

    LottingSteps lottingSteps = new LottingSteps();
    lottingSteps.open().createLotByImport(renaultDataForImportLotsForm);

    LotsPageSteps lotsPageSteps = new LotsPageSteps();
    lotsPageSteps
        .open()
        .searchInGrid(
            vehicleListData.stream().map(VehicleData::getVin).collect(Collectors.toList()));

    List<LotData> lotsFromAvtologistikaRomaniaToTerminalAvtologistika =
        lotsPageSteps.getLotsDataFromGrid();

    /**
     * проверка, что создались лоты по машинам (Логистика -> Лоты -> поиск по VIN-у) - статус “Выбор
     * автовоза”
     */
    assertThat(
            lotsFromAvtologistikaRomaniaToTerminalAvtologistika.stream().map(p -> p.getLotNumber()))
        .isEqualTo(lotDataList.stream().map(LotData::getLotNumber).collect(Collectors.toList()));

    /** - проверка, что лоты в статусе “Выбор автовоза” */
    assertThat(lotsFromAvtologistikaRomaniaToTerminalAvtologistika.stream().map(p -> p.getState()))
        .anyMatch("Truck Choosing"::equalsIgnoreCase);

    TransportOrdersSteps transportOrdersSteps = new TransportOrdersSteps();
    transportOrdersSteps
        .open()
        .searchTransportOrdersByVins(
            vehicleListData.stream().map(VehicleData::getVin).collect(Collectors.toList()));

    // TODO remove
    List<TransportOrderData> TransportOrdersFromAvtologistikaRomaniaToTerminalAvtologistika =
        transportOrdersSteps.getOrdersDataFromGrid();

    /**
     * - проверка, что создались заявки на перевозку по машинам (Логистика -> Заявки на перевозку ->
     * поиск по VIN-у)
     */
    assertThat(
            transportOrdersSteps
                .getOrdersDataFromGrid()
                .stream()
                .map(TransportOrderData::getVin)
                .collect(Collectors.toList())
                .toString())
        .isEqualToIgnoringWhitespace(
            vehicleListData
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList())
                .toString());

    /**
     * проверка статуса заявки на перевозку машинам (Логистика -> Заявки на перевозку -> поиск по
     * VIN-у) - статус “Заявленная”
     */
    assertThat(
            transportOrdersSteps
                .getOrdersDataFromGrid()
                .stream()
                .map(TransportOrderData::getStatus)
                .collect(Collectors.toList()))
        .anyMatch("Declared"::equalsIgnoreCase);

    /** //2.1 Утверждение лота. Логистика -> Лотирование -> Выбрать лот. */
    /**
     * Working only for one first lot from imported file. TODO - create approve for all imported
     * lots - need describe logics ^_^
     */
    LotProfilePageStep lotProfilePageStep =
        lotsPageSteps
            .open()
            .findLotBySearchAndOpen(lotDataList.get(0).getLotNumber())
            .initEditMode()
            .fillTruckType(lotDataForApprove.getTruckType())
            .fillTruck(lotDataForApprove.getTruck())
            .fillLoadingDate(
                lotDataForApprove.getLoadingDateDate(), lotDataForApprove.getLoadingDateTime())
            .selectWarrentyType(lotDataForApprove.getWarrantyType())
            .addBorderCrossing(
                lotDataForApprove.getBorderCrossingData().get(0).getName(),
                lotDataForApprove.getBorderCrossingData().get(0).getDate(),
                lotDataForApprove.getBorderCrossingData().get(0).getCrossingTime())
            .updateExistUnloadingOperations2(
                lotDataForApprove.getTo(),
                "Unloading",
                lotDataForApprove.getToDate(),
                lotDataForApprove.getToTime())
            .approveRoute();

    /** проверка. что создались логистические задачи по лоту */
    // TODO - create logistics tasks method.
    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();

    assertThat(lotProfilePageStep.getLogisticTasks()).isEqualTo(9);

    /** // Выполнить проверку, что лот в статусе Ожидание погрузки. */
    lotsPageSteps
        .open()
        .searchInGrid(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(lotsPageSteps.getLotsDataFromGrid().stream().map(LotData::getState))
        .anyMatch("Loading Awaiting"::equalsIgnoreCase);

    /** 3. Выполнение логистических задач лота Завод Avtologistika Romania - Терминал: */
    logOut();
    loginAs(admin, true);
    /** 3.1 Выполнение прибытия на погрузку Завод. */
    LogisticTaskProfilePageSteps logisticTaskProfilePageSteps = new LogisticTaskProfilePageSteps();

    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();

    lotProfilePageStep.openLogisticTask("Arrival", renaultDataForImportLotsForm.getFrom());

    switchTo().window(1);

    logisticTaskProfilePageSteps
        .fillDateAndTime(DateAndTime.getDatePlusDays(0), DateAndTime.getTime(2))
        .save();

    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    lotProfilePageStep.openTasksTab();

    /** проверка статуса логисической задачи Arrival = Completed */
    assertThat(
            lotProfilePageStep.getLogisticTaskStatus(
                "Arrival", renaultDataForImportLotsForm.getFrom()))
        .isEqualTo("Completed");

    /**
     * // проверка статуса лота (Логистика -> Лоты -> поиск по VIN-у или по номеру лота) статус
     * “Ожидание погрузки”
     */
    lotsPageSteps.open().findLotBySearch(lotDataList.get(0).getLotNumber());
    assertThat(lotsPageSteps.getLotsDataFromGrid().get(0).getState()).isEqualTo("Loading Awaiting");

    /**
     * // - проверка статуса заявки на перевозку(Логистика -> Заявки на перевозку -> поиск по VIN-у)
     * статус "Запланирована"
     */
    transportOrdersSteps
        .open()
        .searchTransportOrdersByVins(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));
    assertThat(
            transportOrdersSteps
                .getOrdersDataFromGrid()
                .stream()
                .map(TransportOrderData::getStatus)
                .collect(Collectors.toList()))
        .anyMatch("Planned"::equalsIgnoreCase); // compair list of string with one value

    /** статуса машины на складе (Склад -> Склад -> поиск по VIN-у и точке) */
    WarehousePageSteps warehousePageSteps = new WarehousePageSteps();
    warehousePageSteps
        .open()
        .searchVehicalsByVin(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(
            warehousePageSteps
                .getWarehouseDataFromGrid()
                .stream()
                .filter(w -> !w.getTransportOrder().isEmpty())
                .map(WarehouseData::getWarehouse)
                .collect(Collectors.toList())
                .toString())
        .contains(renaultDataForImportLotsForm.getFrom());

    /** 3.1.1 Выполнение первой задачи Осмотра перед погрузкой */
    logOut();
    loginAs(warehouseSurveyorRomania, false);

    SurveyTasksPageSteps surveyTasksPageSteps = new SurveyTasksPageSteps();
    surveyTasksPageSteps.open();

   // todo add for each search by vin
    surveyTasksPageSteps.searchSurveyTasksinGrid(
        lotDataList
            .get(0)
            .getVehicleData()
            .stream()
            .map(VehicleData::getVin)
            .collect(Collectors.toList()));

    List<SurveyTaskData> surveyTasksLinks = surveyTasksPageSteps.getLinkOfAllSurveyTasks2();
    surveyTasksPageSteps.performSurveyTasksFromGrid(
            surveyTasksLinks, photo, DateAndTime.getDatePlusDays(0), DateAndTime.getTime(0), "3");


    // login as another
    /** Открыть заявку на погрузку из лота. */
    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();

    lotProfilePageStep.openLogisticTask("Loading", renaultDataForImportLotsForm.getFrom());
    switchTo().window(1);

    List<SelenideElement> linkOfAllSurveyTasksInLoadingOrder =
        logisticTaskProfilePageSteps.getLinkOfAllSurveyTasks();

    logisticTaskProfilePageSteps.performAllSurveyTasksOfLoadingOrdersToComplete(
        linkOfAllSurveyTasksInLoadingOrder,
        photo,
        DateAndTime.getDatePlusDays(0), // work days
        DateAndTime.getTime(0),
        "3");

    /** - Статус задач осмотра поменяется на Completed */
    assertThat(logisticTaskProfilePageSteps.getStatusOfAllServeyTasks())
        .anyMatch("Completed"::equalsIgnoreCase);

    /** - Статус заявки на погрузку поменяется на "в процессе" */
    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();
    assertThat(
            lotProfilePageStep.getLogisticTaskStatus(
                "Loading", renaultDataForImportLotsForm.getFrom()))
        .isEqualTo("In Process");

    /** - Статус лота поменяется на "в процессе" */
    lotsPageSteps.open().findLotBySearch(lotDataList.get(0).getLotNumber());
    assertThat(lotsPageSteps.getLotsDataFromGrid().get(0).getState())
        .isEqualTo("Loading in progress");

    /** back to first window */
    switchTo().window(1).close();
    switchTo().window(0);

    /**
     * // 3.2 Выполнение погрузки на Заводе. Статус лота Транспортировка, статус заявки Транзит, //
     * машины нет ни на одном складе
     */
    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();

    lotProfilePageStep.openLogisticTask("Loading", renaultDataForImportLotsForm.getFrom());

    switchTo().window(1);

    logisticTaskProfilePageSteps
        .fillDateAndTime(DateAndTime.getDatePlusDays(0), DateAndTime.getTime(2)) // work days
        .save();
    //    if ($(By.xpath("//div[@id='isActualDateLessThanCurrentDateValidationMessage']/span"))
    //        .isDisplayed()) {
    //      $(By.xpath("//div[@id='isActualDateLessThanCurrentDateValidationMessage']/span"))
    //          .shouldHave(Condition.text("The TTN date must be equal to the current date"));
    //      logisticTaskProfilePageSteps
    //          .fillDateAndTime(DateAndTime.getDatePlusDays(0), DateAndTime.getTime(2))
    //          .save(); // work days
    //    }

    switchTo().window(1).close();
    switchTo().window(0);

    refresh();

    lotProfilePageStep.openTasksTab();

    /** проверка статуса логисической задачи Loading = Completed */
    assertThat(
            lotProfilePageStep.getLogisticTaskStatus(
                "Loading", renaultDataForImportLotsForm.getFrom()))
        .isEqualTo("Completed");

    /**
     * // проверка статуса лота (Логистика -> Лоты -> поиск по VIN-у или по номеру лота) статус
     * “Transportation"
     */
    lotsPageSteps.open().findLotBySearch(lotDataList.get(0).getLotNumber());
    assertThat(lotsPageSteps.getLotsDataFromGrid().get(0).getState()).isEqualTo("Transportation");

    /**
     * // - проверка статуса заявки на перевозку(Логистика -> Заявки на перевозку -> поиск по VIN-у)
     * статус "Transit" Skip by = User: warehouseSurveyor - doesn't have permission to check
     * transport order page!!!!!
     */
    //    transportOrdersSteps
    //        .open()
    //        .searchTransportOrdersByVins(
    //            lotDataList
    //                .get(0)
    //                .getVehicleData()
    //                .stream()
    //                .map(VehicleData::getVin)
    //                .collect(Collectors.toList()));
    //    assertThat(
    //            transportOrdersSteps
    //                .getOrdersDataFromGrid()
    //                .stream()
    //                .map(TransportOrderData::getStatus)
    //                .collect(Collectors.toList()))
    //        .anyMatch("Transit"::equalsIgnoreCase); // compair list of string with one value

    /**
     * // - статуса машины на складе (Склад -> Склад -> поиск по VIN-у и точке) - empty Skip by =
     * User: warehouseSurveyor - doesn't have permission to check warehouse page !!!!!
     */
    //    warehousePageSteps
    //        .open()
    //        .searchVehicalsByVin(
    //            lotDataList
    //                .get(0)
    //                .getVehicleData()
    //                .stream()
    //                .map(VehicleData::getVin)
    //                .collect(Collectors.toList()));
    //
    //    assertThat(
    //            warehousePageSteps
    //                .getWarehouseDataFromGrid()
    //                .stream()
    //                .filter(w -> !w.getTransportOrder().isEmpty())
    //                .map(WarehouseData::getWarehouse)
    //                .collect(Collectors.toList())
    //                .toString())
    //        .doesNotContain(renaultDataForImportLotsForm.getFrom());

    /**
     * // 3.3 Выполнение убытия с Завода. Статус лота Транспортировка, статус заявки Транзит, машины
     * // нет ни на одном складе
     */
    logOut();
    loginAs(admin, true);

    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();
    lotProfilePageStep.openLogisticTask("Departure", renaultDataForImportLotsForm.getFrom());

    switchTo().window(1);

    logisticTaskProfilePageSteps
        .fillDateAndTime(DateAndTime.getDatePlusDays(0), DateAndTime.getTime(2))
        .save();
    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    lotProfilePageStep.openTasksTab();

    /** проверка статуса логисической задачи Loading = Completed */
    assertThat(
            lotProfilePageStep.getLogisticTaskStatus(
                "Departure", renaultDataForImportLotsForm.getFrom()))
        .isEqualTo("Completed");

    lotsPageSteps.open();

    /** // - проверка статуса лота - статус “Транспортировка” */
    lotsPageSteps.open().findLotBySearch(lotDataList.get(0).getLotNumber());
    assertThat(lotsPageSteps.getLotsDataFromGrid().get(0).getState()).isEqualTo("Transportation");

    /** // - проверка статуса заявки на перевозку статус “Транзит” */
    transportOrdersSteps
        .open()
        .searchTransportOrdersByVins(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(
            transportOrdersSteps
                .getOrdersDataFromGrid()
                .stream()
                .map(TransportOrderData::getStatus)
                .collect(Collectors.toList()))
        .anyMatch("Transit"::equalsIgnoreCase); // compair list of string with one value

    /** // - статуса машины на складе (Склад -> Склад -> поиск по VIN-у и точке) */
    warehousePageSteps
        .open()
        .searchVehicalsByVin(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(
            warehousePageSteps
                .getWarehouseDataFromGrid()
                .stream()
                .filter(w -> !w.getTransportOrder().isEmpty())
                .findFirst())
        .isEmpty();

    /**
     * // 3.4 Выполнение прибытия на погран переход. Статус лота Транспортировка, статус заявки //
     * // * Транзит, машины нет ни на одном складе - нет такой логистической задачи //
     */
    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();
    lotProfilePageStep.openLogisticTask(
        "Arrival", lotDataForApprove.getBorderCrossingData().get(0).getName());

    switchTo().window(1);

    logisticTaskProfilePageSteps
        .fillDateAndTime(DateAndTime.getDatePlusDays(0), DateAndTime.getTime(2))
        .save();
    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    lotProfilePageStep.openTasksTab();

    /** проверка статуса логисической задачи Arrival = Completed */
    assertThat(
            lotProfilePageStep.getLogisticTaskStatus(
                "Arrival", lotDataForApprove.getBorderCrossingData().get(0).getName()))
        .isEqualTo("Completed");

    lotsPageSteps.open();

    /** // - проверка статуса лота - статус “Транспортировка” */
    lotsPageSteps.open().findLotBySearch(lotDataList.get(0).getLotNumber());
    assertThat(lotsPageSteps.getLotsDataFromGrid().get(0).getState()).isEqualTo("Transportation");

    /** // - проверка статуса заявки на перевозку статус “Транзит” */
    transportOrdersSteps
        .open()
        .searchTransportOrdersByVins(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));
    assertThat(
            transportOrdersSteps
                .getOrdersDataFromGrid()
                .stream()
                .map(TransportOrderData::getStatus)
                .collect(Collectors.toList()))
        .anyMatch("Transit"::equalsIgnoreCase); // compair list of string with one value

    /** // - статуса машины на складе (Склад -> Склад -> поиск по VIN-у и точке) */
    warehousePageSteps
        .open()
        .searchVehicalsByVin(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(
            warehousePageSteps
                .getWarehouseDataFromGrid()
                .stream()
                .filter(w -> !w.getTransportOrder().isEmpty())
                .findFirst())
        .isEmpty();

    /**
     * // 3.5 Выполнение задачи погран перехода. Статус лота Транспортировка, статус заявки Транзит,
     * // машины нет ни на одном складе
     */
    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();
    lotProfilePageStep.openLogisticTask(
        "Border Crossing", lotDataForApprove.getBorderCrossingData().get(0).getName());

    switchTo().window(1);

    logisticTaskProfilePageSteps
        .fillDateAndTime(DateAndTime.getDatePlusDays(0), DateAndTime.getTime(2))
        //        .openDocumentsTab()
        //        .addDocument("ТТN/CMR", vehicl)
        //        .addDocument("CMR", vehicl)
        .save();

    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    lotProfilePageStep.openTasksTab();

    /** проверка статуса логисической задачи Border Crossing = Completed */
    assertThat(
            lotProfilePageStep.getLogisticTaskStatus(
                "Border Crossing", lotDataForApprove.getBorderCrossingData().get(0).getName()))
        .isEqualTo("Completed");

    /**
     * // 3.6 Выполнение убытия с погран перехода. Статус лота Транспортировка, статус заявки
     * Транзит, // машины нет ни на одном складе - этот шаг не создался
     */
    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();
    lotProfilePageStep.openLogisticTask(
        "Departure", lotDataForApprove.getBorderCrossingData().get(0).getName());

    switchTo().window(1);

    logisticTaskProfilePageSteps
        .fillDateAndTime(DateAndTime.getDatePlusDays(0), DateAndTime.getTime(2))
        .save();
    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    lotProfilePageStep.openTasksTab();

    /** проверка статуса логисической задачи Departure = Completed */
    assertThat(
            lotProfilePageStep.getLogisticTaskStatus(
                "Departure", lotDataForApprove.getBorderCrossingData().get(0).getName()))
        .isEqualTo("Completed");

    lotsPageSteps.open();

    /** // - проверка статуса лота - статус “Транспортировка” */
    lotsPageSteps.open().findLotBySearch(lotDataList.get(0).getLotNumber());
    assertThat(lotsPageSteps.getLotsDataFromGrid().get(0).getState()).isEqualTo("Transportation");

    /** // - проверка статуса заявки на перевозку статус “Транзит” */
    transportOrdersSteps
        .open()
        .searchTransportOrdersByVins(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(
            transportOrdersSteps
                .getOrdersDataFromGrid()
                .stream()
                .map(TransportOrderData::getStatus)
                .collect(Collectors.toList()))
        .anyMatch("Transit"::equalsIgnoreCase); // compair list of string with one value

    /** // - статуса машины на складе (Склад -> Склад -> поиск по VIN-у и точке) */
    warehousePageSteps
        .open()
        .searchVehicalsByVin(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(
            warehousePageSteps
                .getWarehouseDataFromGrid()
                .stream()
                .filter(w -> !w.getTransportOrder().isEmpty())
                .findFirst())
        .isEmpty();

    /**
     * // 3.7 Выполнение прибытия на разгрузку на Терминал. Статус лота Транспортировка, статус //
     * заявки Транзит, машины нет ни на одном складе
     */
    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();

    lotProfilePageStep.openLogisticTask("Arrival", renaultDataForImportLotsForm.getTo());

    switchTo().window(1);

    logisticTaskProfilePageSteps
        .fillDateAndTime(DateAndTime.getDatePlusDays(0), DateAndTime.getTime(2))
        .save();

    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    lotProfilePageStep.openTasksTab();

    /** проверка статуса логисической задачи Arrival = Completed */
    assertThat(
            lotProfilePageStep.getLogisticTaskStatus(
                "Arrival", renaultDataForImportLotsForm.getTo()))
        .isEqualTo("Completed");

    /**
     * // проверка статуса лота (Логистика -> Лоты -> поиск по VIN-у или по номеру лота) статус
     * “Transportation”
     */
    lotsPageSteps.open().findLotBySearch(lotDataList.get(0).getLotNumber());
    assertThat(lotsPageSteps.getLotsDataFromGrid().get(0).getState()).isEqualTo("Transportation");

    /**
     * // - проверка статуса заявки на перевозку(Логистика -> Заявки на перевозку -> поиск по VIN-у)
     * статус "Запланирована"
     */
    transportOrdersSteps
        .open()
        .searchTransportOrdersByVins(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));
    assertThat(
            transportOrdersSteps
                .getOrdersDataFromGrid()
                .stream()
                .map(TransportOrderData::getStatus)
                .collect(Collectors.toList()))
        .anyMatch("Transit"::equalsIgnoreCase); // compair list of string with one value

    /** статуса машины на складе (Склад -> Склад -> поиск по VIN-у и точке) */
    warehousePageSteps
        .open()
        .searchVehicalsByVin(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(
            warehousePageSteps
                .getWarehouseDataFromGrid()
                .stream()
                .filter(w -> !w.getTransportOrder().isEmpty())
                .map(WarehouseData::getWarehouse)
                .collect(Collectors.toList())
                .toString())
        .doesNotContain(renaultDataForImportLotsForm.getTo());

    /**
     * 3.8 Выполнение разгрузки на Терминале. В заявку нужно прикрепить документ ТТН/CMR (на форме
     * заявки таба документы, кнопка Добавить). Статус лота Выполнен, статус заявки Доставлена,
     * машина появилась на складе Терминал
     */
    logOut();
    loginAs(warehouseSurveyorTerminal, false);
    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();

    lotProfilePageStep.openLogisticTask("Unloading", renaultDataForImportLotsForm.getTo());

    switchTo().window(1);

    logisticTaskProfilePageSteps
        .fillDateAndTime(DateAndTime.getDatePlusDays(0), DateAndTime.getTime(2))
        .openDocumentsTab()
        .addDocument("ТТN/CMR", vehicl)
        .save();

    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    lotProfilePageStep.openTasksTab();

    /** проверка статуса логисической задачи Arrival = Completed */
    assertThat(
            lotProfilePageStep.getLogisticTaskStatus(
                "Unloading", renaultDataForImportLotsForm.getTo()))
        .isEqualTo("Completed");

    /**
     * // проверка статуса лота (Логистика -> Лоты -> поиск по VIN-у или по номеру лота) статус
     * “Transportation”
     */
    lotsPageSteps.open().findLotBySearch(lotDataList.get(0).getLotNumber());
    assertThat(lotsPageSteps.getLotsDataFromGrid().get(0).getState()).isEqualTo("Transportation");

    /**
     * // - проверка статуса заявки на перевозку(Логистика -> Заявки на перевозку -> поиск по VIN-у)
     * статус "Запланирована"
     */
    transportOrdersSteps
        .open()
        .searchTransportOrdersByVins(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));
    assertThat(
            transportOrdersSteps
                .getOrdersDataFromGrid()
                .stream()
                .map(TransportOrderData::getStatus)
                .collect(Collectors.toList()))
        .anyMatch("Transit"::equalsIgnoreCase); // compair list of string with one value

    /** статуса машины на складе (Склад -> Склад -> поиск по VIN-у и точке) */
    warehousePageSteps
        .open()
        .searchVehicalsByVin(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(
            warehousePageSteps
                .getWarehouseDataFromGrid()
                .stream()
                .filter(w -> !w.getTransportOrder().isEmpty())
                .map(WarehouseData::getWarehouse)
                .collect(Collectors.toList())
                .toString())
        .doesNotContain(renaultDataForImportLotsForm.getTo());

    /**
     * 3.8.1 Также нужно будет выполнить все задачи осмотра по этой заявке: пункт Осмотр -> Задачи
     * осмотра -> поиск по VIN-ам -> выделить все VIN и нажать кнопку Осмотр без замечаний
     */
    //    SurveyTasksPageSteps surveyTasksPageSteps = new SurveyTasksPageSteps();
    surveyTasksPageSteps
        .open()
        .searchSurveyTasksinGrid(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    List<SurveyTaskData> listOfLinksSurveyTasks = surveyTasksPageSteps.getLinkOfAllSurveyTasks2();

    surveyTasksPageSteps.performSurveyTasksFromGrid(
        listOfLinksSurveyTasks,
        photo,
        DateAndTime.getDateMinusDays(1),
        DateAndTime.getTime(0),
        "3");

    surveyTasksPageSteps
        .open()
        .searchSurveyTasksinGrid(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    /** статус задачи осмотра меняется на Выполнена */
    assertThat(surveyTasksPageSteps.getStatusOfAllServeyTasks())
        .anyMatch("Completed"::equalsIgnoreCase);

    /**
     * 3.9 Выполнение убытия с Терминала. Статус лота Выполнен, статус заявки Доставлена, машина
     * появилась на складе Терминал - как проверять эти статусы????
     */
    logOut();
    loginAs(admin, true);

    lotsPageSteps.open().findLotBySearchAndOpen(lotDataList.get(0).getLotNumber()).openTasksTab();
    lotProfilePageStep.openLogisticTask("Departure", renaultDataForImportLotsForm.getTo());

    switchTo().window(1);

    logisticTaskProfilePageSteps
        .fillDateAndTime(DateAndTime.getDatePlusDays(0), DateAndTime.getTime(2))
        .save();
    switchTo().window(1).close();
    switchTo().window(0);
    refresh();
    lotProfilePageStep.openTasksTab();

    /** проверка статуса логисической задачи Departure = Completed */
    assertThat(
            lotProfilePageStep.getLogisticTaskStatus(
                "Departure", renaultDataForImportLotsForm.getTo()))
        .isEqualTo("Completed");

    lotsPageSteps.open();

    /** // - проверка статуса лота - статус “Транспортировка” */
    lotsPageSteps.open().findLotBySearch(lotDataList.get(0).getLotNumber());
    assertThat(lotsPageSteps.getLotsDataFromGrid().get(0).getState()).isEqualTo("Transportation");

    /** // - проверка статуса заявки на перевозку статус “Транзит” */
    transportOrdersSteps
        .open()
        .searchTransportOrdersByVins(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(
            transportOrdersSteps
                .getOrdersDataFromGrid()
                .stream()
                .map(TransportOrderData::getStatus)
                .collect(Collectors.toList()))
        .anyMatch("Transit"::equalsIgnoreCase); // compair list of string with one value

    /** // - статуса машины на складе (Склад -> Склад -> поиск по VIN-у и точке) */
    warehousePageSteps
        .open()
        .searchVehicalsByVin(
            lotDataList
                .get(0)
                .getVehicleData()
                .stream()
                .map(VehicleData::getVin)
                .collect(Collectors.toList()));

    assertThat(
            warehousePageSteps
                .getWarehouseDataFromGrid()
                .stream()
                .filter(w -> !w.getTransportOrder().isEmpty())
                .findFirst())
        .isEmpty();

    /** 4. Импорт заявки на перевозку в систему Терминал – Дилер Арма моторс (excel-файл 3). */

    //    /**
    //     * // 3.8 Выполнение разгрузки на Терминале. В заявку нужно прикрепить документ ТТН/CMR
    // (на
    //     * форме // заявки таба документы, кнопка Добавить). Статус лота Выполнен, статус заявки
    //     * Доставлена, // машина появилась на складе Терминал
    //     */
    //    lotsPageSteps
    //        .open()
    //        .findLotBySearchAndOpen(lotsFromExcel.get(0).getLotNumber())
    //        .openTasksTab();
    //    lotProfilePageStep.openLogisticTask("Unloading", lotDataForApprove.getTo());
    //
    //    switchTo().window(1);
    //
    //    logisticTaskProfilePageSteps
    //        .fillDateAndTime("current", DateAndTime.getTime(2))
    //        .openDocumentsTab()
    //        .addDocument("ТТN/CMR", file)
    //        .save();
    //
    //    switchTo().window(1).close();
    //    switchTo().window(0);
    //
    //    lotsPageSteps.open();
    //
    //    /** // проверка статуса лота (Логистика -> Лоты -> поиск по VIN-у или по номеру лота) */
    //    assertions
    //
    // assertThat(lotsPageSteps.findLotBySearchAndGetStatus(lotsFromExcel.get(0).getLotNumber()))
    //        .isEmpty();
    //
    //    /** // - статуса машины на складе (Склад -> Склад -> поиск по VIN-у и точке) */
    //    warehousePageSteps
    //        .open()
    //        .searchVehicalsByVin(
    //            lotsFromExcel
    //                .get(0)
    //                .getVehicleData()
    //                .stream()
    //                .map(VehicleData::getVin)
    //                .collect(Collectors.joining(" ")));
    //
    //    assertions
    //        assertThat(
    //            warehousePageSteps
    //                .getWarehouseDataFromGrid()
    //                .stream()
    //                .filter(w -> w.getTransportOrder().isEmpty())
    //                .map(WarehouseData::getWarehouse)
    //                .collect(Collectors.toList())
    //                .toString())
    //        .contains(renaultDataForImportLotsForm.getTo());
    //
    //    //        .assertSoftly(s->{sassertThat(warehousePageSteps
    //    //              .getWarehouseDataFromGrid()
    //    //              .stream()
    //    //              .filter(w -> w.getTransportOrder().isEmpty())
    //    //              .map(WarehouseData::getWarehouse)
    //    //              .collect(Collectors.toList())
    //    //              .toString())
    //    //              .contains(renaultDataForImportLotsForm.getTo());});
    //
    //    //      **************************************************************

    //      softAssertions.assertAll();
  }
}
