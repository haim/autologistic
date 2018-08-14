package com.estafeta.test.stepdefinitions;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPageSteps {

    public HomePageSteps login(String user, String password){
        $(By.id("UserName")).shouldBe(Condition.visible).setValue(user);
        $(By.id("Password")).shouldBe(Condition.visible).setValue(password);
        $(By.xpath("//button[@name='signin']")).shouldBe(Condition.visible).click();

        return page(HomePageSteps.class);
    }
}
