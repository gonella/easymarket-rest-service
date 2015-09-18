package org.easymarket.ti.edm.v1;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ITSupplier extends ITBaseResource{

    private String gpsCoordinate;
    private String supplierType;
    private String city;
    private String state;
    private String country;

    public ITSupplier(){}



    public String getGpsCoordinate() {
        return gpsCoordinate;
    }

    public void setGpsCoordinate(String gpsCoordinate) {
        this.gpsCoordinate = gpsCoordinate;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
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

    @Override
    public String toString(){
        String string = ReflectionToStringBuilder.toString(this);
        return string;
    }
}
