package com.estafeta.test.ui.data.users;

import com.estafeta.test.ui.data.RolesAndPermission.Role;

import java.util.ArrayList;
import java.util.List;

public class UserRole {

  private final String company;
  private final String activityType;
  private List<Role> listOfRoles = new ArrayList<>();

  private UserRole(Builder builder) {
    this.company = builder.company;
    this.listOfRoles = builder.roleName;
    this.activityType = builder.activityType;
  }

  public String getCompany() {
    return company;
  }

  public List<Role> getListOfRoles() {
    return listOfRoles;
  }

  public String getActivityType() {
    return activityType;
  }

  @Override
  public String toString() {
    return "UserRole{"
        + "company='"
        + company
        + '\''
        + ", listOfRoles="
        + listOfRoles
        + ", activityType='"
        + activityType
        + '\''
        + '}';
  }

  public static class Builder {
    private String company;
    private List<Role> roleName = new ArrayList<>();
    private String activityType;

    public Builder company(String company) {
      this.company = company;
      return this;
    }

    public Builder roleName(List<Role> roleName) {
      this.roleName = roleName;
      return this;
    }

    public Builder activityType(String activityType) {
      this.activityType = activityType;
      return this;
    }

    public UserRole build() {
      return new UserRole(this);
    }
  }
}
