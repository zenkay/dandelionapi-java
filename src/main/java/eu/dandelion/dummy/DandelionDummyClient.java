package eu.dandelion.dummy;

import eu.dandelion.dummy.api.APIClient;
import eu.dandelion.dummy.api.factory.DummyAPIFactory;
import eu.dandelion.dummy.common.Constant;
import eu.dandelion.dummy.common.DummyCLParser;

/**
 * Simple client for Dandelion API.
 * <br/>
 * Usage example:
 * <br/>
 * java -jar dandelion-dummy-client-1.0-SNAPSHOT.jar
 * <br/>
 * java -jar dandelion-dummy-client-1.0-SNAPSHOT.jar -ee "text=The doctor says an apple is better than an orange" -lang "en"
 */
public class DandelionDummyClient {

    public static void main(String[] args) {

        try {

            DummyCLParser cl = new DummyCLParser(args);
            Constant.OPTION api = cl.whichApi();

            if (api == null) {
                cl.printHelp();
            }

            APIClient cliApi = new DummyAPIFactory().getClientAPI(api, cl.getEvaluatedOption(), cl.getAppID(), cl.getAppKey());
            String response = cliApi.makeItHappen();

            System.out.println(response);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

}