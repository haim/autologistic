package com.estafeta.test.ui.stepdefinitions.logistics.lots.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.stepdefinitions.Warehouse.WarehousePageSteps;
import io.qameta.allure.Step;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchLotsBlock {

  @Step("Search '{0}'")
  public WarehousePageSteps searchLotInGridByVin(List<String> vin) {

    SelenideElement s =
        $(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"));
    if (s.is(Condition.exist)) {
      $x("//a[@title='Clear filters']").click();
    }

    //    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    //    clipboard.setContents(new StringSelection(String.valueOf(vin)), null);
    //
    //    $(By.cssSelector("#mineKWidget > input")).clear();
    //    $(By.cssSelector("#mineKWidget > input")).sendKeys(Keys.CONTROL, "V");

    String join = "'" + StringUtils.join(vin.toArray(), "','") + "'";
    System.out.println(join);

    executeJavaScript(
        "$('#vehiclesMultiSelect').data('kendoMultiSelect').setDataSource([" + join + "]);");
    executeJavaScript("$('#vehiclesMultiSelect').data('kendoMultiSelect').value([" + join + "]);");

    $$(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"))
        .shouldHaveSize(vin.size());

    $(By.xpath("//a[@title='Search for warehouse vehicles']")).click();
    return page(WarehousePageSteps.class);
  }

  @Step
  public SearchLotsBlock searchLotInGridByLotNumber(String lotNumber) {
    $(By.xpath("//input[@id='LotNumber']")).setValue(lotNumber);
    $(By.xpath("//a[@title='Apply filters']")).click();
    return page(SearchLotsBlock.class);
  }
}
