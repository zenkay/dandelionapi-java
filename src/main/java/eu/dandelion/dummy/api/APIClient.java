package eu.dandelion.dummy.api;

import java.io.IOException;

/**
 * Defines Dandelion API common calls.
 */
public interface APIClient {

    /**
     * Built the url and perform the call to the API.
     *
     * @return              the API response
     * @throws IOException  if some problem occurs while calling the API
     */
    String makeItHappen() throws IOException;
}
