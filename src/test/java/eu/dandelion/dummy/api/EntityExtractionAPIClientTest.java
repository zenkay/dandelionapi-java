package eu.dandelion.dummy.api;

import eu.dandelion.dummy.rest.RestClient;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class EntityExtractionAPIClientTest {

    @Test
    public void testTypeAndContent() {

        try {

            EntityExtractionAPIClient eeClient = new EntityExtractionAPIClient(mock(RestClient.class), "url=http://www.engadget.com/2015/07/14/commodore-phone/", null, null);

            assertEquals("url", eeClient.getType());
            assertEquals("http://www.engadget.com/2015/07/14/commodore-phone/", eeClient.getContent());

            eeClient = new EntityExtractionAPIClient(mock(RestClient.class), "file=" + (EntityExtractionAPIClientTest.class.getClassLoader().getResource("twit-test").getPath()), null, null);

            assertEquals("text", eeClient.getType());
            assertEquals("Good programming & bugs reminds me of: \"The ones doing their job, doing what they were meant to do, are invisible. You'd never even know they were here. But the other ones, well, we hear about them all the time.\" ~ The Oracle/Matrix @amerkawar", eeClient.getContent());

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void testMakeItHappen() {

        try {

            RestClient restClient = mock(RestClient.class);
            stub(restClient.POST(anyString())).toReturn("{}");

            EntityExtractionAPIClient eeClient = new EntityExtractionAPIClient(restClient, "text=test", "foo", "bar");
            eeClient.makeItHappen();

            verify(restClient).POST(eq("https://api.dandelion.eu/datatxt/nex/v1/?text=test&social.hashtag=false&social.mention=false&$app_id=foo&$app_key=bar"));

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

}