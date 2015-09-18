package org.easymarket.ti.edm.v1;

/**
 * https://en.wikipedia.org/wiki/Barcode
 *
 * @author Adriano
 *
 */
public class ITBarcode {

    private String barcode;
    private ITBarcodeType type;

    public ITBarcode() {
    }

    public ITBarcodeType getType() {
        return type;
    }

    public void setType(ITBarcodeType type) {
        this.type = type;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
