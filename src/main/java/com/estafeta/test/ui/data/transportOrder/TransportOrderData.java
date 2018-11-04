package com.estafeta.test.ui.data.transportOrder;

/**
 * @author dnikiforov
 * @project amt
 */
public class TransportOrderData {

  private final int id;
  private final String number;
  private final String type;
  private final String vin;
  private final String brand;
  private final String modelCodeGroup;
  private final String priority;
  private final String client;
  private final String currentWarehouse;
  private final String consignor;
  private final String consignee;
  private final String dealerCode;
  private final String transportOrderDate;
  private final String transportOrdeTime;
  private final String arrivalDate;
  private final String loadingDate;
  private final int dispatchDeadline;
  private final String vehicleCondition;
  private final String status;
  private final int daysCount;
  private final String source;
  private final String customDeclaration;
  private final String transportOrderModificationDate;
  private final String statusOfBlocking;
  private final boolean isPaid;
  private final String blocking;

  private TransportOrderData(Builder builder) {
    this.id = builder.id;
    this.number = builder.number;
    this.type = builder.type;
    this.vin = builder.vin;
    this.brand = builder.brand;
    this.modelCodeGroup = builder.modelCodeGroup;
    this.priority = builder.priority;
    this.client = builder.client;
    this.currentWarehouse = builder.currentWarehouse;
    this.consignor = builder.consignor;
    this.consignee = builder.consignee;
    this.dealerCode = builder.dealerCode;
    this.transportOrderDate = builder.transportOrderDate;
    this.transportOrdeTime = builder.transportOrdeTime;
    this.arrivalDate = builder.arrivalDate;
    this.loadingDate = builder.loadingDate;
    this.dispatchDeadline = builder.dispatchDeadline;
    this.vehicleCondition = builder.vehicleCondition;
    this.status = builder.status;
    this.daysCount = builder.daysCount;
    this.source = builder.source;
    this.customDeclaration = builder.customDeclaration;
    this.transportOrderModificationDate = builder.transportOrderModificationDate;
    this.statusOfBlocking = builder.statusOfBlocking;
    this.isPaid = builder.isPaid;
    this.blocking = builder.blocking;
  }

  public int getId() {
    return id;
  }

  public String getNumber() {
    return number;
  }

  public String getType() {
    return type;
  }

  public String getVin() {
    return vin;
  }

  public String getBrand() {
    return brand;
  }

  public String getModelCodeGroup() {
    return modelCodeGroup;
  }

  public String getPriority() {
    return priority;
  }

  public String getClient() {
    return client;
  }

  public String getCurrentWarehouse() {
    return currentWarehouse;
  }

  public String getConsignor() {
    return consignor;
  }

  public String getConsignee() {
    return consignee;
  }

  public String getDealerCode() {
    return dealerCode;
  }

  public String getTransportOrderDate() {
    return transportOrderDate;
  }

