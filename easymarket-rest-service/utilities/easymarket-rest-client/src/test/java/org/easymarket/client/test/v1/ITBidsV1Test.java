package org.easymarket.client.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.easymarket.client.test.ITCommon;
import org.easymarket.ti.edm.v1.ITBid;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ITBidsV1Test extends ITCommon {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ITBidsV1Test.class);

    @Before
    public void preTest() {

    }

    @Test
    public void testCreateAndListAll()
            throws JsonParseException,
            JsonMappingException, IOException {

        // Mocking entity
        ITBid obj01Expected = new ITBid();
        obj01Expected.setName(UUID.randomUUID().toString());

        //CHECKING POST
        // Creating entity in database
        bidClient.create(obj01Expected);

        //CHECKING GET ALL
        // List all Bids
        List<ITBid> resultFound = bidClient.getList();
        assertTrue("There is entity created", resultFound.size() > 0);
        // Smart way :):)
        // final ImmutableList<Bid> listExpected =
        // ImmutableList.of(obj01Expected);
        // assertThat(resultFound).containsAll(listExpected);

        //CHECKING GET
        ITBid BidExpected = resultFound.get(0);
        String BidId = BidExpected.getId();
        String URI = getUri(ENDPOINT_BIDS,BidId);
        ITBid BidFound = bidClient.get(URI);
        assertEquals(
                "The elements are differents",
                BidExpected.getName(),
                BidFound.getName());

        //CHECKING DELETE
        ITBid delete = bidClient.delete(URI);
        Exception found = null;
        try {
            ITBid BidFoundCheckDelete = bidClient.get(URI);
        } catch (Exception e) {
            found = e;
        }
        assertNotNull("No exception dispatched, check if the entity was removed", found);

    }

}
