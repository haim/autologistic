package com.estafeta.test.ui.data.transports.transports;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class TransportData {

  private final String board;
  private final String owner;
  private final String transport;
  private final String transportPart;
  private final String firstDriver;

  private TransportData(Builder builder) {
    this.board = builder.board;
    this.owner = builder.owner;
    this.transport = builder.transport;
    this.transportPart = builder.transportPart;
    this.firstDriver = builder.firstDriver;
  }

  public String getBoard() {
    return board;
  }

  public String getOwner() {
    return owner;
  }

  public String getTransport() {
    return transport;
  }

  public String getTransportPart() {
    return transportPart;
  }

  public String getFirstDriver() {
    return firstDriver;
  }

  @Override
  public String toString() {
    return "TransportData{"
        + "board='"
        + board
        + '\''
        + ", owner='"
        + owner
        + '\''
        + ", transport='"
        + transport
        + '\''
        + ", transportPart='"
        + transportPart
        + '\''
        + ", firstDriver='"
        + firstDriver
        + '\''
        + '}';
  }

  public static class Builder {
    private String board;
    private String owner;
    private String transport;
    private String transportPart;
    private String firstDriver;;

    public Builder board(String board) {
      this.board = board;
      return this;
    }

    public Builder owner(String owner) {
      this.owner = owner;
      return this;
    }

    public Builder transport(String transport) {
      this.transport = transport;
      return this;
    }

    public Builder transportPart(String transportPart) {
      this.transportPart = transportPart;
      return this;
    }

    public Builder firstDriver(String firstDriver) {
      this.firstDriver = firstDriver;
      return this;
    }

    public TransportData build() {
      return new TransportData(this);
    }
  }
}