  public String getTransportOrdeTime() {
    return transportOrdeTime;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public String getLoadingDate() {
    return loadingDate;
  }

  public int getDispatchDeadline() {
    return dispatchDeadline;
  }

  public String getVehicleCondition() {
    return vehicleCondition;
  }

  public String getStatus() {
    return status;
  }

  public int getDaysCount() {
    return daysCount;
  }

  public String getSource() {
    return source;
  }

  public String getCustomDeclaration() {
    return customDeclaration;
  }

  public String getTransportOrderModificationDate() {
    return transportOrderModificationDate;
  }

  public String getStatusOfBlocking() {
    return statusOfBlocking;
  }

  public boolean isPaid() {
    return isPaid;
  }

  public String getBlocking() {
    return blocking;
  }

  @Override
  public String toString() {
    return "TransportOrderData{"
        + "id="
        + id
        + ", number='"
        + number
        + '\''
        + ", type='"
        + type
        + '\''
        + ", vin='"
        + vin
        + '\''
        + ", brand='"
        + brand
        + '\''
        + ", modelCodeGroup='"
        + modelCodeGroup
        + '\''
        + ", priority='"
        + priority
        + '\''
        + ", client='"
        + client
        + '\''
        + ", currentWarehouse='"
        + currentWarehouse
        + '\''
        + ", consignor='"
        + consignor
        + '\''
        + ", consignee='"
        + consignee
        + '\''
        + ", dealerCode='"
        + dealerCode
        + '\''
        + ", transportOrderDate='"
        + transportOrderDate
        + '\''
        + ", transportOrdeTime='"
        + transportOrdeTime
        + '\''
        + ", arrivalDate='"
        + arrivalDate
        + '\''
        + ", loadingDate='"
        + loadingDate
        + '\''
        + ", dispatchDeadline="
        + dispatchDeadline
        + ", vehicleCondition='"
        + vehicleCondition
        + '\''
        + ", status='"
        + status
        + '\''
        + ", daysCount="
        + daysCount
        + ", source='"
        + source
        + '\''
        + ", customDeclaration='"
        + customDeclaration
        + '\''
        + ", transportOrderModificationDate='"
        + transportOrderModificationDate
        + '\''
        + ", statusOfBlocking='"
        + statusOfBlocking
        + '\''
        + ", isPaid="
        + isPaid
        + ", blocking='"
        + blocking
        + '\''
        + '}';
  }

  public static class Builder {
    private int id;
    private String number;
    private String type;
    private String vin;
    private String brand;
    private String modelCodeGroup;
    private String priority;
    private String client;
    private String currentWarehouse;
    private String consignor;
    private String consignee;
    private String dealerCode;
    private String transportOrderDate;
    private String transportOrdeTime;
    private String arrivalDate;
    private String loadingDate;
    private int dispatchDeadline;
    private String vehicleCondition;
    private String status;
    private int daysCount;
    private String source;
    private String customDeclaration;
    private String transportOrderModificationDate;
    private String statusOfBlocking;
    private boolean isPaid;
    private String blocking;

    public Builder id(int id) {
      this.id = id;
      return this;
    }

    public Builder number(String number) {
      this.number = number;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder vin(String vin) {
      this.vin = vin;
      return this;
    }

    public Builder brand(String brand) {
      this.brand = brand;
      return this;
    }

    public Builder modelCodeGroup(String modelCodeGroup) {
      this.modelCodeGroup = modelCodeGroup;
      return this;
    }

    public Builder priority(String priority) {
      this.priority = priority;
      return this;
    }

    public Builder client(String client) {
      this.client = client;
      return this;
    }

    public Builder currentWarehouse(String currentWarehouse) {
      this.currentWarehouse = currentWarehouse;
      return this;
    }

    public Builder consignor(String consignor) {
      this.consignor = consignor;
      return this;
    }

    public Builder consignee(String consignee) {
      this.consignee = consignee;
      return this;
    }

    public Builder dealerCode(String dealerCode) {
      this.dealerCode = dealerCode;
      return this;
    }

    public Builder transportOrderDate(String transportOrderDate) {
      this.transportOrderDate = transportOrderDate;
      return this;
    }

    public Builder transportOrdeTime(String currentWarehouse) {
      this.currentWarehouse = currentWarehouse;
      return this;
    }

    public Builder arrivalDate(String arrivalDate) {
      this.arrivalDate = arrivalDate;
      return this;
    }

    public Builder loadingDate(String loadingDate) {
      this.loadingDate = loadingDate;
      return this;
    }

    public Builder dispatchDeadline(int dispatchDeadline) {
      this.dispatchDeadline = dispatchDeadline;
      return this;
    }

    public Builder vehicleCondition(String vehicleCondition) {
      this.vehicleCondition = vehicleCondition;
      return this;
    }

    public Builder status(String status) {
      this.status = status;
      return this;
    }

    public Builder daysCount(int daysCount) {
      this.daysCount = daysCount;
      return this;
    }

    public Builder source(String source) {
      this.source = source;
      return this;
    }

    public Builder customDeclaration(String customDeclaration) {
      this.customDeclaration = customDeclaration;
      return this;
    }

    public Builder transportOrderModificationDate(String transportOrderModificationDate) {
      this.transportOrderModificationDate = transportOrderModificationDate;
      return this;
    }

    public Builder statusOfBlocking(String statusOfBlocking) {
      this.statusOfBlocking = statusOfBlocking;
      return this;
    }

    public Builder isPaid(boolean isPaid) {
      this.isPaid = isPaid;
      return this;
    }

    public Builder blocking(String blocking) {
      this.blocking = blocking;
      return this;
    }

    public TransportOrderData build() {
      return new TransportOrderData(this);
    }
  }
}
