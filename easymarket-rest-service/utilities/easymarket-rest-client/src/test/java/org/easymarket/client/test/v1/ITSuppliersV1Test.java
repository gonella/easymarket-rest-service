package org.easymarket.client.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.easymarket.client.test.ITCommon;
import org.easymarket.ti.edm.v1.ITSupplier;
import org.easymarket.ti.edm.v1.param.ITSupplierRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ITSuppliersV1Test extends ITCommon {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ITSuppliersV1Test.class);

    @Before
    public void preTest() throws JsonParseException, JsonMappingException, IOException {
        //Delete all supplier
        cleanUpSupplier();
    }

    @After
    public void posTest() throws JsonParseException, JsonMappingException, IOException{
        cleanUpSupplier();
    }

    @Test
    public void testCreateAndListAllAndDelete()
            throws JsonParseException,
            JsonMappingException, IOException {

        // Mocking entity
        ITSupplier obj01Expected = new ITSupplier();
        obj01Expected.setName(UUID.randomUUID().toString());
        obj01Expected.setGpsCoordinate("-30.0267408,-51.1903654");
        obj01Expected.setSupplierType("Type01");

        //CHECKING POST
        // Creating entity in database
        supplierClient.create(obj01Expected);

        //CHECKING GET ALL
        // List all Suppliers
        List<ITSupplier> resultFound = supplierClient.getList();
        assertTrue("There is entity created", resultFound.size() > 0);
        // Smart way :):)
        // final ImmutableList<Supplier> listExpected =
        // ImmutableList.of(obj01Expected);
        // assertThat(resultFound).containsAll(listExpected);

        //CHECKING GET
        ITSupplier supplierExpected = resultFound.get(0);
        String URI = getUri(ENDPOINT_SUPPLIERS ,supplierExpected.getId());
        ITSupplier SupplierFound = supplierClient.get(URI);
        assertEquals(
                "The elements are differents",
                supplierExpected.getName(),
                SupplierFound.getName());

        //CHECKING DELETE
        ITSupplier delete = supplierClient.delete(URI);
        Exception found = null;
        try {
            ITSupplier SupplierFoundCheckDelete = supplierClient.get(URI);
        } catch (Exception e) {
            found = e;
        }
        assertNotNull("No exception dispatched, check if the entity was removed", found);

    }

    @Test
    public void testCreateSuppliersAndFindSupplierNearByMe()
            throws IOException {
        //Create suppplier data
        createSupplierData();

        ITSupplierRequest supplierRequest=new ITSupplierRequest();
        supplierRequest.setGpsCoordinates(MOCKDATA.GPS_COORDINATE_GONELLA_HOME);
        supplierRequest.setMaxLimitInMeter(2000);
        //FIXME - we should remove address attribute below, GPS coordinate will be enough to provide address. We need to add GPS finder funcionality in backend. So far we are passing address.
        supplierRequest.setCity("Porto Alegre");
        supplierRequest.setState("RS");
        supplierRequest.setCountry("Brazil");

        ITSupplier[] resultFound = supplierClient.get(ENDPOINT_SUPPPLIERS_DISTANCE,ITSupplier[].class,supplierRequest);
        for (ITSupplier supplier : resultFound) {
            LOGGER.info("Supplier :"+supplier.toString());
        }
        assertEquals("Number of supplier near to GPS coordinates is different than expected.", 5,resultFound.length);
    }
}
