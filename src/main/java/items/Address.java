package items;

import java.io.Serializable;

public class Address implements Serializable {
    String region;
    String province;
    String municipality;
    String countryName;

    public Address() {
    }

    public Address(String region, String province, String municipality) {
        this.region = region;
        this.province = province;
        this.municipality = municipality;
        this.countryName = "Italy";
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
