package com.estafeta.test.ui.stepdefinitions.logistics.transportOrders.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.stepdefinitions.logistics.transportOrders.TransportOrdersSteps;
import io.qameta.allure.Step;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author dnikiforov
 * @project amt
 */
public class SearchTransportOrderBlock {

  @Step("Search '{0}'")
  public TransportOrdersSteps searchInGrid(List<String> vin) {
    SelenideElement s =
        $(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"));
    if (s.is(Condition.exist)) {
      $x("//a[@title='Clear filters']").click();
    }

    $(By.id("loadingOverlay")).waitUntil(Condition.disappear, 20000);
    String join = "'" + StringUtils.join(vin.toArray(), "','") +"'";
    System.out.println(join);

    executeJavaScript("$('#vehiclesMultiSelect').data('kendoMultiSelect').setDataSource(["+join+"]);");
    executeJavaScript("$('#vehiclesMultiSelect').data('kendoMultiSelect').value(["+join+"]);");


//    $("#mineKWidget > input").clear();
//    $("#mineKWidget > input").sendKeys(Keys.CONTROL, "V");

    $(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"))
        .waitUntil(Condition.visible, 20000);
    $$(By.xpath("//ul[@id='vehiclesMultiSelect_taglist']//span[text()='delete']"))
        .shouldHaveSize(vin.size());

    $(By.xpath("//a[@title = 'Apply filters']")).click();
    return page(TransportOrdersSteps.class);
  }
}
