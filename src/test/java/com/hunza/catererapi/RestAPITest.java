package com.hunza.catererapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hunza.catererapi.dto.response.APIResponse;
import com.hunza.catererapi.model.CatererDocument;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestAPITest {
    /*@LocalServerPort
    private int port;*/
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestRestTemplate restTemplate;

    private CatererDocument catererDocument;


    @BeforeEach
    public void getCatererDocumentObj(){
        String catererJson = "{\n" +
                "  \"capacity\": {\n" +
                "    \"maximum\": 100,\n" +
                "    \"minimum\": 1000\n" +
                "  },\n" +
                "  \"contact\": {\n" +
                "    \"emailAddress\": \"test@unit.com\",\n" +
                "    \"mobileNumber\": \"9876543210\",\n" +
                "    \"phoneNumber\": \"9871234\"\n" +
                "  },\n" +
                "  \"location\": {\n" +
                "    \"city\": \"Spring\",\n" +
                "    \"postalCode\": \"234\",\n" +
                "    \"street\": \"Java\"\n" +
                "  },\n" +
                "  \"name\": \"xxxxxxxxJAVAUnitTestxxxxxxxxx\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();

        try {
            this.catererDocument = mapper.readValue(catererJson, CatererDocument.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addCatererNegative() throws Exception {
        logger.info("testing add caterer negative  api");
        logger.info("catererDocument: {}", catererDocument);
        APIResponse apiResponse = restTemplate.postForObject("/add", this.catererDocument, APIResponse.class);
        assertThat(apiResponse).hasFieldOrPropertyWithValue("status","API_400");
    }

    @Test
    public void addCatererPositive() throws Exception {
        logger.info("testing add positive caterer api");

        // CatererDocument catererDocument = getCatererDocumentObj();
        catererDocument.getCapacity().setMinimum(10);

        logger.info("adding: {}", catererDocument);

        APIResponse apiResponse = restTemplate.postForObject("/add", catererDocument, APIResponse.class);
        assertThat(apiResponse).hasFieldOrPropertyWithValue("status","API_201");
    }

    @Test
    public void searchAPI() throws Exception {
        logger.info("testing search api");
        assertThat(this.restTemplate.getForObject("/search",
                APIResponse.class)).hasFieldOrPropertyWithValue("status","API_200");
    }

    @Test
    public void getByNameOrIdCaterer() throws Exception {
        logger.info("testing search api");
        assertThat(this.restTemplate.getForObject("/get/"+this.catererDocument.getName(),
                APIResponse.class)).hasFieldOrPropertyWithValue("status","API_200");
    }

    @AfterAll
    public void deleteCaterer() {
        logger.info("removing test data api");
                this.restTemplate.delete("/delete/"+this.catererDocument.getName());
    }
}
