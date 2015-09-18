package com.easymartket.mockdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.easymarket.core.Barcode;
import com.easymarket.core.BarcodeType;
import com.easymarket.core.Product;
import com.easymarket.core.ProductCategory;
import com.easymarket.core.Supplier;
import com.easymarket.core.SupplierDepartment;
import com.easymarket.core.SupplierType;


/**
 * Mock data for unit tests
 */
public class MockData {

    public final String GPS_COORDINATE_GONELLA_HOME="-30.0165706,-51.1691847";


    public List<Product> mockProductsFromPortoAlegre(
            List<Supplier> listSupplier,
            List<SupplierDepartment> listDepartament,
            List<ProductCategory> listCategory) {
        List<Product> listProduct = new ArrayList<Product>();

        //Barcode type - code128
        //Building products
        //Barcode generated below are unique.
        //Porto Alegre
        listProduct.add(buildProduct("Product1",  "98376373783111111111",BarcodeType.CODE_128,"", 1.55,listSupplier.get(0),listDepartament.get(0), listCategory.get(0).getId().toString()));
        listProduct.add(buildProduct("Product2",  "98376373783111111112",BarcodeType.CODE_128,"", 2.55,listSupplier.get(0),listDepartament.get(0), listCategory.get(0).getId().toString()));
        listProduct.add(buildProduct("Product3",  "98376373783111111113",BarcodeType.CODE_128,"", 3.55,listSupplier.get(0),listDepartament.get(1), listCategory.get(0).getId().toString()));
        listProduct.add(buildProduct("Product4",  "98376373783111111114",BarcodeType.CODE_128,"", 4.55,listSupplier.get(0),listDepartament.get(1), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product5",  "98376373783111111115",BarcodeType.CODE_128,"", 5.55,listSupplier.get(0),listDepartament.get(2), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product6",  "98376373783111111116",BarcodeType.CODE_128,"", 5.55,listSupplier.get(1),listDepartament.get(2), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product7",  "98376373783111111117",BarcodeType.CODE_128,"", 5.55,listSupplier.get(1),listDepartament.get(3), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product8",  "98376373783111111118",BarcodeType.CODE_128,"", 6.55,listSupplier.get(1),listDepartament.get(3), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product9",  "98376373783111111119",BarcodeType.CODE_128,"", 6.55,listSupplier.get(1),listDepartament.get(3), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product10", "98376373783111111120",BarcodeType.CODE_128,"", 6.55,listSupplier.get(1),listDepartament.get(4), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product11", "98376373783111111121",BarcodeType.CODE_128,"", 7.55,listSupplier.get(2),listDepartament.get(4), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product12", "98376373783111111122",BarcodeType.CODE_128,"", 7.55,listSupplier.get(2),listDepartament.get(4), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product13", "98376373783111111123",BarcodeType.CODE_128,"", 7.55,listSupplier.get(2),listDepartament.get(5), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product14", "98376373783111111124",BarcodeType.CODE_128,"", 8.55,listSupplier.get(3),listDepartament.get(5), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product15", "98376373783111111125",BarcodeType.CODE_128,"", 8.55,listSupplier.get(3),listDepartament.get(5), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product16", "98376373783111111126",BarcodeType.CODE_128,"", 8.55,listSupplier.get(3),listDepartament.get(6), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product17", "98376373783111111127",BarcodeType.CODE_128,"", 9.55,listSupplier.get(4),listDepartament.get(6), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product18", "98376373783111111128",BarcodeType.CODE_128,"", 10.55,listSupplier.get(4),listDepartament.get(6), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product19", "98376373783111111129",BarcodeType.CODE_128,"", 12.55,listSupplier.get(5),listDepartament.get(7), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product20", "98376373783111111130",BarcodeType.CODE_128,"", 13.55,listSupplier.get(5),listDepartament.get(7), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product21", "98376373783111111131",BarcodeType.CODE_128,"", 2.35,listSupplier.get(6),listDepartament.get(7), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product22", "98376373783111111132",BarcodeType.CODE_128,"", 3.35,listSupplier.get(6),listDepartament.get(7), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product23", "98376373783111111133",BarcodeType.CODE_128,"", 4.45,listSupplier.get(6),listDepartament.get(8), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product24", "98376373783111111134",BarcodeType.CODE_128,"", 1.15,listSupplier.get(7),listDepartament.get(8), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product25", "98376373783111111135",BarcodeType.CODE_128,"", 2.15,listSupplier.get(7),listDepartament.get(8), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product26", "98376373783111111136",BarcodeType.CODE_128,"", 6.25,listSupplier.get(7),listDepartament.get(8), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product27", "98376373783111111137",BarcodeType.CODE_128,"", 5.35,listSupplier.get(8),listDepartament.get(9), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product28", "98376373783111111138",BarcodeType.CODE_128,"", 6.45,listSupplier.get(8),listDepartament.get(9), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product29", "98376373783111111139",BarcodeType.CODE_128,"", 0.55,listSupplier.get(8),listDepartament.get(9), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product30", "98376373783111111140",BarcodeType.CODE_128,"", 18.65,listSupplier.get(8),listDepartament.get(9), listCategory.get(2).getId().toString()));

        return listProduct;
    }


