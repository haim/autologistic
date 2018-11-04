package com.estafeta.test.ui.test.smoke.transports;

import com.estafeta.test.ui.data.RolesAndPermission.Role;
import com.estafeta.test.ui.data.admin.transportItems.TransportItemsData;
import com.estafeta.test.ui.data.companies.CompanyData;
import com.estafeta.test.ui.data.transports.transports.TransportData;
import com.estafeta.test.ui.data.users.SystemSettings;
import com.estafeta.test.ui.data.users.UserData;
import com.estafeta.test.ui.data.users.UserRole;
import com.estafeta.test.ui.data.vehicles.VehicleData;
import com.estafeta.test.ui.helper.TestData;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.randomizers.EmailRandomizer;
import one.util.streamex.StreamEx;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransportsDataProvider {

  @DataProvider(name = "transport_test")
  public static Object[][] transport_test() throws IOException {

    UserData admin =
        new UserData.Builder()
            .login("estafeta")
            .password("1")
            .company("Communication Company Estafeta")
            .build();

    EnhancedRandom enhancedRandom =
        EnhancedRandomBuilder.aNewEnhancedRandomBuilder().stringLengthRange(10, 15).build();

    TransportItemsData carGeneratedData = enhancedRandom.nextObject(TransportItemsData.class);
    TransportItemsData trailerGeneratedData = enhancedRandom.nextObject(TransportItemsData.class);
    EmailRandomizer emailRandomizer = EmailRandomizer.aNewEmailRandomizer();

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

    UserData personGeneratedData =
        enhancedRandom.nextObject(
            UserData.class,
            "language",
            "email",
            "brands",
            "company",
            "userRoles",
            "systemSettings");

    CompanyData companyData = new CompanyData.Builder().englishName("KATP 13061 PJSC").build();

    SystemSettings systemSettings =
        new SystemSettings.Builder()
            .Verified(true)
            .accessOnlyOwnTasks(true)
            .systemСompany(companyData.getEnglishName())
            .build();

    List<String> phonesList = new ArrayList<>();
    phonesList.add("+380(22)021-14-96");

    List<Role> roles = new ArrayList<>();
    roles.add(new Role.Builder().name("Avtologistika Driver").build());

    UserRole userRole =
        new UserRole.Builder().company(companyData.getEnglishName()).roleName(roles).build();
//    List<UserRole> userRoleList = new ArrayList<>();
//    userRoleList.add(userRole);

    UserData driver =
        new UserData.Builder()
            .email(emailRandomizer.getRandomValue())
            .lastName(personGeneratedData.getLastName())
            .firstName(personGeneratedData.getFirstName())
            .secondName(personGeneratedData.getSecondName())
            .englishFirstName(personGeneratedData.getEnglishFirstName())
            .englishLastName(personGeneratedData.getEnglishLastName())
            .englishSecondName(personGeneratedData.getEnglishSecondName())
            .login(personGeneratedData.getLogin())
            .password(personGeneratedData.getPassword())
            .language("English")
            .phoneNumbers(phonesList)
            .systemSettings(systemSettings)
            .rolesActive(true)
            .userRoles(userRole)
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
            testData.generateLotsNumber(1); // generated String List of Lots number
    List<String> generatedVinsNumber =
            testData.generateVinsNumber(3); // generated String List of Vins number

    int amountVinsForEachLot =
            generatedVinsNumber.size()
                    / generatedLotsNumber.size(); // count how many vins will be add to each  of lots
    System.out.println("vins for each lots: " + amountVinsForEachLot);

    // create vehicles list
    List<VehicleData> vehicleListData = new ArrayList<>();
    for (int i = 0; i < generatedVinsNumber.size(); i++) {
      vehicleListData.add(
              new VehicleData.Builder().vin(" " + generatedVinsNumber.get(i) + " ").build());
    }
    System.out.println("Main cars list: " + vehicleListData.size());

    List<List<VehicleData>> listOfVehList =
            StreamEx.ofSubLists(vehicleListData, amountVinsForEachLot).toList();


    return new Object[][] {{admin, driver, car, trailer, newTransport,vehicleListData}};
  }
}
