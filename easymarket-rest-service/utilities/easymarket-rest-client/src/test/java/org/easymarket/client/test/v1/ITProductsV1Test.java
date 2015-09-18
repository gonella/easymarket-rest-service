package org.easymarket.client.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.easymarket.client.core.RestOptions;
import org.easymarket.client.test.ITCommon;
import org.easymarket.ti.edm.v1.ITBarcode;
import org.easymarket.ti.edm.v1.ITBarcodeType;
import org.easymarket.ti.edm.v1.ITProduct;
import org.easymarket.ti.edm.v1.ITProductCategory;
import org.easymarket.ti.edm.v1.ITSupplier;
import org.easymarket.ti.edm.v1.ITSupplierDepartment;
import org.easymarket.ti.edm.v1.ITSupplierType;
import org.easymarket.ti.edm.v1.param.ITProductRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ITProductsV1Test extends ITCommon {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Before
    public void preTest()
            throws IOException {
        cleanUpProduct();
        cleanUpSupplier();
        cleanUpSupplierDepartment();
    }
    @After
    public void posTest()
            throws IOException {
        cleanUpProduct();
        cleanUpSupplier();
        cleanUpSupplierDepartment();
    }
    @Test
    public void testCreateAndListAll()
            throws Exception {

        //Preparing SUPPLIER + DEPARTMENT
        createSupplierData();
        createSupplierDepartmentData();

        List<ITSupplier> suppliersFound = supplierClient.getList();
        assertTrue("There are no suppliers added.", suppliersFound.size() > 0);
        ITSupplier supplierFound = suppliersFound.get(0);

        ITSupplierDepartment department = new ITSupplierDepartment();
        department.setName("Department01");
        department.setSupplierType(ITSupplierType.MARKET.toString());
        department.setDescription("desc");
        ITSupplierDepartment createDepartment = createDepartment(department);

        // Mocking entity
        ITProduct obj01Expected = new ITProduct();
        ITBarcode barcode = new ITBarcode();
        String barCodeValuExpected = "98376373783111111140";
        barcode.setBarcode(barCodeValuExpected);
        barcode.setType(ITBarcodeType.CODE_128);
        obj01Expected.setBarcode(barcode);
        obj01Expected.setName(UUID.randomUUID().toString());
        obj01Expected.setDescription("test");
        obj01Expected.setCategory("cat01");
        obj01Expected.setDepartment(createDepartment);
        obj01Expected.setPrice(1.0);
        obj01Expected.setSupplier(supplierFound);

        //CHECKING POST
        // Creating entity in database
        createProduct(obj01Expected);

        //CHECKING GET ALL
        // List all products
        List<ITProduct> resultFound = productClient.getList();
        assertTrue("There is product created", resultFound.size() > 0);

        // Smart way :):)
        // final ImmutableList<Product> listExpected =
        // ImmutableList.of(obj01Expected);
        // assertThat(resultFound).containsAll(listExpected);

        //CHECKING GET
        ITProduct productExpected = resultFound.get(0);
        ITProduct productFound = getProduct(productExpected.getId());
        String URI = getUri(ENDPOINT_PRODUCTS, productExpected.getId());

        //Assert product properties
        assertEquals("The elements are differents", productExpected.getName(), productFound.getName());
        assertEquals(
                "Barcode from product found is different than expected",
                barCodeValuExpected,
                productFound.getBarcode().getBarcode());

        assertNotNull("There is no supplier associated to product", productFound.getSupplier());
        assertEquals("Wrong supplier associated to product", "Zaffari1", productFound.getSupplier().getName());

        //CHECKING DELETE
        ITProduct delete = productClient.delete(URI);
        Exception found = null;
        try {
            ITProduct productFoundCheckDelete = productClient.get(URI);
        } catch (Exception e) {
            found = e;
        }
        assertNotNull("No exception dispatched, check if the entity was removed", found);

    }

    @Test
    public void testCreateProductsAndFindDepartaments()
            throws JsonParseException, JsonMappingException, IOException {

        //############################
        //Creating SUPPLIER DEPARTMENT
        //############################
        List<ITSupplierDepartment> mockDepartament = MOCKDATA.mockDepartament();
        for (ITSupplierDepartment itSupplierDepartment : mockDepartament) {
            ITSupplierDepartment created = createDepartment(itSupplierDepartment);
            LOGGER.info("Department created :" + created.toString());
        }
        List<ITSupplierDepartment> listDepartmentCreated = supplierDepartmentClient.getList();
        assertEquals("Number of departaments expected are different than expected", 11, listDepartmentCreated.size());

        //############################
        //Creating SUPPLIER, only for state "RS".
        List<ITSupplier> allSupplier = createSupplierData();
        //FIXME - Introduce query param filter to database.
        //Filter all supplier from "RS"
        List<ITSupplier> allSupplierFromPortoAlegre = new ArrayList<ITSupplier>();
        for (ITSupplier supplier : allSupplier) {
            if ("RS".equals(supplier.getState())) {
                allSupplierFromPortoAlegre.add(supplier);
            }
        }
        //############################

        //FIXME - Two line below must be create as row in a table. ON GOING
        //IMPROVE - We could have different category based on supplier type.
        List<ITProductCategory> mockProductCategory = MOCKDATA.mockProductCategory();

        //############################
        //Generating product in supplier from Porto Alegre.
        List<ITProduct> mockProductsFromPortoAlegre = MOCKDATA
                .mockProductsFromPortoAlegre(allSupplierFromPortoAlegre, listDepartmentCreated, mockProductCategory);

        //Creating products
        for (ITProduct product : mockProductsFromPortoAlegre) {
            ITProduct createProduct = createProduct(product);
            LOGGER.info(createProduct.toString());
        }

        //Find supplier near by
        //Find products where supplier id in (supplier list id)

        //Department
        //DepartmentType

        //#####################################MAIN DEPARTMENT PRESENTED IN UI#####################################
        RestOptions restOptions = RestOptions.query("supplierType", ITSupplierType.MARKET.toString());
        ITSupplierDepartment[] resultFound = supplierDepartmentClient.get(ENDPOINT_SUPPLIER_DEPARTMENTS_TYPE,ITSupplierDepartment[].class,restOptions);

        assertNotNull("Departments were not created corretly.",resultFound);
        assertEquals("Number of departaments expected are different than expected", 11,resultFound.length);

        //######CUSTOMER SELECTS ONE DEPARTMENT, PRODUCTS NEAR BY HIM MUST BE PRESENT AS ITEMS.

        ITSupplierDepartment departmentSelected = resultFound[0];
        LOGGER.info("Department selected :{}",departmentSelected);

        //Customer GPS coordinates
        ITProductRequest productParam=new ITProductRequest();
        productParam.setGpsCoordinates(MOCKDATA.GPS_COORDINATE_GONELLA_HOME);
        productParam.setMaxLimitInMeter(2000);
        //FIXME - we should remove address attribute below, GPS coordinate will be enough to provide address. We need to add GPS finder funcionality in backend. So far we are passing address.
        productParam.setCity("Porto Alegre");
        productParam.setState("RS");
        productParam.setCountry("Brazil");
        productParam.setDepartmentId(departmentSelected.getId());

        //Double check to see how many supplier we have around.
        //NearByRequest reused from productRequest.
        ITSupplier[] supplierNearByCustomer = supplierClient.get(ENDPOINT_SUPPPLIERS_DISTANCE,ITSupplier[].class,productParam);
        for (ITSupplier supplier : supplierNearByCustomer) {
            LOGGER.info("Supplier near by:"+supplier.toString());
        }
        assertEquals("Number of supplier near to GPS coordinates is different than expected.", 5,supplierNearByCustomer.length);

        //Listing all products near by the customer based on gps coordinates and department selected.
        ITProduct[] productsNearByCustomer = productClient.get(ENDPOINT_PRODUCTS_NEAR_BY, ITProduct[].class, productParam);
        for (ITProduct product : productsNearByCustomer) {
            LOGGER.info("Products near by :"+product.toString());
        }

        assertEquals("Number of products near by GPS coordinates is different than expected.", 18,productsNearByCustomer.length);

        //List ITEM based on product found.
        //TODO - Create ItemResourceV1
        //All products found near by the customer must be returned as ITEMS.

    }
}
