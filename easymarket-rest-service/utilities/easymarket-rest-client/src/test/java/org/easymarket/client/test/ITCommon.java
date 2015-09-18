package org.easymarket.client.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.easymarket.client.core.RestClient;
import org.easymarket.ti.edm.v1.ITBid;
import org.easymarket.ti.edm.v1.ITProduct;
import org.easymarket.ti.edm.v1.ITSetting;
import org.easymarket.ti.edm.v1.ITSupplier;
import org.easymarket.ti.edm.v1.ITSupplierDepartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ITCommon {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ITCommon.class);

    public final String ENDPOINT_BIDS = "/v1/bids";
    public final String ENDPOINT_PRODUCTS = "/v1/products";
    public final String ENDPOINT_PRODUCTS_NEAR_BY = ENDPOINT_PRODUCTS + "/nearby";
    public final String ENDPOINT_SETTINGS = "/v1/settings";
    public final String ENDPOINT_SUPPLIERS = "/v1/suppliers";
    public final String ENDPOINT_SUPPPLIERS_DISTANCE = ENDPOINT_SUPPLIERS + "/nearby";
    public final String ENDPOINT_SUPPLIER_DEPARTMENTS = "/v1/supplierdepartments";
    public final String ENDPOINT_SUPPLIER_DEPARTMENTS_TYPE = ENDPOINT_SUPPLIER_DEPARTMENTS + "/type";

    public RestClient<ITBid> bidClient = new RestClient<ITBid>(
            TestIntegrationEnvironment.EASYMARKET_URL,
            ENDPOINT_BIDS,
            ITBid.class,
            ITBid[].class);
    public RestClient<ITSupplier> supplierClient = new RestClient<ITSupplier>(
            TestIntegrationEnvironment.EASYMARKET_URL,
            ENDPOINT_SUPPLIERS,
            ITSupplier.class,
            ITSupplier[].class);
    public RestClient<ITProduct> productClient = new RestClient<ITProduct>(
            TestIntegrationEnvironment.EASYMARKET_URL,
            ENDPOINT_PRODUCTS,
            ITProduct.class,
            ITProduct[].class);
    public RestClient<ITSetting> settingClient = new RestClient<ITSetting>(
            TestIntegrationEnvironment.EASYMARKET_URL,
            ENDPOINT_SETTINGS,
            ITSetting.class,
            ITSetting[].class);
    public RestClient<ITSupplierDepartment> supplierDepartmentClient = new RestClient<ITSupplierDepartment>(
            TestIntegrationEnvironment.EASYMARKET_URL,
            ENDPOINT_SUPPLIER_DEPARTMENTS,
            ITSupplierDepartment.class,
            ITSupplierDepartment[].class);

    public ITMockData MOCKDATA = new ITMockData();

    public List<ITSupplier> createSupplierData()
            throws IOException {
        List<ITSupplier> supplierMock = MOCKDATA.mockSuppliersPortoAlegre();
        supplierMock.addAll(MOCKDATA.mockSuppliersNovoHamburgo());

        List<ITSupplier> listSupplierCreated = new ArrayList<ITSupplier>();
        for (ITSupplier supplier : supplierMock) {
            LOGGER.info("Creating supplier :" + supplier);
            ITSupplier supplierCreated = createSupplier(supplier);
            listSupplierCreated.add(supplierCreated);
        }
        return listSupplierCreated;
    }

    public List<ITSupplierDepartment> createSupplierDepartmentData()
            throws IOException {
        List<ITSupplierDepartment> data = MOCKDATA.mockDepartament();

        List<ITSupplierDepartment> listCreated = new ArrayList<ITSupplierDepartment>();
        for (ITSupplierDepartment toCreate : data) {
            LOGGER.info("Creating :" + toCreate);
            ITSupplierDepartment supplierCreated = createDepartment(toCreate);
            listCreated.add(supplierCreated);
        }
        return listCreated;
    }

    public ITSupplier createSupplier(ITSupplier supplier)
            throws IOException {
        ITSupplier supplierCreated = supplierClient.create(supplier);
        return supplierCreated;
    }

    public ITProduct createProduct(ITProduct obj01Expected)
            throws IOException {
        ITProduct productCreated = productClient.create(obj01Expected);
        return productCreated;
    }

    public ITProduct getProduct(String id)
            throws JsonParseException, JsonMappingException, IOException {
        String URI = getUri(ENDPOINT_PRODUCTS, id);
        ITProduct productFound = productClient.get(URI);
        return productFound;
    }

    public String getUri(String endpoint, String id) {
        String URI = endpoint + "/" + id;
        return URI;
    }

    public void cleanUpSupplier()
            throws JsonParseException, JsonMappingException, IOException {

        LOGGER.info("CleanUp Suppliers");

        List<ITSupplier> resultFound = supplierClient.getList();
        for (ITSupplier supplier : resultFound) {
            String URI = getUri(ENDPOINT_SUPPLIERS, supplier.getId());
            LOGGER.info("Deleting..." + URI);
            ITSupplier delete = supplierClient.delete(URI);
        }
        LOGGER.info("CleanUp Suppliers - DONE");
    }

    public void cleanUpSupplierDepartment()
            throws JsonParseException, JsonMappingException, IOException {

        LOGGER.info("CleanUp Supplier Department");

        List<ITSupplierDepartment> resultFound = supplierDepartmentClient.getList();
        for (ITSupplierDepartment supplier : resultFound) {
            String URI = getUri(ENDPOINT_SUPPLIER_DEPARTMENTS, supplier.getId());
            LOGGER.info("Deleting..." + URI);
            ITSupplierDepartment delete = supplierDepartmentClient.delete(URI);
        }
        LOGGER.info("CleanUp Supplier Department - DONE");
    }

    public void cleanUpProduct()
            throws JsonParseException, JsonMappingException, IOException {

        LOGGER.info("CleanUp Products");

        List<ITProduct> resultFound = productClient.getList();
        for (ITProduct found : resultFound) {
            String URI = getUri(ENDPOINT_PRODUCTS, found.getId());
            LOGGER.info("Deleting..." + URI);
            ITProduct delete = productClient.delete(URI);
        }
        LOGGER.info("CleanUp Products - DONE");
    }

    public ITSupplierDepartment createDepartment(ITSupplierDepartment obj01Expected)
            throws IOException {
        ITSupplierDepartment created = supplierDepartmentClient.create(obj01Expected);
        return created;
    }
}
