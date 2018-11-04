package com.estafeta.test.ui.data.brands;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class BrandData {

  private final String name;
  private final String code;
  private final int modelCount;
  private final int modelCodeCount;
  private final String vehicleTypes;

  private BrandData(Builder builder) {
    this.name = builder.name;
    this.code = builder.code;
    this.modelCount = builder.modelCount;
    this.modelCodeCount = builder.modelCodeCount;
    this.vehicleTypes = builder.vehicleTypes;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public int getModelCount() {
    return modelCount;
  }

  public int getModelCodeCount() {
    return modelCodeCount;
  }

  public String getVehicleTypes() {
    return vehicleTypes;
  }

  @Override
  public String toString() {
    return "BrandData{"
        + "name='"
        + name
        + '\''
        + ", code='"
        + code
        + '\''
        + ", modelCount="
        + modelCount
        + ", modelCodeCount="
        + modelCodeCount
        + ", vehicleTypes='"
        + vehicleTypes
        + '\''
        + '}';
  }

  public static class Builder {
    private String name;
    private String code;
    private int modelCount;
    private int modelCodeCount;
    private String vehicleTypes;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public Builder modelCount(int modelCount) {
      this.modelCount = modelCount;
      return this;
    }

    public Builder modelCodeCount(int modelCodeCount) {
      this.modelCodeCount = modelCodeCount;
      return this;
    }

    public Builder vehicleTypes(String vehicleTypes) {
      this.vehicleTypes = vehicleTypes;
      return this;
    }

    public BrandData build() {
      return new BrandData(this);
    }
  }
}
