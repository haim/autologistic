package com.estafeta.test.ui.test.smoke.lots;

import com.estafeta.test.ui.data.admin.transportItems.TransportItemsData;
import com.estafeta.test.ui.data.lots.LotData;
import com.estafeta.test.ui.data.transports.transports.TransportData;
import com.estafeta.test.ui.data.users.UserData;
import com.estafeta.test.ui.data.vehicles.VehicleData;
import com.estafeta.test.ui.helper.BaseTest;
import com.estafeta.test.ui.stepdefinitions.logistics.lotting.LottingSteps;
import com.estafeta.test.ui.stepdefinitions.vehicles.VehiclesGeneralSteps;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.estafeta.test.ui.stepdefinitions.LoginPageSteps.loginAs;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ImportLotTest extends BaseTest {

  @Test(
      dataProvider = "create_lot_by_excel_renault_cars2",
      dataProviderClass = LotsDataProvider2.class)
  public void test(
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
    String vehicl =
        "/home/dnikiforov/Dropbox/code/avtologistika/estafeta/autologistic/estafeta/src/test/resources/RenaultVehicles.xlsx";

    /** login as admin */
    loginAs(admin, true);

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

    assertThat(vehiclesGeneralSteps.getVehicleDataFromGrid().size())
        .isEqualTo(vehicleListData.size());

    /** 2) Импорт файла с лотами */
    LottingSteps lottingSteps = new LottingSteps();
    lottingSteps.open().createLotByImport(renaultDataForImportLotsForm);
  }
}
