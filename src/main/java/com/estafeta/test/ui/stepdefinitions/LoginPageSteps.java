package com.estafeta.test.ui.stepdefinitions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.estafeta.test.ui.data.users.UserData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class LoginPageSteps {

  private static String LOGIN_PAGE_URL = "/Account/LogOn?ReturnUrl=%2f";

  @Step
  public static LoginPageSteps open() {
    Selenide.open(LOGIN_PAGE_URL);
    return page(LoginPageSteps.class);
  }

  @Step
  public static HomePageSteps loginAs(UserData userData, boolean company) throws InterruptedException {
    open();
    $(By.id("UserName")).setValue(userData.getLogin());
    $(By.id("Password")).setValue(userData.getPassword());
    $(By.xpath("//button[@name='signin']")).click();
    if (company==true)
    selectCompany(userData.getCompany());
    Thread.sleep(2000);
    if ($(By.id("whatsNewView")).isDisplayed()){
//      $(By.xpath("//div[@id='whatsNewView']")).waitUntil(Condition.visible, 20000);
      $(By.xpath("//div[@id='whatsNewView']//button[@type='button']")).click();
      $(By.xpath("//div[@id='whatsNewView']")).waitUntil(Condition.disappear, 20000);
    }
    return page(HomePageSteps.class);
  }

  @Step
  public static HomePageSteps loginAs(String  login, String password) {
    open();
    $(By.id("UserName")).setValue(login);
    $(By.id("Password")).setValue(password);
    $(By.xpath("//button[@name='signin']")).click();
    if ($(By.id("whatsNewView")).isDisplayed()){
      $(By.xpath("//div[@id='whatsNewView']//button[@type='button']")).click();
    }
    return page(HomePageSteps.class);
  }

  @Step
  public static LoginPageSteps selectCompany(String companyName) {
    $(By.xpath("//span[@class='k-widget k-dropdown k-header span12']")).click();
    $$(By.xpath("//ul[@class='k-list k-reset']/li")).findBy(Condition.text(companyName)).click();
    $(By.xpath("//button[@class='k-button btn-primary pull-right']")).click();
    return page(LoginPageSteps.class);
  }

  @Step
  public static LoginPageSteps logOut(){
    $(By.xpath("//ul[@id='userBtn']//i[text()='more_vert']")).click();
    $(By.xpath("//ul[@id='userBtn']//a[@href='/Account/LogOff']"))
        .waitUntil(Condition.visible, 2000)
        .click();

    return page(LoginPageSteps.class);
  }

}
