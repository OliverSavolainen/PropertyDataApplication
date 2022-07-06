package app;

import app.controllers.RentApartmentController;
import app.repositories.RentApartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @Test
    void basics() throws Exception {

        this.mvc.perform(get("/rentApartments")).andDo(print()).andExpect(status().isOk());
    }

}
