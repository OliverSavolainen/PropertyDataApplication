package properties.example.accessingdatajpa;

import app.controllers.RentApartmentController;
import app.filters.AddressFilter;
import app.filters.OverallFilter;
import app.properties.RentApartment;
import app.repositories.RentApartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RentApartmentController.class)
public class RentApartmentControllerTests {
    @MockBean
    private RentApartmentRepository repository;
    @Autowired
    private RentApartmentController rentApartmentController;


    @BeforeEach
    public void setUp() {
        repository.save(new RentApartment("Riia 1, Kesklinn", (float) 34.6, "Uus", 1, 2, 341, 2000, "A", 4, ""));
        repository.save(new RentApartment("Riia 1, Annelinn", (float) 134.6, null, 0, 3, 56, 2020, "C", 1, ""));
        repository.save(new RentApartment());

    }


    @Test
    public void contextLoads() {
        assertThat(rentApartmentController).isNotNull();
    }

    @Test
    public void addressFilterTest() throws Exception {
        OverallFilter overallFilter = new OverallFilter();
        overallFilter.setAddressFilter(new AddressFilter("Kesklinn", "contains"));
        List<RentApartment> filteredApartments = rentApartmentController.getFilteredApartments(overallFilter);
        assertThat(filteredApartments.size()).isEqualTo(1);
        overallFilter.setAddressFilter(new AddressFilter("Riia 1, Annelinn", "equal"));
        filteredApartments = rentApartmentController.getFilteredApartments(overallFilter);
        assertThat(filteredApartments.size()).isEqualTo(1);
    }

}
