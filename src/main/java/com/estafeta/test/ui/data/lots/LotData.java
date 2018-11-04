package com.estafeta.test.ui.data.lots;

import com.estafeta.test.ui.data.vehicles.VehicleData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class LotData {

  private String lotNumber;
  private String state;
  private String loadingPoint;
  private String loadingDateDate;
  private String loadingDateTime;
  private String loadingDeadline;
  private List<VehicleData> vehicleData = new ArrayList<>();
  private String localities;
  private String truckType;
  private String truck;
  private String comment;
  private String complete;
  private String from;
  private String to;
  private String toDate;
  private String toTime;
  private String shippingAgent;
  private String client;
  private String xmlForImport;
  private String warrantyType;
  private List<BorderCrossingData> borderCrossingData = new ArrayList<>();

  private LotData(Builder builder) {
    this.lotNumber = builder.lotNumber;
    this.vehicleData = builder.vehicleData;
    this.state = builder.state;
    this.loadingPoint = builder.loadingPoint;
    this.loadingDateDate = builder.loadingDateDate;
    this.loadingDateTime = builder.loadingDateTime;
    this.loadingDeadline = builder.loadingDeadline;
    this.localities = builder.localities;
    this.truckType = builder.truckType;
    this.truck = builder.truck;
    this.comment = builder.comment;
    this.complete = builder.complete;
    this.from = builder.from;
    this.to = builder.to;
    this.toDate = builder.toDate;
    this.toTime = builder.toTime;
    this.shippingAgent = builder.shippingAgent;
    this.client = builder.client;
    this.xmlForImport = builder.xmlForImport;
    this.warrantyType = builder.warrantyType;
    this.borderCrossingData = builder.borderCrossingData;
  }

  public String getLotNumber() {
    return lotNumber;
  }

  public String getState() {
    return state;
  }

  public String getLoadingPoint() {
    return loadingPoint;
  }

  public String getLoadingDateDate() {
    return loadingDateDate;
  }

  public String getLoadingDateTime() {
    return loadingDateTime;
  }

  public String getLoadingDeadline() {
    return loadingDeadline;
  }

  public List<VehicleData> getVehicleData() {
    return vehicleData;
  }

  public String getLocalities() {
    return localities;
  }

  public String getTruckType() {
    return truckType;
  }

  public String getTruck() {
    return truck;
  }

  public String getComment() {
    return comment;
  }

  public String getComplete() {
    return complete;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public String getToDate() {
    return toDate;
  }

  public String getToTime() {
    return toTime;
  }

  public String getShippingAgent() {
    return shippingAgent;
  }

  public String getClient() {
    return client;
  }

  public String getXmlForImport() {
    return xmlForImport;
  }

  public String getWarrantyType() {
    return warrantyType;
  }

  public List<BorderCrossingData> getBorderCrossingData() {
    return borderCrossingData;
  }

  @Override
  public String toString() {
    return "LotData{"
        + "lotNumber='"
        + lotNumber
        + '\''
        + ", state='"
        + state
        + '\''
        + ", loadingPoint='"
        + loadingPoint
        + '\''
        + ", loadingDateDate='"
        + loadingDateDate
        + '\''
        + ", loadingDateTime='"
        + loadingDateTime
        + '\''
        + ", loadingDeadline='"
        + loadingDeadline
        + '\''
        + ", vehicleData="
        + vehicleData
        + ", localities='"
        + localities
        + '\''
        + ", truckType='"
        + truckType
        + '\''
        + ", truck='"
        + truck
        + '\''
        + ", comment='"
        + comment
        + '\''
        + ", complete='"
        + complete
        + '\''
        + ", from='"
        + from
        + '\''
        + ", to='"
        + to
        + '\''
        + ", toDate='"
        + toDate
        + '\''
        + ", toTime='"
        + toTime
        + '\''
        + ", shippingAgent='"
        + shippingAgent
        + '\''
        + ", client='"
        + client
        + '\''
        + ", xmlForImport='"
        + xmlForImport
        + '\''
        + ", warrantyType='"
        + warrantyType
        + '\''
        + ", borderCrossingData="
        + borderCrossingData
        + '}';
  }

  public static class Builder {
    private String lotNumber;
    private List<VehicleData> vehicleData = new ArrayList<>();
    private String state;
    private String loadingPoint;
    private String loadingDateDate;
    private String loadingDateTime;
    private String loadingDeadline;
    private String localities;
    private String truckType;
    private String truck;
    private String comment;
    private String complete;
    private String from;
    private String to;
    private String toDate;
    private String toTime;
    private String shippingAgent;
    private String client;
    private String xmlForImport;
    private String warrantyType;
    private List<BorderCrossingData> borderCrossingData;

    public Builder lotNumber(String lotNumber) {
      this.lotNumber = lotNumber;
      return this;
    }

    public Builder vehicleData(List<VehicleData> vehicleData) {
      this.vehicleData = vehicleData;
      return this;
    }

    public Builder state(String state) {
      this.state = state;
      return this;
    }

    public Builder loadingPoint(String loadingPoint) {
      this.loadingPoint = loadingPoint;
      return this;
    }

    public Builder loadingDateDate(String loadingDateDate) {
      this.loadingDateDate = loadingDateDate;
      return this;
    }

    public Builder loadingDateTime(String loadingDateTime) {
      this.loadingDateTime = loadingDateTime;
      return this;
    }

    public Builder loadingDeadline(String loadingDeadline) {
      this.loadingDeadline = loadingDeadline;
      return this;
    }

    public Builder localities(String localities) {
      this.localities = localities;
      return this;
    }

    public Builder truckType(String truckType) {
      this.truckType = truckType;
      return this;
    }

    public Builder truck(String truck) {
      this.truck = truck;
      return this;
    }

    public Builder comment(String comment) {
      this.comment = comment;
      return this;
    }

    public Builder complete(String complete) {
      this.complete = complete;
      return this;
    }

    public Builder from(String from) {
      this.from = from;
      return this;
    }

    public Builder to(String to) {
      this.to = to;
      return this;
    }

    public Builder toDate(String toDate) {
      this.toDate = toDate;
      return this;
    }

    public Builder toTime(String toTime) {
      this.toTime = toTime;
      return this;
    }

    public Builder shippingAgent(String shippingAgent) {
      this.shippingAgent = shippingAgent;
      return this;
    }

    public Builder client(String client) {
      this.client = client;
      return this;
    }

    public Builder xmlForImport(String xmlForImport) {
      this.xmlForImport = xmlForImport;
      return this;
    }

    public Builder warrantyType(String warrantyType) {
      this.warrantyType = warrantyType;
      return this;
    }

    public Builder borderCrossingData(List<BorderCrossingData> borderCrossingData) {
      this.borderCrossingData = borderCrossingData;
      return this;
    }

    public LotData build() {
      return new LotData(this);
    }
  }
}
