package com.estafeta.test.ui.data.users;

public class SystemSettings {
  private final boolean Verified;
  private final boolean accessOnlyOwnTasks;
  private final String systemСompany;

  private SystemSettings(Builder builder) {
    this.Verified = builder.Verified;
    this.accessOnlyOwnTasks = builder.accessOnlyOwnTasks;
    this.systemСompany = builder.systemСompany;
  }

  public boolean isVerified() {
    return Verified;
  }

  public boolean isAccessOnlyOwnTasks() {
    return accessOnlyOwnTasks;
  }

  public String getSystemСompany() {
    return systemСompany;
  }

  @Override
  public String toString() {
    return "SystemSettings{"
        + "Verified="
        + Verified
        + ", accessOnlyOwnTasks="
        + accessOnlyOwnTasks
        + ", systemСompany='"
        + systemСompany
        + '\''
        + '}';
  }

  public static class Builder {
    private boolean Verified;
    private boolean accessOnlyOwnTasks;
    private String systemСompany;

    public Builder Verified(boolean Verified) {
      this.Verified = Verified;
      return this;
    }

    public Builder accessOnlyOwnTasks(boolean accessOnlyOwnTasks) {
      this.accessOnlyOwnTasks = accessOnlyOwnTasks;
      return this;
    }

    public Builder systemСompany(String systemСompany) {
      this.systemСompany = systemСompany;
      return this;
    }

    public SystemSettings build() {
      return new SystemSettings(this);
    }
  }
}
