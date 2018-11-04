package com.estafeta.test.ui.stepdefinitions.Warehouse.blocks;

import com.codeborne.selenide.Condition;
import com.estafeta.test.ui.stepdefinitions.Warehouse.WarehousePageSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SearchBlock {

  @Step("Search '{0}'")
  public WarehousePageSteps searchLotInGridByVin(String vin) {
    $(By.cssSelector("#mineKWidget > input")).scrollTo().shouldBe(Condition.visible).setValue(vin);
    $(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"))
        .shouldBe(Condition.visible);
    $(By.xpath("//a[@title='Search for warehouse vehicles']")).click();
    return page(WarehousePageSteps.class);
  }
}
