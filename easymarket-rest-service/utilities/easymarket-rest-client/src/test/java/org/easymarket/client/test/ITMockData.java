package org.easymarket.client.test;

import java.util.ArrayList;
import java.util.List;

import org.easymarket.ti.edm.v1.ITBarcode;
import org.easymarket.ti.edm.v1.ITBarcodeType;
import org.easymarket.ti.edm.v1.ITProduct;
import org.easymarket.ti.edm.v1.ITProductCategory;
import org.easymarket.ti.edm.v1.ITSupplier;
import org.easymarket.ti.edm.v1.ITSupplierDepartment;
import org.easymarket.ti.edm.v1.ITSupplierType;

public class ITMockData {

    public final String GPS_COORDINATE_GONELLA_HOME="-30.0165706,-51.1691847";


    public List<ITProduct> mockProductsFromPortoAlegre(
            List<ITSupplier> listSupplier,
            List<ITSupplierDepartment> listDepartament,
            List<ITProductCategory> listCategory) {
        List<ITProduct> listProduct = new ArrayList<ITProduct>();

        //Barcode type - code128
        //Building products
        //Barcode generated below are unique.
        //Porto Alegre
        listProduct.add(buildProduct("Product1",  "98376373783111111111",ITBarcodeType.CODE_128,"", 1.55,listSupplier.get(0),listDepartament.get(0), listCategory.get(0).getName().toString()));
        listProduct.add(buildProduct("Product2",  "98376373783111111112",ITBarcodeType.CODE_128,"", 2.55,listSupplier.get(0),listDepartament.get(0), listCategory.get(0).getName().toString()));
        listProduct.add(buildProduct("Product3",  "98376373783111111113",ITBarcodeType.CODE_128,"", 3.55,listSupplier.get(0),listDepartament.get(1), listCategory.get(0).getName().toString()));
        listProduct.add(buildProduct("Product4",  "98376373783111111114",ITBarcodeType.CODE_128,"", 4.55,listSupplier.get(0),listDepartament.get(1), listCategory.get(1).getName().toString()));
        listProduct.add(buildProduct("Product5",  "98376373783111111115",ITBarcodeType.CODE_128,"", 5.55,listSupplier.get(0),listDepartament.get(2), listCategory.get(1).getName().toString()));
        listProduct.add(buildProduct("Product6",  "98376373783111111116",ITBarcodeType.CODE_128,"", 5.55,listSupplier.get(1),listDepartament.get(2), listCategory.get(1).getName().toString()));
        listProduct.add(buildProduct("Product7",  "98376373783111111117",ITBarcodeType.CODE_128,"", 5.55,listSupplier.get(1),listDepartament.get(3), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product8",  "98376373783111111118",ITBarcodeType.CODE_128,"", 6.55,listSupplier.get(1),listDepartament.get(3), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product9",  "98376373783111111119",ITBarcodeType.CODE_128,"", 6.55,listSupplier.get(1),listDepartament.get(3), listCategory.get(1).getName().toString()));
        listProduct.add(buildProduct("Product10", "98376373783111111120",ITBarcodeType.CODE_128,"", 6.55,listSupplier.get(1),listDepartament.get(4), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product11", "98376373783111111121",ITBarcodeType.CODE_128,"", 7.55,listSupplier.get(2),listDepartament.get(4), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product12", "98376373783111111122",ITBarcodeType.CODE_128,"", 7.55,listSupplier.get(2),listDepartament.get(4), listCategory.get(1).getName().toString()));
        listProduct.add(buildProduct("Product13", "98376373783111111123",ITBarcodeType.CODE_128,"", 7.55,listSupplier.get(2),listDepartament.get(5), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product14", "98376373783111111124",ITBarcodeType.CODE_128,"", 8.55,listSupplier.get(3),listDepartament.get(5), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product15", "98376373783111111125",ITBarcodeType.CODE_128,"", 8.55,listSupplier.get(3),listDepartament.get(5), listCategory.get(1).getName().toString()));
        listProduct.add(buildProduct("Product16", "98376373783111111126",ITBarcodeType.CODE_128,"", 8.55,listSupplier.get(3),listDepartament.get(6), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product17", "98376373783111111127",ITBarcodeType.CODE_128,"", 9.55,listSupplier.get(4),listDepartament.get(6), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product18", "98376373783111111128",ITBarcodeType.CODE_128,"", 10.55,listSupplier.get(4),listDepartament.get(6), listCategory.get(1).getName().toString()));
        listProduct.add(buildProduct("Product19", "98376373783111111129",ITBarcodeType.CODE_128,"", 12.55,listSupplier.get(5),listDepartament.get(7), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product20", "98376373783111111130",ITBarcodeType.CODE_128,"", 13.55,listSupplier.get(5),listDepartament.get(7), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product21", "98376373783111111131",ITBarcodeType.CODE_128,"", 2.35,listSupplier.get(6),listDepartament.get(7), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product22", "98376373783111111132",ITBarcodeType.CODE_128,"", 3.35,listSupplier.get(6),listDepartament.get(7), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product23", "98376373783111111133",ITBarcodeType.CODE_128,"", 4.45,listSupplier.get(6),listDepartament.get(8), listCategory.get(1).getName().toString()));
        listProduct.add(buildProduct("Product24", "98376373783111111134",ITBarcodeType.CODE_128,"", 1.15,listSupplier.get(7),listDepartament.get(8), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product25", "98376373783111111135",ITBarcodeType.CODE_128,"", 2.15,listSupplier.get(7),listDepartament.get(8), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product26", "98376373783111111136",ITBarcodeType.CODE_128,"", 6.25,listSupplier.get(7),listDepartament.get(8), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product27", "98376373783111111137",ITBarcodeType.CODE_128,"", 5.35,listSupplier.get(8),listDepartament.get(9), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product28", "98376373783111111138",ITBarcodeType.CODE_128,"", 6.45,listSupplier.get(8),listDepartament.get(9), listCategory.get(1).getName().toString()));
        listProduct.add(buildProduct("Product29", "98376373783111111139",ITBarcodeType.CODE_128,"", 0.55,listSupplier.get(8),listDepartament.get(9), listCategory.get(2).getName().toString()));
        listProduct.add(buildProduct("Product30", "98376373783111111140",ITBarcodeType.CODE_128,"", 18.65,listSupplier.get(8),listDepartament.get(9), listCategory.get(2).getName().toString()));
        return listProduct;
    }
    public List<ITProduct> mockProductsFromNovoHamburgo(
            List<ITSupplier> listSupplier,
            List<ITSupplierDepartment> listDepartament,
            List<ITProductCategory> listCategory) {
        List<ITProduct> listProduct = new ArrayList<ITProduct>();


        //Novo Hamburgo
        listProduct.add(buildProduct("Product31", "5410471130901",ITBarcodeType.EAN_13,"", 18.65,listSupplier.get(9),listDepartament.get(2), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product32", "39047046023",ITBarcodeType.UPC_A,"", 18.65,listSupplier.get(9),listDepartament.get(2), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product33", "7891962030678",ITBarcodeType.EAN_13,"", 8.65,listSupplier.get(9),listDepartament.get(2), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product34", "7891150008502",ITBarcodeType.EAN_13,"", 9.65,listSupplier.get(9),listDepartament.get(3), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product35", "7897974400522",ITBarcodeType.EAN_13,"", 10.65,listSupplier.get(9),listDepartament.get(3), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product36", "7898994006091",ITBarcodeType.EAN_13,"", 11.65,listSupplier.get(9),listDepartament.get(3), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product37", "7891000100721",ITBarcodeType.EAN_13,"", 12.65,listSupplier.get(9),listDepartament.get(4), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product38", "7891000369500",ITBarcodeType.EAN_13,"", 13.65,listSupplier.get(9),listDepartament.get(4), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product39", "7896004002538",ITBarcodeType.EAN_13,"", 14.65,listSupplier.get(9),listDepartament.get(4), listCategory.get(2).getId().toString()));
        return listProduct;
    }


