package com.estafeta.test.ui.test.smoke.vehicle;

import com.estafeta.test.ui.data.vehicles.VehicleData;
import com.poiji.bind.Poiji;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.List;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class VehicleDataProvider {

  String newVehicle =
      "/home/dnikiforov/Dropbox/code/avtologistika/estafeta/autologistic/estafeta/src/test/resources/data/renault/Renault_new_VINs_import.xlsx";

  //    @DataProvider(name = "Verification, that project cannot be created without specifying all
  // required fields")
  //    public static Object[][] shouldNotAllowToCreateProjectWithoutSpecifyingRequiredFields() {
  //        VehicleDataProvider vehicleData = new VehicleDataProvider().Builder()
  //                .name()
  //                .laboratory(SampleData.LAB_NO_LAB)
  //                .area(randomizeName("Area")).build();
  //        return new Object[][]{{projectData}};
  //    }

  @DataProvider(name = "")
  public static Object[][] create_new_renault_vehicals_by_manual_import() {
    List<VehicleData> vehiclesFromExcel =
        Poiji.fromExcel(
            new File("src/test/resources/data/renault/Renault_new_VINs_import.xlsx"),
            VehicleData.class);

    //        List<>

    return new Object[][] {{}};
  }
}
