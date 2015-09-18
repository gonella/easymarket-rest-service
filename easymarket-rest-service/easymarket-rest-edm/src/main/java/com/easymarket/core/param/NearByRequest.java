package com.easymarket.core.param;

import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Temporary class to request supplier/products near by the customer
 */
public class NearByRequest {

    @QueryParam("gpsCoordinates")
    private String gpsCoordinates;
    @QueryParam("maxLimitInMeter")
    private Integer maxLimitInMeter;

    //FIXME - we should remove address attribute below, GPS coordinate will be enough to provide address. We need to add GPS finder funcionality in backend. So far we are passing address.
    @QueryParam("city")
    @Deprecated
    private String city;
    @QueryParam("state")
    @Deprecated
    private String state;
    @QueryParam("country")
    @Deprecated
    private String country;

    public NearByRequest(){};

    public String getGpsCoordinates() {
        return gpsCoordinates;
    }
    public void setGpsCoordinates(String gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }
    public Integer getMaxLimitInMeter() {
        return maxLimitInMeter;
    }
    public void setMaxLimitInMeter(Integer maxLimitInMeter) {
        this.maxLimitInMeter = maxLimitInMeter;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
