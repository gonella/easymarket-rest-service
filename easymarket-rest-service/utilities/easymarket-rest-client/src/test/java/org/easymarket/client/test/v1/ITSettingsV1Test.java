package org.easymarket.client.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.easymarket.client.test.ITCommon;
import org.easymarket.ti.edm.v1.ITSetting;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ITSettingsV1Test extends ITCommon {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ITSettingsV1Test.class);

    @Before
    public void preTest() {

    }

    @Test
    public void testCreateAndListAll()
            throws JsonParseException,
            JsonMappingException, IOException {

        // Mocking entity
        ITSetting obj01Expected = new ITSetting();
        obj01Expected.setName(UUID.randomUUID().toString());

        //CHECKING POST
        // Creating entity in database
        settingClient.create(obj01Expected);

        //CHECKING GET ALL
        // List all Settings
        List<ITSetting> resultFound = settingClient.getList();
        assertTrue("There is entity created", resultFound.size() > 0);
        // Smart way :):)
        // final ImmutableList<Setting> listExpected =
        // ImmutableList.of(obj01Expected);
        // assertThat(resultFound).containsAll(listExpected);

        //CHECKING GET
        ITSetting SettingExpected = resultFound.get(0);
        String SettingId = SettingExpected.getId();
        String URI = getUri(ENDPOINT_SETTINGS,SettingId);
        ITSetting SettingFound = settingClient.get(URI);
        assertEquals(
                "The elements are differents",
                SettingExpected.getName(),
                SettingFound.getName());

        //CHECKING DELETE
        ITSetting delete = settingClient.delete(URI);
        Exception found = null;
        try {
            ITSetting SettingFoundCheckDelete = settingClient.get(URI);
        } catch (Exception e) {
            found = e;
        }
        assertNotNull("No exception dispatched, check if the entity was removed", found);

    }

}
