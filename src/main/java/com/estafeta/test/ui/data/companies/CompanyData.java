package com.estafeta.test.ui.data.companies;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class CompanyData {
  private final String nativeName;
  private final String englishName;
  private final String localName;
  private final String legalAddress;
  private final String legalAddressEnglish;

  private CompanyData(Builder builder) {
    this.nativeName = builder.nativeName;
    this.englishName = builder.englishName;
    this.localName = builder.localName;
    this.legalAddress = builder.legalAddress;
    this.legalAddressEnglish = builder.legalAddressEnglish;
  }

  public String getNativeName() {
    return nativeName;
  }

  public String getEnglishName() {
    return englishName;
  }

  public String getLocalName() {
    return localName;
  }

  public String getLegalAddress() {
    return legalAddress;
  }

  public String getLegalAddressEnglish() {
    return legalAddressEnglish;
  }

  @Override
  public String toString() {
    return "CompanyData{"
        + "nativeName='"
        + nativeName
        + '\''
        + ", englishName='"
        + englishName
        + '\''
        + ", localName='"
        + localName
        + '\''
        + ", legalAddress='"
        + legalAddress
        + '\''
        + ", legalAddressEnglish='"
        + legalAddressEnglish
        + '\''
        + '}';
  }

  public static class Builder {
    private String nativeName;
    private String englishName;
    private String localName;
    private String legalAddress;
    private String legalAddressEnglish;

    public Builder nativeName(String nativeName) {
      this.nativeName = nativeName;
      return this;
    }

    public Builder englishName(String englishName) {
      this.englishName = englishName;
      return this;
    }

    public Builder localName(String localName) {
      this.localName = localName;
      return this;
    }

    public Builder legalAddress(String legalAddress) {
      this.legalAddress = legalAddress;
      return this;
    }

    public Builder legalAddressEnglish(String legalAddressEnglish) {
      this.legalAddressEnglish = legalAddressEnglish;
      return this;
    }

    public CompanyData build() {
      return new CompanyData(this);
    }
  }
}
