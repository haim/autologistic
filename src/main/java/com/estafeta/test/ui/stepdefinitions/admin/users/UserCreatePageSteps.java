package com.estafeta.test.ui.stepdefinitions.admin.users;

import com.codeborne.selenide.Condition;
import com.estafeta.test.ui.data.users.UserData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class UserCreatePageSteps {

  UserRolesBlockSteps userRolesBlockSteps = new UserRolesBlockSteps();

  @Step
  public UserCreatePageSteps fillForms(UserData userData) {
    $x("//input[@name='LastName']").setValue(userData.getLastName());
    $x("//input[@name='FirstName']").setValue(userData.getFirstName());
    $x("//input[@name='EnglishFirstName']").setValue(userData.getEnglishFirstName());
    $x("//input[@name='EnglishLastName']").setValue(userData.getEnglishLastName());
    $x("//input[@name='EnglishSecondName']").setValue(userData.getEnglishSecondName());
    addPhoneNumber(userData.getPhoneNumbers().get(0));
    $x("//select[@id='CultureId']").click();
    $x("//select[@id='CultureId']").selectOptionContainingText(userData.getLanguage());
    $(By.id("Login")).setValue(userData.getLogin());
    $(By.id("Password")).setValue(userData.getPassword());
    if (userData.isRolesActive() == true) {
      $x("//input[@id='IsActive']").setSelected(userData.isRolesActive());
    }
    $(By.id("addRolesButton")).click();
    userRolesBlockSteps.fillFields(userData.getUserRoles());
    $(By.id("editRolesView")).waitUntil(Condition.disappear, 20000);
    filldFormSettingsTab(
        userData.getSystemSettings().isVerified(),
        userData.getSystemSettings().isAccessOnlyOwnTasks(),
        userData.getSystemSettings().getSystemСompany());
    $(By.id("saveButton")).click();
    return page(UserCreatePageSteps.class);
  }

  @Step
  public UserCreatePageSteps filldFormSettingsTab(
      boolean verified, boolean accessOnlyOwnTasks, String systemCompany) {
    $x("//a[@href='#systemSettingsTabContent']").shouldBe(Condition.visible).click();
    $(By.id("IsVerified")).setSelected(verified);
    $(By.id("IsAccessOnlyOwnTasks")).setSelected(accessOnlyOwnTasks);
    $x("//input[@name='UserCompanyId_input']").setValue(systemCompany);
    selectFromDropdown(systemCompany);
    return page(UserCreatePageSteps.class);
  }

  @Step
  public UserCreatePageSteps addPhoneNumber(String phoneNumber) {
    $(By.xpath("//a[@onclick='userEdit.AddPhoneNumber()']")).click();
    $(By.id("PhoneNumbersBlock")).$(By.name("PhoneNumber")).clear();
    $(By.id("PhoneNumbersBlock")).$(By.name("PhoneNumber")).setValue(phoneNumber);
    return page(UserCreatePageSteps.class);
  }

  private void selectFromDropdown(String text) {
    $$(By.xpath("//ul[@id='UserCompanyId_listbox']//div/span"))
        .findBy(Condition.text(text))
        .click();
  }
}
