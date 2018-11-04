package com.estafeta.test.ui.data.lots;

public class BorderCrossingData {

  private final String name;
  private final String date;
  private final String time;
  private final String crossingTime;

  private BorderCrossingData(Builder builder) {
    this.name = builder.name;
    this.date = builder.date;
    this.time = builder.time;
    this.crossingTime = builder.crossingTime;
  }

  public String getName() {
    return name;
  }

  public String getDate() {
    return date;
  }

  public String getTime() {
    return time;
  }

  public String getCrossingTime() {
    return crossingTime;
  }

  @Override
  public String toString() {
    return "BorderCrossingData{"
        + "name='"
        + name
        + '\''
        + ", date='"
        + date
        + '\''
        + ", time='"
        + time
        + '\''
        + ", crossingTime='"
        + crossingTime
        + '\''
        + '}';
  }

  public static class Builder {
    private String name;
    private String date;
    private String time;
    private String crossingTime;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder date(String date) {
      this.date = date;
      return this;
    }

    public Builder time(String time) {
      this.time = time;
      return this;
    }

    public Builder crossingTime(String crossingTime) {
      this.crossingTime = crossingTime;
      return this;
    }

    public BorderCrossingData build() {
      return new BorderCrossingData(this);
    }
  }
}
