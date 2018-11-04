package com.estafeta.test.ui.data.vehicles;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class VehicleData {

  private final String vin;
  private final String brand;
  private final String model;
  private final String modelCode;
  private final String place;
  private final String location;
  private final String customStatus;
  private final String blockings;
  private final String erpCode;
  private final String erpCompany;
  private final String erpDate;
  private final String creationDate;

  private VehicleData(Builder builder) {
    this.vin = builder.vin;
    this.brand = builder.brand;
    this.model = builder.model;
    this.modelCode = builder.modelCode;
    this.place = builder.place;
    this.location = builder.location;
    this.customStatus = builder.customStatus;
    this.blockings = builder.blockings;
    this.erpCode = builder.erpCode;
    this.erpCompany = builder.erpCompany;
    this.erpDate = builder.erpDate;
    this.creationDate = builder.creationDate;
  }

  public String getVin() {
    return vin;
  }

  public String getBrand() {
    return brand;
  }

  public String getModel() {
    return model;
  }

  public String getModelCode() {
    return modelCode;
  }

  public String getPlace() {
    return place;
  }

  public String getLocation() {
    return location;
  }

  public String getCustomStatus() {
    return customStatus;
  }

  public String getBlockings() {
    return blockings;
  }

  public String getErpCode() {
    return erpCode;
  }

  public String getErpCompany() {
    return erpCompany;
  }

  public String getErpDate() {
    return erpDate;
  }

  public String getCreationDate() {
    return creationDate;
  }

  @Override
  public String toString() {
    return "VehicleData{"
        + "vin='"
        + vin
        + '\''
        + ", brand='"
        + brand
        + '\''
        + ", model='"
        + model
        + '\''
        + ", modelCode='"
        + modelCode
        + '\''
        + ", place='"
        + place
        + '\''
        + ", location='"
        + location
        + '\''
        + ", customStatus='"
        + customStatus
        + '\''
        + ", blockings='"
        + blockings
        + '\''
        + ", erpCode='"
        + erpCode
        + '\''
        + ", erpCompany='"
        + erpCompany
        + '\''
        + ", erpDate='"
        + erpDate
        + '\''
        + ", creationDate='"
        + creationDate
        + '\''
        + '}';
  }

  public static class Builder {
    private String vin;
    private String brand;
    private String model;
    private String modelCode;
    private String place;
    private String location;
    private String customStatus;
    private String blockings;
    private String erpCode;
    private String erpCompany;
    private String erpDate;
    private String creationDate;

    public Builder vin(String vin) {
      this.vin = vin;
      return this;
    }

    public Builder brand(String brand) {
      this.brand = brand;
      return this;
    }

    public Builder model(String model) {
      this.model = model;
      return this;
    }

    public Builder modelCode(String modelCode) {
      this.modelCode = modelCode;
      return this;
    }

    public Builder place(String place) {
      this.place = place;
      return this;
    }

    public Builder location(String location) {
      this.location = location;
      return this;
    }

    public Builder customStatus(String customStatus) {
      this.customStatus = customStatus;
      return this;
    }

    public Builder blockings(String blockings) {
      this.blockings = blockings;
      return this;
    }

    public Builder erpCode(String erpCode) {
      this.erpCode = erpCode;
      return this;
    }

    public Builder erpCompany(String erpCompany) {
      this.erpCompany = erpCompany;
      return this;
    }

    public Builder erpDate(String erpDate) {
      this.erpDate = erpDate;
      return this;
    }

    public Builder creationDate(String creationDate) {
      this.creationDate = creationDate;
      return this;
    }

    public VehicleData build() {
      return new VehicleData(this);
    }
  }
}
