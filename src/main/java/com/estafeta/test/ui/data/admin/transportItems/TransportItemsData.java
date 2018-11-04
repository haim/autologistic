package com.estafeta.test.ui.data.admin.transportItems;

public class TransportItemsData {

  private final boolean active;
  private final boolean verified;
  private final String type;
  private final String number;
  private final String carrier;
  private final boolean trailer;
  private final String transportItemPartType;
  private final String brand;
  private final String model;
  private final String description;
  private final String vin;

  private TransportItemsData(Builder builder) {
    this.active = builder.active;
    this.verified = builder.verified;
    this.type = builder.type;
    this.number = builder.number;
    this.carrier = builder.carrier;
    this.trailer = builder.trailer;
    this.transportItemPartType = builder.transportItemPartType;
    this.brand = builder.brand;
    this.model = builder.model;
    this.description = builder.description;
    this.vin = builder.vin;
  }

  public boolean isActive() {
    return active;
  }

  public boolean isVerified() {
    return verified;
  }

  public String getType() {
    return type;
  }

  public String getNumber() {
    return number;
  }

  public String getCarrier() {
    return carrier;
  }

  public boolean isTrailer() {
    return trailer;
  }

  public String getTransportItemPartType() {
    return transportItemPartType;
  }

  public String getBrand() {
    return brand;
  }

  public String getModel() {
    return model;
  }

  public String getDescription() {
    return description;
  }

  public String getVin() {
    return vin;
  }

  @Override
  public String toString() {
    return "TransportItemsData{"
        + "active="
        + active
        + ", verified="
        + verified
        + ", type='"
        + type
        + '\''
        + ", number='"
        + number
        + '\''
        + ", carrier='"
        + carrier
        + '\''
        + ", trailer="
        + trailer
        + ", transportItemPartType='"
        + transportItemPartType
        + '\''
        + ", brand='"
        + brand
        + '\''
        + ", model='"
        + model
        + '\''
        + ", description='"
        + description
        + '\''
        + ", vin='"
        + vin
        + '\''
        + '}';
  }

  public static class Builder {
    private boolean active;
    private boolean verified;
    private String type;
    private String number;
    private String carrier;
    private boolean trailer;
    private String transportItemPartType;
    private String brand;
    private String model;
    private String description;
    private String vin;

    public Builder active(boolean active) {
      this.active = active;
      return this;
    }

    public Builder verified(boolean verified) {
      this.verified = verified;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder number(String number) {
      this.number = number;
      return this;
    }

    public Builder carrier(String carrier) {
      this.carrier = carrier;
      return this;
    }

    public Builder trailer(boolean trailer) {
      this.trailer = trailer;
      return this;
    }

    public Builder transportItemPartType(String transportItemPartType) {
      this.transportItemPartType = transportItemPartType;
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

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder vin(String vin) {
      this.vin = vin;
      return this;
    }

    public TransportItemsData build() {
      return new TransportItemsData(this);
    }
  }
}
