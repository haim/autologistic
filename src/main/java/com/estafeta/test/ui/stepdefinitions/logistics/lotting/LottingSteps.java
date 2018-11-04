package com.estafeta.test.ui.stepdefinitions.logistics.lotting;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.estafeta.test.ui.data.lots.LotData;
import com.estafeta.test.ui.stepdefinitions.logistics.lots.LotProfilePageStep;
import com.estafeta.test.ui.stepdefinitions.logistics.lotting.blocks.ImportLotsBlock;
import com.estafeta.test.ui.stepdefinitions.logistics.lotting.blocks.LotEditViewSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class LottingSteps {

  ImportLotsBlock importLotsBlock = new ImportLotsBlock();
  LotEditViewSteps lotEditViewSteps = new LotEditViewSteps();

  @Step
  public LottingSteps createLotByImport(LotData lotData) {
    $(By.xpath("//input[@id='importLotsButton']"))
        .waitUntil(Condition.visible.because("some times not visible"), 2000)
        .click();
    importLotsBlock.filldData(
        lotData.getClient(),
        lotData.getShippingAgent(),
        lotData.getFrom(),
        lotData.getTo(),
        lotData.getXmlForImport());
    $x("//div[@id='loadingOverlay']").waitUntil(Condition.disappear, 20000);
    $(By.id("importLotsView")).waitUntil(Condition.disappear, 20000);
    $x("//div[@id='loadingOverlay']").waitUntil(Condition.disappear, 20000);
    return page(LottingSteps.class);
  }

  private void selectFromDropdown(String text) {
    $$(By.xpath("//div/span")).findBy(Condition.text(text)).click();
  }

  @Step
  public List<LotData> getLotsNumberDataFromGrid() {
    $(By.xpath("//div[@data-cookiename='LotsBoard']//tbody[@role='rowgroup']/tr/td/a"))
        .waitUntil(Condition.visible, 200000);
    List<LotData> lots = new ArrayList<>();

    for (String s :
        $$(By.xpath("//div[@data-cookiename='LotsBoard']//tbody[@role='rowgroup']/tr/td/a"))
            .texts()) {

      lots.add(new LotData.Builder().lotNumber(s).build());
    }
    return lots;
  }

  @Step
  public String getLotsStatusDataFromGrid(String lotNumber) {
    $(By.xpath("//div[@data-cookiename='LotsBoard']//tbody[@role='rowgroup']/tr/td/a"))
        .waitUntil(Condition.visible, 200000);

    return $(By.xpath(
            "//div[@data-cookiename='LotsBoard']//tbody[@role='rowgroup']/tr/td/a[contains(text(),'"
                + lotNumber
                + "')]/../../td[2]"))
        .shouldBe(Condition.visible)
        .text();
  }

  @Step
  public LottingSteps open() {
    Selenide.open("/Lots/Board");
    return page(LottingSteps.class);
  }

  @Step
  public LotEditViewSteps openLot(String lotNumber) {
    $(By.xpath("//div[@id='lotsGrid']//a[@class='gridLink']"))
        .shouldBe(Condition.visible)
        .shouldHave(Condition.text(lotNumber))
        .click();
    return page(LotEditViewSteps.class);
  }

  @Step
  public LotProfilePageStep approveRoute() {
    lotEditViewSteps.approveRoute();
    return page(LotProfilePageStep.class);
  }

  @Step
  public LottingSteps changeLot(LotData lotData) throws InterruptedException {
    //    lotEditViewSteps.changeLot(lotData);

    return page(LottingSteps.class);
  }
}
