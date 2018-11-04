package com.estafeta.test.ui.data.transports.drivers;

import com.estafeta.test.ui.data.brands.BrandData;
import com.estafeta.test.ui.data.companies.CompanyData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class  DriverData {

  private final String lastName;
  private final String firstName;
  private final String secondName;
  private final String email;
  private final String englishLastName;
  private final String englishFristName;
  private final String englishSecondName;
  private final Boolean isActive;
  private final String login;
  private final String password;
  private List<String> language = new ArrayList<>();
  private List<String> phoneNumbers = new ArrayList<>();
  private List<BrandData> brands = new ArrayList<>();
  private List<CompanyData> systemCompany = new ArrayList<>();

  private DriverData(Builder builder) {
    this.lastName = builder.lastName;
    this.firstName = builder.firstName;
    this.secondName = builder.secondName;
    this.email = builder.email;
    this.englishLastName = builder.englishLastName;
    this.englishFristName = builder.englishFristName;
    this.englishSecondName = builder.englishSecondName;
    this.language = builder.language;
    this.isActive = builder.isActive;
    this.phoneNumbers = builder.phoneNumbers;
    this.login = builder.login;
    this.password = builder.password;
    this.brands = builder.brands;
    this.systemCompany = builder.systemCompany;
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

  public List<String> getLanguage() {
    return language;
  }

  public Boolean getActive() {
    return isActive;
  }

  public List<String> getPhoneNumbers() {
    return phoneNumbers;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public List<BrandData> getBrands() {
    return brands;
  }

  public List<CompanyData> getSystemCompany() {
    return systemCompany;
  }

  @Override
  public String toString() {
    return "DriverData{"
        + "lastName='"
        + lastName
        + '\''
        + ", firstName='"
        + firstName
        + '\''
        + ", secondName='"
        + secondName
        + '\''
        + ", email='"
        + email
        + '\''
        + ", englishLastName='"
        + englishLastName
        + '\''
        + ", englishFristName='"
        + englishFristName
        + '\''
        + ", englishSecondName='"
        + englishSecondName
        + '\''
        + ", language='"
        + language
        + '\''
        + ", isActive="
        + isActive
        + '}';
  }

  public static class Builder {
    private String lastName;
    private String firstName;
    private String secondName;
    private String email;
    private String englishLastName;
    private String englishFristName;
    private String englishSecondName;
    private List<String> language = new ArrayList<>();
    private Boolean isActive;
    private List<String> phoneNumbers = new ArrayList<>();
    private String login;
    private String password;
    private List<BrandData> brands = new ArrayList<>();
    private List<CompanyData> systemCompany = new ArrayList<>();

    public Builder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder secondName(String secondName) {
      this.secondName = secondName;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder englishLastName(String englishLastName) {
      this.englishLastName = englishLastName;
      return this;
    }

    public Builder englishFristName(String englishFristName) {
      this.englishFristName = englishFristName;
      return this;
    }

    public Builder englishSecondName(String englishSecondName) {
      this.englishSecondName = englishSecondName;
      return this;
    }

    public Builder language(List<String> language) {
      this.language = language;
      return this;
    }

    public Builder isActive(Boolean isActive) {
      this.isActive = isActive;
      return this;
    }

    public DriverData build() {
      return new DriverData(this);
    }
  }
}
