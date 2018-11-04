package com.estafeta.test.ui.data.warehouse;

public class WarehouseData {

  private final String vin;
  private final String transportOrder;
  private final String warehouse;
  private final String consignee;

  private WarehouseData(Builder builder) {
    this.vin = builder.vin;
    this.transportOrder = builder.transportOrder;
    this.warehouse = builder.warehouse;
    this.consignee = builder.consignee;
  }

  public String getVin() {
    return vin;
  }

  public String getTransportOrder() {
    return transportOrder;
  }

  public String getWarehouse() {
    return warehouse;
  }

  public String getConsignee() {
    return consignee;
  }

  @Override
  public String toString() {
    return "WarehouseData{"
        + "vin='"
        + vin
        + '\''
        + ", transportOrder='"
        + transportOrder
        + '\''
        + ", warehouse='"
        + warehouse
        + '\''
        + ", consignee='"
        + consignee
        + '\''
        + '}';
  }

  public static class Builder {
    private String vin;
    private String transportOrder;
    private String warehouse;
    private String consignee;

    public Builder vin(String vin) {
      this.vin = vin;
      return this;
    }

    public Builder transportOrder(String transportOrder) {
      this.transportOrder = transportOrder;
      return this;
    }

    public Builder warehouse(String warehouse) {
      this.warehouse = warehouse;
      return this;
    }

    public Builder consignee(String consignee) {
      this.consignee = consignee;
      return this;
    }

    public WarehouseData build() {
      return new WarehouseData(this);
    }
  }
}
