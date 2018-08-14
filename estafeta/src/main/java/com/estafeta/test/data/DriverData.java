package com.estafeta.test.data;

public class DriverData {



    private final String lastName;
    private final String firstName;
    private final String secondName;
    private final String email;
    private final String englishLastName;
    private final String englishFristName;
    private final String englishSecondName;
    private final String language;

    public DriverData(String lastName, String firstName, String secondName, String email, String englishLastName, String englishFristName, String englishSecondName, String language) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.englishLastName = englishLastName;
        this.englishFristName = englishFristName;
        this.englishSecondName = englishSecondName;
        this.language = language;
    }




    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getEnglishLastName() {
        return englishLastName;
    }

    public String getEnglishFristName() {
        return englishFristName;
    }

    public String getEnglishSecondName() {
        return englishSecondName;
    }

    public String getLanguage() {
        return language;
    }



    @Override
    public String toString() {
        return "DriverData{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", englishLastName='" + englishLastName + '\'' +
                ", englishFristName='" + englishFristName + '\'' +
                ", englishSecondName='" + englishSecondName + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

}
