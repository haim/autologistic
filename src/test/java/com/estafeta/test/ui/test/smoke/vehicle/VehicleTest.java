package com.estafeta.test.ui.test.smoke.vehicle;

import com.estafeta.test.ui.data.vehicles.VehicleData;
import com.estafeta.test.ui.helper.BaseTest;
import com.estafeta.test.ui.preconditions.LoginRequired;
import com.estafeta.test.ui.stepdefinitions.HomePageSteps;
import com.estafeta.test.ui.stepdefinitions.vehicles.VehiclesGeneralSteps;
import com.poiji.bind.Poiji;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class VehicleTest extends BaseTest {

  String file =
      "/home/dnikiforov/Dropbox/code/avtologistika/estafeta/autologistic/estafeta/src/test/resources/excelFiles/Check_route_Toyota_Lexus.xlsx";

  @Test
  @LoginRequired
  public void create_new_renault_vehicals_by_manual_import()
      throws InterruptedException, AWTException {
    List<VehicleData> vehiclesFromExcel = Poiji.fromExcel(new File(file), VehicleData.class);

    VehiclesGeneralSteps vehiclesGeneralSteps = new HomePageSteps().openVehiclesPage();

    //    vehiclesGeneralSteps.importVehicles(file).searchInGrid(vehiclesFromExcel.get(0).getVin());
    assertThat(vehiclesGeneralSteps.getVehicleVin(vehiclesFromExcel.get(0).getVin()))
        .isEqualTo(vehiclesFromExcel.get(0).getVin());
  }

  @Test
  @LoginRequired
  public void create_new_vehicle() {
    VehiclesGeneralSteps vehiclesGeneralSteps = new VehiclesGeneralSteps();
    //    vehiclesGeneralSteps
    //        .createNewCar("JTMDFREV80D036567", "Acura", "RDX", "3.5 AT (273 hp)")
    //        .searchInGrid("JTMDFREV80D036567");
    assertThat(vehiclesGeneralSteps.getVehicleVin("JTMDFREV80D036567"))
        .isEqualTo("JTMDFREV80D036567");
  }
}
