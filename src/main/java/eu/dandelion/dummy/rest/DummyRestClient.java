package eu.dandelion.dummy.rest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;

/**
 * Simple {@link RestClient} implementation .
 */
public class DummyRestClient implements RestClient {

    private CloseableHttpClient client;

    /**
     * Instantiate {@link org.apache.http.client.HttpClient}.
     */
    public DummyRestClient() {
        client = HttpClients.createMinimal();
    }

    public String GET(String uri) throws IOException {
        return execute(new HttpGet(uri));
    }

    public String POST(String uri) throws IOException {
        return execute(new HttpPost(uri));
    }

    private String execute(HttpUriRequest req) throws IOException {

        Date start = new Date();
        CloseableHttpResponse response = client.execute(req);

        System.out.println(req + " returned " + response.getStatusLine().getStatusCode() + " in " + (new Date().getTime() - start.getTime()) + "ms");

        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

}