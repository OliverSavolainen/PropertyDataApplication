package app;

import app.controllers.RentApartmentController;
import app.properties.RentApartment;
import app.repositories.RentApartmentRepository;
import app.services.RentApartmentModelAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
public class RentApartmentControllerTests {
    @MockBean
    RentApartmentRepository repository;
    @MockBean
    RentApartmentModelAssembler assembler;
    @Autowired
    MockMvc mvc;

    @BeforeEach
    public void setUp() {
        repository.save(new RentApartment());
        System.out.println(repository.count() + " were found DGDSGDSGS");
    }

    @Test
    void basicGet() throws Exception {
        this.mvc.perform(get("/rentapartments")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void basicPost() throws Exception {
        this.mvc.perform(post("/rentapartments").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"address\": \"Väike-Turu tn 5, Kesklinn\",\n" +
                "    \"size\": 113.8,\n" +
                "    \"propertyCondition\": \"Heas korras\",\n" +
                "    \"bedrooms\": 2,\n" +
                "    \"rooms\": 3,\n" +
                "    \"price\": 1200.0,\n" +
                "    \"renovationYear\": 2008,\n" +
                "    \"energyLabel\": \"E\",\n" +
                "    \"floor\": 13,\n" +
                "    \"url\": null\n" +
                "}")).andDo(print()).andExpect(status().is(201));
    }

    @Test
    void getOne() throws Exception {
        this.mvc.perform(get("/rentapartments/1")).andExpect(status().isNotFound());
        this.mvc.perform(post("/rentapartments").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"id\": 1,\n" +
                "    \"address\": \"Väike-Turu tn 5, Kesklinn\",\n" +
                "    \"size\": 113.8,\n" +
                "    \"propertyCondition\": \"Heas korras\",\n" +
                "    \"bedrooms\": 2,\n" +
                "    \"rooms\": 3,\n" +
                "    \"price\": 1200.0,\n" +
                "    \"renovationYear\": 2008,\n" +
                "    \"energyLabel\": \"E\",\n" +
                "    \"floor\": 13,\n" +
                "    \"url\": null\n" +
                "}"));

        this.mvc.perform(get("/rentapartments/1")).andDo(print()).andExpect(status().isOk());

    }


    @Test
    void getFiltered() throws Exception {
        this.mvc.perform(get("/filtered").contentType(MediaType.APPLICATION_JSON).content("{  \"addressFilter\": {\n" +
                "       \"address\": null,\n" +
                "       \"comparison\": null\n" +
                "   },\n" +
                "    \"bedroomsFilter\": {\n" +
                "       \"amount\": null,\n" +
                "       \"comparison\": null\n" +
                "   },\n" +
                "    \"conditionFilter\": {\n" +
                "       \"condition\": null,\n" +
                "       \"comparison\": null\n" +
                "   },\n" +
                "    \"energyLabelFilter\": {\n" +
                "       \"label\": null,\n" +
                "       \"comparison\": null\n" +
                "   },   \n" +
                "   \"floorFilter\": {\n" +
                "       \"floor\": null,\n" +
                "       \"comparison\": null\n" +
                "   },\n" +
                "   \"priceFilter\": {\n" +
                "       \"amount\": null,\n" +
                "       \"comparison\": null\n" +
                "   },\n" +
                "   \"renovationYearFilter\": {\n" +
                "       \"year\": null,\n" +
                "       \"comparison\": null\n" +
                "   },\n" +
                "      \"roomsFilter\": {\n" +
                "       \"amount\": null,\n" +
                "       \"comparison\": null\n" +
                "   },\n" +
                "      \"sizeFilter\": {\n" +
                "       \"size\": null,\n" +
                "       \"comparison\": null\n" +
                "   }\n" +
                "}")).andExpect(status().isOk());

    }
    @Test
    void sendFiltered() throws Exception {
        this.mvc.perform(get("/filtered/send").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"from\": \"wrongemailaddressPDA@gmail.com\",\n" +
                "    \"recipient\": \"wrongemailaddressPDA@gmail.com\",\n" +
                "    \"password\": \"wrongpassword\",\n" +
                "    \"overallFilter\": {\n" +
                "        \"addressFilter\": {\n" +
                "            \"address\": null,\n" +
                "            \"comparison\": null\n" +
                "        },\n" +
                "        \"bedroomsFilter\": {\n" +
                "            \"amount\": 0,\n" +
                "            \"comparison\": \"more\" \n" +
                "        },\n" +
                "        \"conditionFilter\": {\n" +
                "            \"condition\": null,\n" +
                "            \"comparison\": null\n" +
                "        },\n" +
                "        \"energyLabelFilter\": {\n" +
                "            \"label\": null,\n" +
                "            \"comparison\": null\n" +
                "        },\n" +
                "        \"floorFilter\": {\n" +
                "            \"floor\": null,\n" +
                "            \"comparison\": null\n" +
                "        },\n" +
                "        \"priceFilter\": {\n" +
                "            \"amount\": 400,\n" +
                "            \"comparison\": \"less\"\n" +
                "        },\n" +
                "        \"renovationYearFilter\": {\n" +
                "            \"year\": null,\n" +
                "            \"comparison\": null\n" +
                "        },\n" +
                "        \"roomsFilter\": {\n" +
                "            \"amount\": null,\n" +
                "            \"comparison\": null\n" +
                "        },\n" +
                "        \"sizeFilter\": {\n" +
                "            \"size\": null,\n" +
                "            \"comparison\": null\n" +
                "        }\n" +
                "    }\n" +
                "}")).andExpect(status().isForbidden());

    }

}
