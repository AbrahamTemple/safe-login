package com.spring.logauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.10.1
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IpInfo implements Serializable {
    String status;
    String device;
    String country;
    String countryCode;
    String region;
    String regionName;
    String city;
    String zip;
    Double lat;
    Double lon;
    String timezone;
    String isp;
    String org;
    String as;
    String query;

    public IpInfo(String status, String device, Double lat, Double lon, String query) {
        this.status = status;
        this.device = device;
        this.lat = lat;
        this.lon = lon;
        this.query = query;
    }

    @Override
    public String toString() {
        return "IpInfo{" +
                "status='" + status + '\'' +
                ", device='" + device + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", region='" + region + '\'' +
                ", regionName='" + regionName + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", timezone='" + timezone + '\'' +
                ", isp='" + isp + '\'' +
                ", org='" + org + '\'' +
                ", as='" + as + '\'' +
                ", query='" + query + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpInfo ipInfo = (IpInfo) o;
        return Objects.equals(status, ipInfo.status) &&
                Objects.equals(country, ipInfo.country) &&
                Objects.equals(countryCode, ipInfo.countryCode) &&
                Objects.equals(region, ipInfo.region) &&
                Objects.equals(regionName, ipInfo.regionName) &&
                Objects.equals(city, ipInfo.city) &&
                Objects.equals(zip, ipInfo.zip) &&
                Objects.equals(lat, ipInfo.lat) &&
                Objects.equals(lon, ipInfo.lon) &&
                Objects.equals(timezone, ipInfo.timezone) &&
                Objects.equals(isp, ipInfo.isp) &&
                Objects.equals(org, ipInfo.org) &&
                Objects.equals(as, ipInfo.as) &&
                Objects.equals(query, ipInfo.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, country, countryCode, region, regionName, city, zip, lat, lon, timezone, isp, org, as, query);
    }
}
