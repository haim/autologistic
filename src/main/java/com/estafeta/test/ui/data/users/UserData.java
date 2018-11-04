package com.estafeta.test.ui.data.users;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class UserData {
  private String lastName;
  private String firstName;
  private String secondName;
  private String email;
  private List<String> phoneNumbers = new ArrayList<>();
  private String englishLastName;
  private String englishFirstName;
  private String englishSecondName;
  private String language;
  private String login;
  private String password;
  private List<String> brands = new ArrayList<>();
  private String company;
  private boolean rolesActive;
  private UserRole userRoles;
  private SystemSettings systemSettings;

  private UserData(Builder builder) {
    this.lastName = builder.lastName;
    this.firstName = builder.firstName;
    this.secondName = builder.secondName;
    this.email = builder.email;
    this.phoneNumbers = builder.phoneNumbers;
    this.englishLastName = builder.englishLastName;
    this.englishFirstName = builder.englishFirstName;
    this.englishSecondName = builder.englishSecondName;
    this.language = builder.language;
    this.login = builder.login;
    this.password = builder.password;
    this.brands = builder.brands;
    this.company = builder.company;
    this.rolesActive = builder.rolesActive;
    this.userRoles = builder.userRoles;
    this.systemSettings = builder.systemSettings;
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

  public List<String> getPhoneNumbers() {
    return phoneNumbers;
  }

  public String getEnglishLastName() {
    return englishLastName;
  }

  public String getEnglishFirstName() {
    return englishFirstName;
  }

  public String getEnglishSecondName() {
    return englishSecondName;
  }

  public String getLanguage() {
    return language;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public List<String> getBrands() {
    return brands;
  }

  public String getCompany() {
    return company;
  }

  public boolean isRolesActive() {
    return rolesActive;
  }

  public UserRole getUserRoles() {
    return userRoles;
  }

  public SystemSettings getSystemSettings() {
    return systemSettings;
  }

  @Override
  public String toString() {
    return "UserData{"
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
        + ", phoneNumbers="
        + phoneNumbers
        + ", englishLastName='"
        + englishLastName
        + '\''
        + ", englishFirstName='"
        + englishFirstName
        + '\''
        + ", englishSecondName='"
        + englishSecondName
        + '\''
        + ", language='"
        + language
        + '\''
        + ", loginAs='"
        + login
        + '\''
        + ", password='"
        + password
        + '\''
        + ", brands="
        + brands
        + ", company='"
        + company
        + '\''
        + ", rolesActive="
        + rolesActive
        + ", userRoles="
        + userRoles
        + ", systemSettings="
        + systemSettings
        + '}';
  }

  public static class Builder {
    private String lastName;
    private String firstName;
    private String secondName;
    private String email;
    private List<String> phoneNumbers = new ArrayList<>();
    private String englishLastName;
    private String englishFirstName;
    private String englishSecondName;
    private String language;
    private String login;
    private String password;
    private List<String> brands = new ArrayList<>();
    private String company;
    private boolean rolesActive;
    private UserRole userRoles;
    private SystemSettings systemSettings;

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

    public Builder phoneNumbers(List<String> phoneNumbers) {
      this.phoneNumbers = phoneNumbers;
      return this;
    }

    public Builder englishLastName(String englishLastName) {
      this.englishLastName = englishLastName;
      return this;
    }

    public Builder englishFirstName(String englishFirstName) {
      this.englishFirstName = englishFirstName;
      return this;
    }

    public Builder englishSecondName(String englishSecondName) {
      this.englishSecondName = englishSecondName;
      return this;
    }

    public Builder language(String language) {
      this.language = language;
      return this;
    }

    public Builder login(String login) {
      this.login = login;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder brands(List<String> brands) {
      this.brands = brands;
      return this;
    }

    public Builder company(String company) {
      this.company = company;
      return this;
    }

    public Builder rolesActive(boolean rolesActive) {
      this.rolesActive = rolesActive;
      return this;
    }

    public Builder userRoles(UserRole userRoles) {
      this.userRoles = userRoles;
      return this;
    }

    public Builder systemSettings(SystemSettings systemSettings) {
      this.systemSettings = systemSettings;
      return this;
    }

    public UserData build() {
      return new UserData(this);
    }
  }
}
