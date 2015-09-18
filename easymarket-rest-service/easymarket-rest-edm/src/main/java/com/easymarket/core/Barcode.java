package com.easymarket.core;

/**
 * https://en.wikipedia.org/wiki/Barcode
 *
 * @author Adriano
 *
 */
public class Barcode implements java.io.Serializable, IBaseResource{

    private String barcode;
    private BarcodeType type;

    public Barcode() {
    }

    public BarcodeType getType() {
        return type;
    }

    public void setType(BarcodeType type) {
        this.type = type;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
