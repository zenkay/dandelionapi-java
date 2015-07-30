package eu.dandelion.dummy.api.factory;

import eu.dandelion.dummy.api.APIClient;
import eu.dandelion.dummy.api.EntityExtractionAPIClient;
import eu.dandelion.dummy.common.Constant;
import eu.dandelion.dummy.common.ConstantTest;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class DummyAPIFactoryTest {

    @Test
    public void testGetClientAPI() {

        Map<Constant.OPTION, String> opts = new HashMap<Constant.OPTION, String>();
        for (int i = 0; i < ConstantTest.VALID_OPTIONS.length; i++) {

            if ((i & 1) == 0) continue;

            opts.put(Constant.OPTION.valueOf(ConstantTest.VALID_OPTIONS[i - 1].substring(1)), ConstantTest.VALID_OPTIONS[i]);
        }

        DummyAPIFactory factory = new DummyAPIFactory();
        APIClient client = factory.getClientAPI(Constant.OPTION.ee, opts, null, null);

        assertTrue(client instanceof EntityExtractionAPIClient);

        EntityExtractionAPIClient eeClient = (EntityExtractionAPIClient) client;
        assertEquals("en", eeClient.getLang());

    }

}