package com.estafeta.test.ui.test.smoke.searchTest;

import com.estafeta.test.ui.helper.BaseTest;
import com.estafeta.test.ui.preconditions.LoginRequired;
import com.estafeta.test.ui.stepdefinitions.vehicles.VehiclesGeneralSteps;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class SearchTest extends BaseTest {

  String welcomeTitle = "Welcome to Estafeta Automotive";

  String file =
      "/home/dnikiforov/Dropbox/code/avtologistika/estafeta/autologistic/estafeta/src/test/resources/data/Check_route_Toyota_Lexus.xlsx";

  VehiclesGeneralSteps vehiclesGeneralSteps = new VehiclesGeneralSteps();

  //  @BeforeTest
  //  @LoginRequired
  //  public void precondition() throws MalformedURLException {
  //    LoginPageSteps loginPageSteps =
  //        open("https://amt2.estafeta.org/Account/LogOn?ReturnUrl=%2f", LoginPageSteps.class);
  //    //    loginPageSteps.loginAs(username, password, companyName);
  //    assertEquals($(By.xpath("//h1")).getText(), welcomeTitle);
  //    homePageSteps.openVehiclesPage();
  //  }

  @Test
  @LoginRequired
  public void search_car_by_vin_code() throws InterruptedException, AWTException {

    //    new VehiclesGeneralSteps().searchInGrid("WDB9702571L812046");
    System.out.printf(vehiclesGeneralSteps.getVehicleVin("WDB9702571L812046"));
  }
}
