package com.estafeta.test.ui.stepdefinitions.vehicles;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.data.vehicles.VehicleData;
import io.qameta.allure.Step;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class VehiclesGeneralSteps {

  @Step
  public VehiclesGeneralSteps open() {
    Selenide.open("/Vehicles/List");
    return page(VehiclesGeneralSteps.class);
  }

  @Step
  public VehiclesGeneralSteps importVehicles(String filenameVinXLSX) {
    executeJavaScript(
        "document.querySelector('#userLayoutBody > div.page-toolbar > div > div.page-toolbar-actions > div:nth-child(1) > div:nth-child(3)').style.display='block';");
    $("#userLayoutBody > div.page-toolbar > div > div.page-toolbar-actions > div:nth-child(1) > div:nth-child(3)")
        .shouldBe(Condition.visible);
    executeJavaScript("vehiclesList.ImportedObject = 0");
    $(By.xpath("//div[@class='k-button k-upload-button']/input[@id='fileInput']"))
        .uploadFile(new File(filenameVinXLSX));
    $(By.xpath("//div[@id='parsingResultForm']//h3")).waitUntil(Condition.visible, 20000);
    $(By.xpath("//a[@id='btnUpload']")).click();
    $(By.xpath("//div[@id='importResultForm']")).waitUntil(Condition.visible, 20000);
    $(By.xpath("//a[@id='anotherFileBtnImportResult']/..//a[text()='Close']")).click();
    return page(VehiclesGeneralSteps.class);
  }

  @Step
  public VehiclesGeneralSteps importVehicles2(String filenameVinXLSX) {

    executeJavaScript(
        "document.querySelector('#userLayoutBody > div.page-toolbar > div > div.page-toolbar-actions > div:nth-child(1) > div:nth-child(3)').style.display='block';");
    $("#userLayoutBody > div.page-toolbar > div > div.page-toolbar-actions > div:nth-child(1) > div:nth-child(3)")
        .shouldBe(Condition.visible);
    executeJavaScript("vehiclesList.ImportedObject = 0");
    $(By.xpath("//div[@class='k-button k-upload-button']/input[@id='fileInput']"))
        .uploadFile(new File(filenameVinXLSX));

    $(By.xpath("//a[@id='importButton']/span")).uploadFile(new File(filenameVinXLSX));
    // new RobotFileUpload().robotInsertText(filenameVinXLSX);
    $(By.xpath("//div[@id='parsingResultForm']//h3")).waitUntil(Condition.visible, 20000);
    $(By.xpath("//a[@id='btnUpload']")).click();
    $(By.xpath("//a[@id='anotherFileBtnImportResult']/..//a[text()='Close']")).click();
    $(By.xpath("//a[@id='anotherFileBtnImportResult']/..//a[text()='Close']"))
        .shouldNotBe(Condition.visible);
    return page(VehiclesGeneralSteps.class);
  }

  //  @Step("Search '{0}'")
  //  public VehiclesGeneralSteps searchInGrid(String vin) {
  //    $(By.cssSelector("#mineKWidget > input")).setValue(vin);
  //    $(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"))
  //        .shouldBe(Condition.visible);
  //    $(By.xpath("//a[@title = 'Apply filters']")).click();
  //    return this;
  //  }

  // TODO - Selenoid
  // https://github.com/aerokube/selenoid/issues/550
  private String clipboardPost(String post) {

    //    getWebDriver().getSessionId();
    SessionId postText = ((RemoteWebDriver) getWebDriver()).getSessionId();
    HttpPost httpPost = new HttpPost();
    return post;
  }

  // TODO - Selenoid
  // https://github.com/aerokube/selenoid/issues/550
  private String clipboardGet(String get) {

    return get;
  }

  @Step("Search '{0}'")
  public VehiclesGeneralSteps searchInGrid(List<String> vin) {
    SelenideElement s =
        $(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"));
    if (s.is(Condition.exist)) {
      $x("//a[@title='Clear filters']").click();
    }

//    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//    clipboard.setContents(new StringSelection(String.valueOf(vin)), null);
//
//    $("#mineKWidget > input").clear();
//    $("#mineKWidget > input").sendKeys(Keys.CONTROL, "V");

    String join = "'" + StringUtils.join(vin.toArray(), "','") +"'";
    System.out.println(join);

    executeJavaScript("$('#vehiclesMultiSelect').data('kendoMultiSelect').setDataSource(["+join+"]);");
    executeJavaScript("$('#vehiclesMultiSelect').data('kendoMultiSelect').value(["+join+"]);");


    $$(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"))
        .shouldHaveSize(vin.size());

    $(By.xpath("//a[@title = 'Apply filters']")).click();
    return page(VehiclesGeneralSteps.class);
  }

  @Step
  public String getVehicleVin(String vehickeVin) {
    return $$(By.xpath("//tbody[@role='rowgroup']/tr/td/a"))
        .findBy(Condition.text(vehickeVin))
        .getText();
  }

  @Step
  public VehiclesGeneralSteps createNewCar(
      String vin, String brand, String model, String modification) {
    $(By.xpath("//a[text() = 'Create']")).click();
    $(By.xpath("//input[@name='Vin']")).setValue(vin);
    $(By.xpath(
            "//*[normalize-space(text()) and normalize-space(.)='Brand:'][1]/following::span[3]"))
        .click();
    $$(By.xpath("//ul[@id='BrandId_listbox']/li")).findBy(Condition.text(brand)).click();
    $(By.xpath(
            ".//*[normalize-space(text()) and normalize-space(.)='Model:'][1]/following::span[3]"))
        .click();
    $$(By.xpath("//ul[@id='ModelId_listbox']/li")).findBy(Condition.text(model)).click();
    $(By.xpath(
            ".//*[normalize-space(text()) and normalize-space(.)='Modification:'][1]/following::span[3]"))
        .click();
    $$(By.xpath("//ul[@id='ModificationId_listbox']/li"))
        .findBy(Condition.text(modification))
        .click();
    $(By.xpath("//input[@id='selectButton']")).click();
    return this;
  }

  @Step
  public List<VehicleData> getVehicleDataFromGrid() {

    List<VehicleData> vehicleDataList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@class='k-grid-content']/table[@role='grid']/tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));
    int row_count = rows.size();

    for (int row = 0; row < rows.size(); row++) {
      String vin = rows.get(row).$$(By.xpath("td")).get(0).text();
      String brand = rows.get(row).$$(By.xpath("td")).get(1).text();
      String model = rows.get(row).$$(By.xpath("td")).get(2).text();
      String modelCode = rows.get(row).$$(By.xpath("td")).get(3).text();

      vehicleDataList.add(
          new VehicleData.Builder()
              .vin(" " + vin + " ")
              .brand(brand)
              .model(model)
              .modelCode(modelCode)
              //              .creationDate(creationDate)
              .build());
    }
    return vehicleDataList;
  }
}
