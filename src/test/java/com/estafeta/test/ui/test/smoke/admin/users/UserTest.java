package com.estafeta.test.ui.test.smoke.admin.users;

import com.codeborne.selenide.Condition;
import com.estafeta.test.ui.data.users.UserData;
import com.estafeta.test.ui.helper.BaseTest;
import com.estafeta.test.ui.preconditions.LoginRequired;
import com.estafeta.test.ui.stepdefinitions.admin.users.UserCreatePageSteps;
import com.estafeta.test.ui.stepdefinitions.admin.users.UsersPageSteps;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static com.estafeta.test.ui.stepdefinitions.LoginPageSteps.logOut;
import static com.estafeta.test.ui.stepdefinitions.LoginPageSteps.loginAs;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UserTest extends BaseTest {

  @Test(dataProvider = "new_driver", dataProviderClass = UserDataProvider.class)
  @LoginRequired
  public void new_driver(UserData driver, UserData warehouseSurveyor) throws InterruptedException {

//    logOut();
//    loginAs(warehouseSurveyor, false);
//    assertThat(homePageSteps.getWelcomeMessage()).isEqualTo("Welcome to Estafeta Automotive");

    UsersPageSteps usersPageSteps = new UsersPageSteps();
    usersPageSteps.open().initCreateNewUser();
    UserCreatePageSteps userCreatePageSteps = new UserCreatePageSteps();
    userCreatePageSteps.fillForms(driver);
    usersPageSteps.searchUser(driver.getLogin());
    assertThat(usersPageSteps.getUserDataFromGrid().get(0).getLogin()).isEqualTo(driver.getLogin());
    logOut();
    loginAs(driver, false);
    refresh();
    refresh();
//    $(By.id("whatsNewView")).waitUntil(Condition.visible, 2000);
    logOut();
    loginAs(driver, false);
    System.out.println("");
  }
}
