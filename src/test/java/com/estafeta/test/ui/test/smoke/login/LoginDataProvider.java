package com.estafeta.test.ui.test.smoke.login;

import com.estafeta.test.ui.data.users.UserData;
import org.testng.annotations.DataProvider;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class LoginDataProvider {

  @DataProvider(name = "Login as Admin")
  public static Object[][] loginAsAdmin() {
    UserData admin =
        new UserData.Builder()
            .login("estafeta")
            .password("1")
            .company("Communication Company Estafeta")
            .build();
    return new Object[][] {{admin}};
  }
}
