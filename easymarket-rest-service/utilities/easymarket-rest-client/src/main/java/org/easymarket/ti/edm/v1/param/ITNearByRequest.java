package org.easymarket.ti.edm.v1.param;

/**
 *  Model used to ask for supplier around.
 */
public class ITNearByRequest implements java.io.Serializable, IQueryBeanParam{

    private String gpsCoordinates;
    private Integer maxLimitInMeter;
    //FIXME - we should remove address attribute below, GPS coordinate will be enough to provide address. We need to add GPS finder funcionality in backend. So far we are passing address.
    @Deprecated
    private String city;
    @Deprecated
    private String state;
    @Deprecated
    private String country;

    public ITNearByRequest(){};

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
