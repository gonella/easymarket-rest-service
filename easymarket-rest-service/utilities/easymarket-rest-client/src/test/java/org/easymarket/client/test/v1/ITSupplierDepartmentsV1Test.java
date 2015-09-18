package org.easymarket.client.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.easymarket.client.test.ITCommon;
import org.easymarket.ti.edm.v1.ITSupplierDepartment;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ITSupplierDepartmentsV1Test extends ITCommon {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ITSupplierDepartmentsV1Test.class);

    @Before
    public void preTest() {

    }

    @Test
    public void testCreateAndListAllAndDelete()
            throws JsonParseException,
            JsonMappingException, IOException {

        // Mocking entity
        ITSupplierDepartment obj01Expected = new ITSupplierDepartment();
        obj01Expected.setName(UUID.randomUUID().toString());

        //CHECKING POST
        // Creating entity in database
        createDepartment(obj01Expected);

        //CHECKING GET ALL
        // List all Suppliers
        List<ITSupplierDepartment> resultFound = supplierDepartmentClient.getList();
        assertTrue("There is entity created", resultFound.size() > 0);
        // Smart way :):)
        // final ImmutableList<Supplier> listExpected =
        // ImmutableList.of(obj01Expected);
        // assertThat(resultFound).containsAll(listExpected);

        //CHECKING GET
        ITSupplierDepartment supplierExpected = resultFound.get(0);
        String URI = getUri(ENDPOINT_SUPPLIER_DEPARTMENTS ,supplierExpected.getId());
        ITSupplierDepartment SupplierFound = supplierDepartmentClient.get(URI);
        assertEquals(
                "The elements are differents",
                supplierExpected.getName(),
                SupplierFound.getName());

        //CHECKING DELETE
        ITSupplierDepartment delete = supplierClient.delete(URI);
        Exception found = null;
        try {
            ITSupplierDepartment SupplierFoundCheckDelete = supplierDepartmentClient.get(URI);
        } catch (Exception e) {
            found = e;
        }
        assertNotNull("No exception dispatched, check if the entity was removed", found);

    }


    @Test
    public void testCreateDepartmentAndProduct()
            throws JsonParseException,
            JsonMappingException, IOException {

        cleanUpSupplierDepartment();

        List<ITSupplierDepartment> mockDepartament = MOCKDATA.mockDepartament();
        for (ITSupplierDepartment itSupplierDepartment : mockDepartament) {
            createDepartment(itSupplierDepartment);
        }
        List<ITSupplierDepartment> listCreated = supplierDepartmentClient.getList();

        assertEquals("Number of departaments expected are different than expected", 11,listCreated.size());

    }
}
