package app;

import app.controllers.RentApartmentController;
import app.properties.RentApartment;
import app.repositories.RentApartmentRepository;
import app.services.RentApartmentModelAssembler;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentApartmentController.class)
public class RentApartmentControllerTests {

    @MockBean
    RentApartmentRepository repository;
    @MockBean
    RentApartmentModelAssembler assembler;
    @Autowired
    MockMvc mvc;
    @Before
    public void setUp() {
        repository.save(new RentApartment());
    }

    @Test
    void basics() throws Exception {
        this.mvc.perform(get("/rentapartments")).andDo(print()).andExpect(status().isOk());

        //this.mvc.perform(get("/rentapartments/{id}", 1L)).andExpect(status().isOk());
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
                "       \"floor\": 5,\n" +
                "       \"comparison\": \"les\"\n" +
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
                "}")).andDo(print()).andExpect(status().isOk());
    }

}
