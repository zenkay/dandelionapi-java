package eu.dandelion.dummy.rest;

import java.io.IOException;

/**
 * Defines rest client common methods.
 */
public interface RestClient {

    /**
     * Perform a GET call to the specified uri.
     *
     * @param uri           API uri
     * @return              API response
     * @throws IOException  if some problem occurs while calling the API
     */
    public String GET(String uri) throws IOException;

    /**
     * Perform a POST call to the specified uri.
     *
     * @param uri           API uri
     * @return              API response
     * @throws IOException  if some problem occurs while calling the API
     */
    public String POST(String uri) throws IOException;

}
