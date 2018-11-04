package com.estafeta.test.ui.data.RolesAndPermission;

public class Role {
  private final String name;

  private Role(Builder builder) {
    this.name = builder.name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Role{" + "name='" + name + '\'' + '}';
  }

  public static class Builder {
    private String name;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Role build() {
      return new Role(this);
    }
  }
}
