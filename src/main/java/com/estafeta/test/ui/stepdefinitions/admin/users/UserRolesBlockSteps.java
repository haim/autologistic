package com.estafeta.test.ui.stepdefinitions.admin.users;

import com.codeborne.selenide.Condition;
import com.estafeta.test.ui.data.RolesAndPermission.Role;
import com.estafeta.test.ui.data.users.UserRole;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class UserRolesBlockSteps {

  @Step
  public UserCreatePageSteps fillFields(UserRole role) {
    $(By.id("editRolesView")).shouldBe(Condition.visible);
    $(By.name("CompanyId_input"))
        .setValue(role.getCompany())
        .$(By.xpath("//ul[@id='CompanyId_listbox']/li/div/span"))
        .waitUntil(Condition.visible, 20000)
        .$$(By.xpath("//ul[@id='CompanyId_listbox']/li/div/span"))
        .findBy(Condition.text(role.getCompany()))
        .click();
    selectRole(role.getListOfRoles());
    $(By.id("editButton")).click();
    return page(UserCreatePageSteps.class);
  }

  @Step
  public UserCreatePageSteps selectRole(List<Role> roleName) {
    $x("//form[@id='userRolesEditForm']//button[@class='ui-multiselect span12']").click();
    for (Role name : roleName) {
      $x("//div[@class='ui-multiselect-filter']/input")
          .setValue(name.getName()); // find role by search
      $$(By.xpath(
              "//div[@class='ui-multiselect-menu k-popup'][2]//ul[@class='ui-multiselect-checkboxes ui-helper-reset']/li/label/span"))
          .findBy(Condition.exactText(name.getName()))
          .click();
    }

    $x("//form[@id='userRolesEditForm']").click();
    return page(UserCreatePageSteps.class);
  }
}