    public List<Product> mockProductsFromNovoHamburgo(
            List<Supplier> listSupplier,
            List<SupplierDepartment> listDepartament,
            List<ProductCategory> listCategory) {
        List<Product> listProduct = new ArrayList<Product>();


        //Novo Hamburgo
        listProduct.add(buildProduct("Product31", "5410471130901",BarcodeType.EAN_13,"", 18.65,listSupplier.get(9),listDepartament.get(2), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product32", "39047046023",BarcodeType.UPC_A,"", 18.65,listSupplier.get(9),listDepartament.get(2), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product33", "7891962030678",BarcodeType.EAN_13,"", 8.65,listSupplier.get(9),listDepartament.get(2), listCategory.get(1).getId().toString()));
        listProduct.add(buildProduct("Product34", "7891150008502",BarcodeType.EAN_13,"", 9.65,listSupplier.get(9),listDepartament.get(3), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product35", "7897974400522",BarcodeType.EAN_13,"", 10.65,listSupplier.get(9),listDepartament.get(3), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product36", "7898994006091",BarcodeType.EAN_13,"", 11.65,listSupplier.get(9),listDepartament.get(3), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product37", "7891000100721",BarcodeType.EAN_13,"", 12.65,listSupplier.get(9),listDepartament.get(4), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product38", "7891000369500",BarcodeType.EAN_13,"", 13.65,listSupplier.get(9),listDepartament.get(4), listCategory.get(2).getId().toString()));
        listProduct.add(buildProduct("Product39", "7896004002538",BarcodeType.EAN_13,"", 14.65,listSupplier.get(9),listDepartament.get(4), listCategory.get(2).getId().toString()));
        return listProduct;
    }

    public List<ProductCategory> mockProductCategory() {
        //Bulding product category
        List<ProductCategory> listCategory = new ArrayList<ProductCategory>();
        listCategory.add(buildCategory("Category1", ""));
        listCategory.add(buildCategory("Category2", ""));
        listCategory.add(buildCategory("Category3", ""));
        return listCategory;
    }

    public List<SupplierDepartment> mockDepartament() {
        //Building supplier departament
        List<SupplierDepartment> listDepartament = new ArrayList<SupplierDepartment>();
        listDepartament.add(buildDepartament("Açougue", "Carnes bovinas, suínas e aves",SupplierType.MARKET));
        listDepartament.add(buildDepartament("Bebidas", "Os mais diversos tipos de bebidas nacionais e importadas, que vão de vinhos, espumantes, destiladas, cervejas, refrigerantes, sucos, águas entre outras",SupplierType.MARKET));
        listDepartament.add(buildDepartament("Floricultura", "Plantas e flores",SupplierType.MARKET));
        listDepartament.add(buildDepartament("Frios e Laticínios", "Leite, queijo, iorgute, manteiga, salame, presunto e outros",SupplierType.MARKET));
        listDepartament.add(buildDepartament("Hortifruti", "Legumes e verduras",SupplierType.MARKET));
        listDepartament.add(buildDepartament("Limpeza",
                " materiais de limpeza, tanto para a área doméstica quanto para a profissiona",SupplierType.MARKET));
        listDepartament.add(buildDepartament("Mercearia",
                "Tradicionais de matinais, doces e enlatados, diet/light, produtos orientais e importados",SupplierType.MARKET));
        listDepartament.add(buildDepartament("Padaria e Confeitaria", "",SupplierType.MARKET));
        listDepartament.add(buildDepartament("Peixaria", "",SupplierType.MARKET));
        listDepartament.add(buildDepartament("Perfumaria e Higiene", "",SupplierType.MARKET));
        listDepartament.add(buildDepartament("Adega", "",SupplierType.MARKET));
        return listDepartament;
    }

