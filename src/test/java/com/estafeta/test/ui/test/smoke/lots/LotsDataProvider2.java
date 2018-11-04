package com.estafeta.test.ui.test.smoke.lots;

import com.estafeta.test.ui.data.RolesAndPermission.Role;
import com.estafeta.test.ui.data.admin.transportItems.TransportItemsData;
import com.estafeta.test.ui.data.companies.CompanyData;
import com.estafeta.test.ui.data.lots.BorderCrossingData;
import com.estafeta.test.ui.data.lots.LotData;
import com.estafeta.test.ui.data.transports.transports.TransportData;
import com.estafeta.test.ui.data.users.SystemSettings;
import com.estafeta.test.ui.data.users.UserData;
import com.estafeta.test.ui.data.users.UserRole;
import com.estafeta.test.ui.data.vehicles.VehicleData;
import com.estafeta.test.ui.helper.DateAndTime;
import com.estafeta.test.ui.helper.TestData;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.randomizers.EmailRandomizer;
import one.util.streamex.StreamEx;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class LotsDataProvider2 {

  @DataProvider(name = "create_lot_by_excel_renault_cars2")
  public static Object[][] create_lot_by_excel_renault_cars2() throws IOException {

    UserData admin =
        new UserData.Builder()
            .login("estafeta")
            .password("1")
            .company("Communication Company Estafeta")
            .build();

    EnhancedRandom enhancedRandomForCar =
        EnhancedRandomBuilder.aNewEnhancedRandomBuilder().stringLengthRange(10, 15).build();
    EnhancedRandom enhancedRandomForTrailer =
        EnhancedRandomBuilder.aNewEnhancedRandomBuilder().stringLengthRange(10, 15).build();

    TransportItemsData carGeneratedData = enhancedRandomForCar.nextObject(TransportItemsData.class);
    TransportItemsData trailerGeneratedData =
        enhancedRandomForTrailer.nextObject(TransportItemsData.class);
    EmailRandomizer emailRandomizerForDriver = EmailRandomizer.aNewEmailRandomizer();
    EmailRandomizer emailRandomizerForlogisticsOperator = EmailRandomizer.aNewEmailRandomizer();
    EmailRandomizer emailRandomizerForWarehouseSurveyorRomania =
        EmailRandomizer.aNewEmailRandomizer();
    EmailRandomizer emailRandomizerForWarehouseTerminal = EmailRandomizer.aNewEmailRandomizer();

    TransportItemsData car =
        new TransportItemsData.Builder()
            .active(true)
            .verified(true)
            .type("Car Transporter")
            .number(carGeneratedData.getNumber())
            .carrier("KATP 13061 PJSC")
            .trailer(false)
            .brand("DAF")
            .model("FT75CF")
            .description("вантажний сідловий тягач")
            .build();

    TransportItemsData trailer =
        new TransportItemsData.Builder()
            .active(true)
            .verified(true)
            .type("Car Transporter")
            .number(trailerGeneratedData.getNumber())
            .carrier("KATP 13061 PJSC")
            .trailer(true)
            .transportItemPartType("Eurolohr")
            .brand("LOHR")
            .model("1.21Е1")
            .description("Напівпричіп (автовоз)")
            .build();

    UserData driverGeneratedData =
        enhancedRandomForCar.nextObject(
            UserData.class,
            "language",
            "email",
            "brands",
            "company",
            "userRoles",
            "systemSettings");

    UserData logisticsOperatorGeneratedData =
        enhancedRandomForCar.nextObject(
            UserData.class,
            "language",
            "email",
            "brands",
            "company",
            "userRoles",
            "systemSettings");

    UserData WarehouseSurveyorRomaniaGeneratedData =
        enhancedRandomForCar.nextObject(
            UserData.class,
            "language",
            "email",
            "brands",
            "company",
            "userRoles",
            "systemSettings");

    UserData WarehouseSurveyorTerminalGeneratedData =
        enhancedRandomForCar.nextObject(
            UserData.class,
            "language",
            "email",
            "brands",
            "company",
            "userRoles",
            "systemSettings");

    CompanyData companyDataForDriver =
        new CompanyData.Builder().englishName("KATP 13061 PJSC").build();
    CompanyData companyDataForLogisticsOperator =
        new CompanyData.Builder().englishName("Avtologistika - Romania").build();
    CompanyData   companyDataForWarehouseSurveyorRomania =
        new CompanyData.Builder().englishName("Avtologistika - Romania").build();
    CompanyData companyDataForWarehouseSurveyorTerminal =
        new CompanyData.Builder().englishName("Terminal Avtologistika").build();

    SystemSettings systemSettingsForDriver =
        new SystemSettings.Builder()
            .Verified(true)
            .accessOnlyOwnTasks(true)
            .systemСompany(companyDataForDriver.getEnglishName())
            .build();

    SystemSettings systemSettingsForLogisticsOperator =
        new SystemSettings.Builder()
            .Verified(true)
            .accessOnlyOwnTasks(false)
            .systemСompany(companyDataForLogisticsOperator.getEnglishName())
            .build();

    SystemSettings systemSettingsForWarehouseSurveyorRomania =
        new SystemSettings.Builder()
            .Verified(true)
            .accessOnlyOwnTasks(false)
            .systemСompany(companyDataForWarehouseSurveyorRomania.getEnglishName())
            .build();

    SystemSettings systemSettingsForWarehouseSurveyorTerminal =
        new SystemSettings.Builder()
            .Verified(true)
            .accessOnlyOwnTasks(false)
            .systemСompany(companyDataForWarehouseSurveyorTerminal.getEnglishName())
            .build();

    List<String> phonesList = new ArrayList<>();
    phonesList.add("+380(22)021-14-96");

    List<Role> rolesForDriver = new ArrayList<>();
    rolesForDriver.add(new Role.Builder().name("Avtologistika Driver").build());

    List<Role> rolesForLogisticsOperator = new ArrayList<>();
    rolesForLogisticsOperator.add(new Role.Builder().name("Logistics specialist").build());

    UserRole driverRole =
        new UserRole.Builder()
            .company(companyDataForDriver.getEnglishName())
            .roleName(rolesForDriver)
            .build();

    UserRole LogisticsOperatorRole =
        new UserRole.Builder()
            .company(companyDataForLogisticsOperator.getEnglishName())
            .roleName(rolesForLogisticsOperator)
            .build();

    List<Role> rolesForWarehouseSurveyorRomania = new ArrayList<>();
    rolesForWarehouseSurveyorRomania.add(new Role.Builder().name("Warehouse Surveyor").build());
//    rolesForWarehouseSurveyorRomania.add(new Role.Builder().name("Avtologistika-Romania Lots Changing Transport").build());
    rolesForWarehouseSurveyorRomania.add(new Role.Builder().name("Dispatcher").build());

    List<Role> rolesForWarehouseSurveyorTerminal = new ArrayList<>();
    rolesForWarehouseSurveyorTerminal.add(new Role.Builder().name("Warehouse Surveyor").build());
//    rolesForWarehouseSurveyorTerminal.add(new Role.Builder().name("Avtologistika-Romania Lots Changing Transport").build());
    rolesForWarehouseSurveyorTerminal.add(new Role.Builder().name("Dispatcher").build());

    UserRole warehouseSurveyorRomaniaRole =
        new UserRole.Builder()
            .company(companyDataForWarehouseSurveyorRomania.getEnglishName())
            .roleName(rolesForWarehouseSurveyorRomania)
            .build();

    UserRole warehouseSurveyorTerminalRole =
        new UserRole.Builder()
            .company(companyDataForWarehouseSurveyorTerminal.getEnglishName())
            .roleName(rolesForWarehouseSurveyorTerminal)
            .build();

    UserData driver =
        new UserData.Builder()
            .email(emailRandomizerForDriver.getRandomValue())
            .lastName(driverGeneratedData.getLastName())
            .firstName(driverGeneratedData.getFirstName())
            .secondName(driverGeneratedData.getSecondName())
            .englishFirstName(driverGeneratedData.getEnglishFirstName())
            .englishLastName(driverGeneratedData.getEnglishLastName())
            .englishSecondName(driverGeneratedData.getEnglishSecondName())
            .login(driverGeneratedData.getLogin())
            .password(driverGeneratedData.getPassword())
            .language("English")
            .phoneNumbers(phonesList)
            .systemSettings(systemSettingsForDriver)
            .rolesActive(true)
            .userRoles(driverRole)
            .build();

    UserData logisticsOperator =
        new UserData.Builder()
            .email(emailRandomizerForlogisticsOperator.getRandomValue())
            .lastName(logisticsOperatorGeneratedData.getLastName())
            .firstName(logisticsOperatorGeneratedData.getFirstName())
            .secondName(logisticsOperatorGeneratedData.getSecondName())
            .englishFirstName(logisticsOperatorGeneratedData.getEnglishFirstName())
            .englishLastName(logisticsOperatorGeneratedData.getEnglishLastName())
            .englishSecondName(logisticsOperatorGeneratedData.getEnglishSecondName())
            .login(logisticsOperatorGeneratedData.getLogin())
            .password(logisticsOperatorGeneratedData.getPassword())
            .language("English")
            .phoneNumbers(phonesList)
            .systemSettings(systemSettingsForLogisticsOperator)
            .rolesActive(true)
            .userRoles(LogisticsOperatorRole)
            .build();

    UserData warehouseSurveyorRomania =
        new UserData.Builder()
            .email(emailRandomizerForWarehouseSurveyorRomania.getRandomValue())
            .lastName(WarehouseSurveyorRomaniaGeneratedData.getLastName())
            .firstName(WarehouseSurveyorRomaniaGeneratedData.getFirstName())
            .secondName(WarehouseSurveyorRomaniaGeneratedData.getSecondName())
            .englishFirstName(WarehouseSurveyorRomaniaGeneratedData.getEnglishFirstName())
            .englishLastName(WarehouseSurveyorRomaniaGeneratedData.getEnglishLastName())
            .englishSecondName(WarehouseSurveyorRomaniaGeneratedData.getEnglishSecondName())
            .login(WarehouseSurveyorRomaniaGeneratedData.getLogin())
            .password(WarehouseSurveyorRomaniaGeneratedData.getPassword())
            .language("English")
            .phoneNumbers(phonesList)
            .systemSettings(systemSettingsForWarehouseSurveyorRomania)
            .rolesActive(true)
            .userRoles(warehouseSurveyorRomaniaRole)
            .build();

    UserData warehouseSurveyorTerminal =
        new UserData.Builder()
            .email(emailRandomizerForWarehouseTerminal.getRandomValue())
            .lastName(WarehouseSurveyorTerminalGeneratedData.getLastName())
            .firstName(WarehouseSurveyorTerminalGeneratedData.getFirstName())
            .secondName(WarehouseSurveyorTerminalGeneratedData.getSecondName())
            .englishFirstName(WarehouseSurveyorTerminalGeneratedData.getEnglishFirstName())
            .englishLastName(WarehouseSurveyorTerminalGeneratedData.getEnglishLastName())
            .englishSecondName(WarehouseSurveyorTerminalGeneratedData.getEnglishSecondName())
            .login(WarehouseSurveyorTerminalGeneratedData.getLogin())
            .password(WarehouseSurveyorTerminalGeneratedData.getPassword())
            .language("English")
            .phoneNumbers(phonesList)
            .systemSettings(systemSettingsForWarehouseSurveyorTerminal)
            .rolesActive(true)
            .userRoles(warehouseSurveyorTerminalRole)
            .build();

    TransportData newTransport =
        new TransportData.Builder()
            .owner("KATP 13061 PJSC")
            .transport(car.getNumber())
            .transportPart(trailer.getNumber())
            .firstDriver(driver.getLastName())
            .build();

    TestData testData = new TestData();
    List<String> generatedLotsNumber =
        testData.generateLotsNumber(3); // generated String List of Lots number
    List<String> generatedVinsNumber =
        testData.generateVinsNumber(6); // generated String List of Vins number

    testData.createRenaultNewVehiclesForImportByXLSX(
        generatedVinsNumber); // create renault car file for import
    testData.createLotsFileForImport(
        generatedLotsNumber, generatedVinsNumber); // create renault lots file for import

    int amountVinsForEachLot =
        generatedVinsNumber.size()
            / generatedLotsNumber.size(); // count how many vins will be add to each  of lots
    System.out.println("vins for each lots: " + amountVinsForEachLot);

    // create vehicles list
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
              .lotNumber("DU-" + generatedLotsNumber.get(i))
              .vehicleData(listOfVehList.get(i))
              .state("Truck Choosing")
              .loadingPoint("Avtologistika - Romania")
              .build());
    }

    LotData renaultDataForImportLotsForm =
        new LotData.Builder()
            .client("Renault Ukraine")
            .shippingAgent("Avtologistika - Forwarding and Logistics Services")
            .from("Avtologistika - Romania")
            .to("Terminal Avtologistika")
            .xmlForImport(
                "src/test/resources/LotsRenaultVehicles.xlsx")
            .build();

    List<BorderCrossingData> borderCrossingData = new ArrayList<>();
    borderCrossingData.add(
        new BorderCrossingData.Builder()
            .name("П/п \"Порубне - Сірет\"")
            .date(DateAndTime.getDatePlusDays(1))
            .crossingTime("03:00")
            .build());

    LotData lotDataForApprove =
        new LotData.Builder()
            .truckType("Eurolohr")
            .truck(newTransport.getTransport())
            .loadingDateDate(DateAndTime.getDatePlusDays(0))
            .loadingDateTime(DateAndTime.getTime(2))
            .warrantyType("EX")
            .borderCrossingData(borderCrossingData)
            .loadingDateTime("03:00")
            .to("Terminal Avtologistika")
            .toDate(DateAndTime.getDatePlusDays(2))
            .toTime("03:00")
            .build();

    // arma motors imort transort orders file
    testData.createLotsFileForImportDillerArmaMotors(lotDataList
            .get(0)
            .getVehicleData()
            .stream()
            .map(VehicleData::getVin)
            .collect(Collectors.toList()));

    return new Object[][] {
      {
        renaultDataForImportLotsForm,
        lotDataForApprove,
        lotDataList,
        generatedLotsNumber,
        generatedVinsNumber,
        vehicleListData,
        admin,
        driver,
        logisticsOperator,
        warehouseSurveyorRomania,
        warehouseSurveyorTerminal,
        car,
        trailer,
        newTransport
      }
    };
  }
}
