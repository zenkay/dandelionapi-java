package eu.dandelion.dummy.rest;

import com.github.restdriver.clientdriver.ClientDriverRequest;
import com.github.restdriver.clientdriver.ClientDriverRule;
import static com.github.restdriver.clientdriver.RestClientDriver.*;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class DummyRestClientTest {

    private static final String SERVER_RESPONSE = "{\"time\":10,\"results\":[{\"type\":\"annotation\",\"time\":4,\"annotations\":[{\"start\":5,\"end\":23,\"spot\":\"programming \\u0026 bugs\",\"confidence\":0.7117,\"id\":37085,\"title\":\"Software bug\",\"uri\":\"http://en.wikipedia.org/wiki/Software_bug\",\"label\":\"Bug\",\"categories\":[\"Software bugs\"],\"types\":[]},{\"start\":219,\"end\":225,\"spot\":\"Oracle\",\"confidence\":0.7489,\"id\":22591,\"title\":\"Oracle Corporation\",\"uri\":\"http://en.wikipedia.org/wiki/Oracle_Corporation\",\"label\":\"Oracle Corporation\",\"categories\":[\"1977 establishments in California\",\"Companies based in Redwood Shores, California\",\"Companies established in 1977\",\"Computer companies of the United States\",\"CRM software companies\",\"ERP software companies\",\"Multinational companies headquartered in the United States\",\"Oracle Corporation\",\"American brands\",\"Oracle software\",\"Publicly traded companies of the United States\",\"Software companies based in the San Francisco Bay Area\",\"UML Partners\",\"Technology companies based in the San Francisco Bay Area\"],\"types\":[\"http://dbpedia.org/ontology/Agent\",\"http://dbpedia.org/ontology/Organisation\",\"http://dbpedia.org/ontology/Company\"]},{\"start\":226,\"end\":232,\"spot\":\"Matrix\",\"confidence\":0.6774,\"id\":30007,\"title\":\"The Matrix\",\"uri\":\"http://en.wikipedia.org/wiki/The_Matrix\",\"label\":\"The Matrix\",\"categories\":[\"1990s action films\",\"1990s science fiction films\",\"1999 films\",\"American action films\",\"American action thriller films\",\"American science fiction action films\",\"Best Film Empire Award winners\",\"Cyberpunk films\",\"Dystopian films\",\"English-language films\",\"Films about telepresence\",\"Films directed by The Wachowskis\",\"Films shot in Australia\",\"Films shot in Sydney\",\"Films that won the Best Sound Mixing Academy Award\",\"Films that won the Best Visual Effects Academy Award\",\"Films whose editor won the Best Film Editing Academy Award\",\"Gun fu films\",\"Kung fu films\",\"Martial arts science fiction films\",\"Post-apocalyptic films\",\"Silver Pictures films\",\"Screenplays by The Wachowskis\",\"The Matrix (franchise)\",\"United States National Film Registry films\",\"Village Roadshow Pictures films\",\"Warner Bros. films\"],\"types\":[\"http://dbpedia.org/ontology/Work\",\"http://dbpedia.org/ontology/Film\"]}],\"lang\":\"en\"},{\"type\":\"annotation\",\"time\":4,\"annotations\":[{\"start\":5,\"end\":23,\"spot\":\"programming \\u0026 bugs\",\"confidence\":0.7117,\"id\":37085,\"title\":\"Software bug\",\"uri\":\"http://en.wikipedia.org/wiki/Software_bug\",\"label\":\"Bug\",\"categories\":[\"Software bugs\"],\"types\":[]},{\"start\":219,\"end\":225,\"spot\":\"Oracle\",\"confidence\":0.7489,\"id\":22591,\"title\":\"Oracle Corporation\",\"uri\":\"http://en.wikipedia.org/wiki/Oracle_Corporation\",\"label\":\"Oracle Corporation\",\"categories\":[\"1977 establishments in California\",\"Companies based in Redwood Shores, California\",\"Companies established in 1977\",\"Computer companies of the United States\",\"CRM software companies\",\"ERP software companies\",\"Multinational companies headquartered in the United States\",\"Oracle Corporation\",\"American brands\",\"Oracle software\",\"Publicly traded companies of the United States\",\"Software companies based in the San Francisco Bay Area\",\"UML Partners\",\"Technology companies based in the San Francisco Bay Area\"],\"types\":[\"http://dbpedia.org/ontology/Agent\",\"http://dbpedia.org/ontology/Organisation\",\"http://dbpedia.org/ontology/Company\"]},{\"start\":226,\"end\":232,\"spot\":\"Matrix\",\"confidence\":0.6774,\"id\":30007,\"title\":\"The Matrix\",\"uri\":\"http://en.wikipedia.org/wiki/The_Matrix\",\"label\":\"The Matrix\",\"categories\":[\"1990s action films\",\"1990s science fiction films\",\"1999 films\",\"American action films\",\"American action thriller films\",\"American science fiction action films\",\"Best Film Empire Award winners\",\"Cyberpunk films\",\"Dystopian films\",\"English-language films\",\"Films about telepresence\",\"Films directed by The Wachowskis\",\"Films shot in Australia\",\"Films shot in Sydney\",\"Films that won the Best Sound Mixing Academy Award\",\"Films that won the Best Visual Effects Academy Award\",\"Films whose editor won the Best Film Editing Academy Award\",\"Gun fu films\",\"Kung fu films\",\"Martial arts science fiction films\",\"Post-apocalyptic films\",\"Silver Pictures films\",\"Screenplays by The Wachowskis\",\"The Matrix (franchise)\",\"United States National Film Registry films\",\"Village Roadshow Pictures films\",\"Warner Bros. films\"],\"types\":[\"http://dbpedia.org/ontology/Work\",\"http://dbpedia.org/ontology/Film\"]}],\"lang\":\"en\"}],\"timestamp\":\"2015-07-17T08:40:55.018\"}";

    @Rule
    public ClientDriverRule driver = new ClientDriverRule();

    @Test
    public void testPOST() {

        driver.addExpectation(
                onRequestTo("/datatxt/nex/v1/").withAnyParams().withMethod(ClientDriverRequest.Method.POST),
                giveResponse(SERVER_RESPONSE, "applicatino/json").withStatus(200)
        );

        try {

            String response  = new DummyRestClient().POST(driver.getBaseUrl() + "/datatxt/nex/v1/?text=Good+programming+%26+bugs+reminds+me+of%3A+%22The+ones+doing+their+job%2C+doing+what+they+were+meant+to+do%2C+are+invisible.+You%27d+never+even+know+they+were+here.+But+the+other+ones%2C+well%2C+we+hear+about+them+all+the+time.%22+%7E+The+Oracle%2FMatrix+%40amerkawar&lang=en&social.hashtag=false&social.mention=false&include=types,categories&$app_id=foo&$app_key=bar");
            assertEquals(SERVER_RESPONSE, response);

        } catch (IOException e) {
            fail(e.getMessage());
        }

    }

}