    public List<Supplier> mockSuppliersPortoAlegre() {
        //Building supplier
        List<Supplier> listSupplier = mockSuppliersPortoAlegreClose2000MetersFromGonellaHome();
        listSupplier.add(buildSupplier("Zaffari2", "Zaffari Anita", "-30.0267408,-51.1903654",SupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Zaffari3", "Zaffari Strip Center", "-30.0080264,-51.1432283",SupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Zaffari5", "Zaffari Bordini", "-30.0218756,-51.1968389",SupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Zaffari6", "Zaffari Cristovão", "-30.0210581,-51.2076992",SupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        return listSupplier;
    }

    public List<Supplier> mockSuppliersPortoAlegreClose2000MetersFromGonellaHome() {
        List<Supplier> listSupplier = new ArrayList<Supplier>();
        listSupplier.add(buildSupplier("Zaffari1", "Zaffari Higienopolis", "-30.0192702,-51.1815179",SupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Zaffari4", "Zaffari Country", "-30.0203574,-51.1643836",SupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Nacional1", "Nacional Plinio",   "-30.0200599,-51.1793669",SupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Nacional2", "Nacional Iguatemi", "-30.0165611,-51.1691793",SupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        listSupplier.add(buildSupplier("Nacional3", "Nacional sertorio", "-30.0160271,-51.1690784",SupplierType.MARKET,"Porto Alegre","RS","Brazil"));
        return listSupplier;
    }
    public List<Supplier> mockSuppliersNovoHamburgo(List<SupplierType> listSupplierType) {
        //Building supplier
        List<Supplier> listSupplier = new ArrayList<Supplier>();
        listSupplier.add(buildSupplier("Zaffari7", "Zaffari Novo Hamburgo", "-29.6944939,-51.1311651",SupplierType.MARKET,"Novo Hamburgo","RS","Brazil"));
        return listSupplier;
    }


    public Supplier buildSupplier(String name,String description,String gpsCoordinate,SupplierType supplierType
            ,String city,String state,String country
            ){
        Supplier obj=new Supplier();
        obj.setId(generateLongGenerator());
        obj.setName(name);
        obj.setDescription(description);
        obj.setGpsCoordinate(gpsCoordinate);
        obj.setSupplierType(supplierType.toString());
        obj.setCity(city);
        obj.setState(state);
        obj.setCountry(country);

        return obj;
    }

    public Product buildProduct(String name, String barCode,BarcodeType type,String description, double price,Supplier supplier,SupplierDepartment departament, String categoryId) {

        Product product = new Product();
        product.setId(generateLongGenerator());
        Barcode barcodeObj = new Barcode();
        barcodeObj.setBarcode(barCode);
        barcodeObj.setType(type);
        product.setBarcode(barcodeObj);
        product.setName(name);
        product.setDescription(description);
        product.setSupplier(supplier);
        product.setDepartment(departament);
        product.setCategory(categoryId);

        return product;
    }

    public SupplierDepartment buildDepartament(String name, String description,SupplierType supplierType) {
        SupplierDepartment supplierDepartament = new SupplierDepartment();
        supplierDepartament.setId(generateLongGenerator());
        supplierDepartament.setName(name);
        supplierDepartament.setDescription(description);
        supplierDepartament.setSupplierType(supplierType.toString());
        return supplierDepartament;
    }

    public ProductCategory buildCategory(String name, String description) {
        ProductCategory obj = new ProductCategory();
        obj.setId(generateLongGenerator());
        obj.setName(name);
        obj.setDescription(description);
        return obj;
    }

    public long generateLongGenerator() {
        // RandomStringUtils.randomNumeric(10)
        // UUID.randomUUID().getLeastSignificantBits();

        long result = RandomUtils.nextLong(10000000, 200000000);

        return result;
    }

    /*  public List<Product> findProductsFromSuppliers(List<Supplier> listSupplier){
        List<Product> productsFound=new ArrayList<Product>();

        List<Product> products = mockProductsFromPortoAlegre(mockSuppliersPortoAlegre(mockSuppliersType()),mockDepartament() , mockProductCategory());

        Long[] productIds = DaoUtil.getIds(products);
        List<Long> asList = Arrays.asList(productIds);

        for (Product product : products) {
            Long productId = Long.valueOf(product.getSupplier());

            if(asList.contains(productId)){
                productsFound.add(product);
            }
        }

        return productsFound;
    }*/
}
