package com.estafeta.test.ui.stepdefinitions.survey;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SurveyTasksProfilePageSteps {

    @Step
    public SurveyTasksProfilePageSteps startExecuting(){
        $(By.id("startButton")).click();
        return page(SurveyTasksProfilePageSteps.class);
    }

    @Step
    public SurveyTasksProfilePageSteps resumeExecuting(){
        $(By.id("continueExecutingButton")).click();
        return page(SurveyTasksProfilePageSteps.class);
    }


}