    public List<ITSupplier> mockSuppliersPortoAlegre() {
        //Building supplier
        List<ITSupplier> listSupplier = mockSuppliersPortoAlegreClose2000MetersFromGonellaHome();
        listSupplier.add(buildSupplier("Zaffari2", "Zaffari Anita", "-30.0267408,-51.1903654",ITSupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Zaffari3", "Zaffari Strip Center", "-30.0080264,-51.1432283",ITSupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Zaffari5", "Zaffari Bordini", "-30.0218756,-51.1968389",ITSupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Zaffari6", "Zaffari Cristovão", "-30.0210581,-51.2076992",ITSupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        return listSupplier;
    }

    public List<ITSupplier> mockSuppliersPortoAlegreClose2000MetersFromGonellaHome() {
        List<ITSupplier> listSupplier = new ArrayList<ITSupplier>();
        listSupplier.add(buildSupplier("Zaffari1", "Zaffari Higienopolis", "-30.0192702,-51.1815179",ITSupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Zaffari4", "Zaffari Country", "-30.0203574,-51.1643836",ITSupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Nacional1", "Nacional Plinio",   "-30.0200599,-51.1793669",ITSupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Nacional2", "Nacional Iguatemi", "-30.0165611,-51.1691793",ITSupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Nacional3", "Nacional sertorio", "-30.0160271,-51.1690784",ITSupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        return listSupplier;
    }

    public List<ITSupplier> mockSuppliersNovoHamburgo() {
        //Building supplier
        List<ITSupplier> listSupplier = new ArrayList<ITSupplier>();
        listSupplier.add(buildSupplier("Zaffari7", "Zaffari Novo Hamburgo", "-29.6944939,-51.1311651",ITSupplierType.MARKET,"Novo Hamburgo","RS","Brazil"));
        return listSupplier;
    }
    public ITSupplier buildSupplier(String name,String description,String gpsCoordinate,ITSupplierType supplierType
            ,String city,String state,String country
            ){
        ITSupplier obj=new ITSupplier();
        obj.setName(name);
        obj.setDescription(description);
        obj.setGpsCoordinate(gpsCoordinate);
        obj.setSupplierType(supplierType.toString());

        obj.setCity(city);
        obj.setState(state);
        obj.setCountry(country);

        return obj;
    }
    public ITProduct buildProduct(String name, String barCode,ITBarcodeType type,String description, double price,ITSupplier supplier,ITSupplierDepartment department, String category) {

        ITProduct product = new ITProduct();

        ITBarcode barcodeObj = new ITBarcode();
        barcodeObj.setBarcode(barCode);
        barcodeObj.setType(type);
        product.setBarcode(barcodeObj);

        product.setName(name);
        product.setDescription(description);
        product.setSupplier(supplier);
        product.setDepartment(department);
        product.setCategory(category);
        product.setPrice(price);

        return product;
    }

    public List<ITProductCategory> mockProductCategory() {
        //Bulding product category
        List<ITProductCategory> listCategory = new ArrayList<ITProductCategory>();
        listCategory.add(buildCategory("Category1", ""));
        listCategory.add(buildCategory("Category2", ""));
        listCategory.add(buildCategory("Category3", ""));
        return listCategory;
    }
    public ITProductCategory buildCategory(String name, String description) {
        ITProductCategory obj = new ITProductCategory();
        obj.setName(name);
        obj.setDescription(description);
        return obj;
    }
    public ITSupplierDepartment buildDepartament(String name, String description, ITSupplierType supplierType) {
        ITSupplierDepartment supplierDepartament = new ITSupplierDepartment();
        supplierDepartament.setName(name);
        supplierDepartament.setDescription(description);
        supplierDepartament.setSupplierType(supplierType.toString());
        return supplierDepartament;
    }

    public List<ITSupplierDepartment> mockDepartament() {
        //Building supplier departament
        List<ITSupplierDepartment> listDepartament = new ArrayList<ITSupplierDepartment>();
        listDepartament.add(buildDepartament("Açougue", "Carnes bovinas, suínas e aves",ITSupplierType.MARKET));
        listDepartament.add(buildDepartament("Bebidas",
                "Os mais diversos tipos de bebidas nacionais e importadas, que vão de vinhos, espumantes, destiladas, cervejas, refrigerantes, sucos, águas entre outras",ITSupplierType.MARKET));
        listDepartament.add(buildDepartament("Floricultura", "Plantas e flores",ITSupplierType.MARKET));
        listDepartament.add(
                buildDepartament("Frios e Laticínios", "Leite, queijo, iorgute, manteiga, salame, presunto e outros",ITSupplierType.MARKET));
        listDepartament.add(buildDepartament("Hortifruti", "Legumes e verduras",ITSupplierType.MARKET));
        listDepartament.add(buildDepartament("Limpeza",
                " materiais de limpeza, tanto para a área doméstica quanto para a profissiona",ITSupplierType.MARKET));
        listDepartament.add(buildDepartament("Mercearia",
                "Tradicionais de matinais, doces e enlatados, diet/light, produtos orientais e importados",ITSupplierType.MARKET));
        listDepartament.add(buildDepartament("Padaria e Confeitaria", "",ITSupplierType.MARKET));
        listDepartament.add(buildDepartament("Peixaria", "",ITSupplierType.MARKET));
        listDepartament.add(buildDepartament("Perfumaria e Higiene", "",ITSupplierType.MARKET));
        listDepartament.add(buildDepartament("Adega", "",ITSupplierType.MARKET));
        return listDepartament;
    }
}
