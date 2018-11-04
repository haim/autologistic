package com.estafeta.test.ui.test.smoke.login;

import com.estafeta.test.ui.data.users.UserData;
import com.estafeta.test.ui.helper.BaseTest;
import com.estafeta.test.ui.stepdefinitions.HomePageSteps;
import com.estafeta.test.ui.stepdefinitions.LoginPageSteps;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static com.estafeta.test.ui.stepdefinitions.LoginPageSteps.loginAs;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class LoginTest extends BaseTest {

  @Description("User can loginAs as Admin")
  @Test(dataProvider = "Login as Admin", dataProviderClass = LoginDataProvider.class)
  public void login_test(UserData admin) throws InterruptedException {
    homePageSteps = new LoginPageSteps().open().loginAs(admin,true);
    assertThat(homePageSteps.getWelcomeMessage()).isEqualTo("Welcome to Estafeta Automotive");
  }

  @Test(dataProvider = "Login as Admin", dataProviderClass = LoginDataProvider.class)
  public void login_test2(UserData admin) throws InterruptedException {
    loginAs(admin,true);
    assertThat(new HomePageSteps().getWelcomeMessage()).isEqualTo("Welcome to Estafeta Automotive");
  }
}
