package com.estafeta.test.ui.test.smoke.admin.users;

import com.estafeta.test.ui.data.RolesAndPermission.Role;
import com.estafeta.test.ui.data.companies.CompanyData;
import com.estafeta.test.ui.data.users.SystemSettings;
import com.estafeta.test.ui.data.users.UserData;
import com.estafeta.test.ui.data.users.UserRole;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.randomizers.EmailRandomizer;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class UserDataProvider {

  @DataProvider(name = "new_driver")
  public static Object[][] new_driver() {

    /** Custom random userdata */
    EnhancedRandom enhancedRandom =
        EnhancedRandomBuilder.aNewEnhancedRandomBuilder().stringLengthRange(10, 15).build();

    EmailRandomizer emailRandomizer = EmailRandomizer.aNewEmailRandomizer();

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
    roles.add(new Role.Builder().name("Warehouse Surveyor").build());

    UserRole userRole =
        new UserRole.Builder().company(companyData.getEnglishName()).roleName(roles).build();

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

    List<Role> rolesForWarehouseSurveyor = new ArrayList<>();
    rolesForWarehouseSurveyor.add(new Role.Builder().name("Warehouse Surveyor").build());
    rolesForWarehouseSurveyor.add(new Role.Builder().name("Avtologistika-Romania Lots Changing Transport").build());

    EmailRandomizer emailRandomizerForWarehouseSurveyor = EmailRandomizer.aNewEmailRandomizer();

    UserData WarehouseSurveyorGeneratedData =
            enhancedRandom.nextObject(
                    UserData.class,
                    "language",
                    "email",
                    "brands",
                    "company",
                    "userRoles",
                    "systemSettings");
    CompanyData companyDataForWarehouseSurveyor = new CompanyData.Builder().englishName("Avtologistika - Romania").build();

    UserRole warehouseSurveyorRole =
            new UserRole.Builder()
                    .company(companyDataForWarehouseSurveyor.getEnglishName())
                    .roleName(rolesForWarehouseSurveyor)
                    .build();


    SystemSettings systemSettingsForWarehouseSurveyor =
            new SystemSettings.Builder()
                    .Verified(true)
                    .accessOnlyOwnTasks(false)
                    .systemСompany(companyDataForWarehouseSurveyor.getEnglishName())
                    .build();

    UserData warehouseSurveyor =
            new UserData.Builder()
                    .email(emailRandomizerForWarehouseSurveyor.getRandomValue())
                    .lastName(WarehouseSurveyorGeneratedData.getLastName())
                    .firstName(WarehouseSurveyorGeneratedData.getFirstName())
                    .secondName(WarehouseSurveyorGeneratedData.getSecondName())
                    .englishFirstName(WarehouseSurveyorGeneratedData.getEnglishFirstName())
                    .englishLastName(WarehouseSurveyorGeneratedData.getEnglishLastName())
                    .englishSecondName(WarehouseSurveyorGeneratedData.getEnglishSecondName())
                    .login(WarehouseSurveyorGeneratedData.getLogin())
                    .password(WarehouseSurveyorGeneratedData.getPassword())
                    .language("English")
                    .phoneNumbers(phonesList)
                    .systemSettings(systemSettingsForWarehouseSurveyor)
                    .rolesActive(true)
                    .userRoles(warehouseSurveyorRole)
                    .build();


    return new Object[][] {{driver, warehouseSurveyor}};
  }
}
