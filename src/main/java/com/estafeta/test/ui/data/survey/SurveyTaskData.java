package com.estafeta.test.ui.data.survey;

import com.codeborne.selenide.SelenideElement;

public class SurveyTaskData {
    private final String number;
    private final String link;

    private SurveyTaskData(Builder builder){
        this.link = builder.link;
        this.number = builder.number;
    }

    public String getNumber() {
        return number;
    }

    public String getLink() {
        return link;
    }

    public static class Builder{
        private String number;
        private String link;

        public Builder number(String number){
            this.number = number;
            return this;
        }
        public Builder link(String link){
            this.link = link;
            return this;
        }
        public SurveyTaskData build(){
            return new SurveyTaskData(this);
        }
    }

    @Override
    public String toString() {
        return "SurveyTaskData{" +
                "number='" + number + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
