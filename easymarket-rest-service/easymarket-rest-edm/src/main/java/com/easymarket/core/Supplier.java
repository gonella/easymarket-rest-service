package com.easymarket.core;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name = "supplier")
@NamedQueries(
        { @NamedQuery(name = "com.easymarket.core.Supplier.findAll", query = "SELECT p FROM Supplier p") ,
            @NamedQuery(name = "com.easymarket.core.Supplier.findBasedOnCityAndStateAndCountry",
            query = "SELECT p FROM Supplier p where p.city = :city AND p.state = :state AND p.country = :country") }
        )
public class Supplier extends ViewResource implements java.io.Serializable, IBaseResource {

    @Column(nullable = false)
    private String gpsCoordinate;

    @Column(nullable = false)
    private String supplierType;

    //##########ADDRESS
    //FIXME - Link to Address object
    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;
    //##########ADDRESS


    public Supplier() {
    }

    public Supplier(String name) {
        this.setName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Supplier)) {
            return false;
        }

        final Supplier that = (Supplier) o;

        return Objects.equals(this.getId(), that.getId()) && Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
