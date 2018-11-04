package com.estafeta.test.ui.stepdefinitions.admin.users;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.estafeta.test.ui.data.users.UserData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class UsersPageSteps {
  static UserCreatePageSteps userCreatePageSteps = new UserCreatePageSteps();

  @Step
  public static UsersPageSteps createUser(UserData userData) {
    open();
    initCreateNewUser();
    userCreatePageSteps.fillForms(userData);
    return page(UsersPageSteps.class);
  }

  @Step
  public static UsersPageSteps open() {
    Selenide.open("/Admin/Users/List");
    return page(UsersPageSteps.class);
  }

  @Step
  public static UserCreatePageSteps initCreateNewUser() {
    $x("//a[@href='/Admin/Users/Create']").click();
    return page(UserCreatePageSteps.class);
  }

  @Step
  public UsersPageSteps searchUser(String userData) {
    $(By.id("searchText")).setValue(userData);
    $x("//i[@class='icon-search']").click();
    $(By.id("loadingOverlay")).waitUntil(Condition.disappear, 20000);
    return page(UsersPageSteps.class);
  }

  @Step
  public List<UserData> getUserDataFromGrid() {

    List<UserData> userDataList = new ArrayList<>();
    SelenideElement table = $(By.xpath("//div[@class='k-grid-content']/table[@role='grid']/tbody"));
    ElementsCollection rows = table.$$(By.xpath("tr"));

    for (int row = 0; row < rows.size(); row++) {
      String lastName = rows.get(row).$$(By.xpath("td")).get(0).text();
      String firstName = rows.get(row).$$(By.xpath("td")).get(1).text();
      String secondName = rows.get(row).$$(By.xpath("td")).get(2).text();
      String englishLastName = rows.get(row).$$(By.xpath("td")).get(3).text();
      String englishFirstName = rows.get(row).$$(By.xpath("td")).get(4).text();
      String englishSecondName = rows.get(row).$$(By.xpath("td")).get(5).text();
      String login = rows.get(row).$$(By.xpath("td")).get(6).text();
      String userRoles = rows.get(row).$$(By.xpath("td")).get(11).text();
      List<String> roles = new ArrayList<>();
      roles.add(userRoles);

      userDataList.add(
          new UserData.Builder()
              .lastName(lastName)
              .firstName(firstName)
              .secondName(secondName)
              .englishLastName(englishLastName)
              .englishFirstName(englishFirstName)
              .englishSecondName(englishSecondName)
              .login(login)
              .build());
    }
    return userDataList;
  }
}
