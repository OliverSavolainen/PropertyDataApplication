package app;

import app.filters.*;
import app.properties.RentApartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilterLogicTest {

    private final List<RentApartment> rentApartments = new ArrayList<>();
    private final FilterLogic filterLogic = new FilterLogic(new OverallFilter(),rentApartments);

    @BeforeEach
    public void setUp() {
        filterLogic.setOverallFilter(new OverallFilter());
        RentApartment first = new RentApartment("Test",23,"Heas korras",1,2,400.0F,1999,"E",5,null);
        rentApartments.add(first);
    }

    @Test
    public void addressFilterTests() throws Exception {
        OverallFilter test = new OverallFilter();
        filterLogic.setOverallFilter(test);
        AddressFilter addressFilter1 = new AddressFilter("Te","contains");
        test.setAddressFilter(addressFilter1);
        assertThat(filterLogic.filterAll().size()).isEqualTo(1);
        AddressFilter addressFilter2 = new AddressFilter("Test","equal");
        test.setAddressFilter(addressFilter2);
        assertThat(filterLogic.filterAll().size()).isEqualTo(1);
        AddressFilter addressFilter3 = new AddressFilter("False","contains");
        test.setAddressFilter(addressFilter3);
        assertThat(filterLogic.filterAll().size()).isEqualTo(0);
    }
    @Test
    public void priceFilterTests() throws Exception {
        OverallFilter test = new OverallFilter();
        filterLogic.setOverallFilter(test);
        PriceFilter priceFilter1 = new PriceFilter(450.0F,"less");
        test.setPriceFilter(priceFilter1);
        assertThat(filterLogic.filterAll().size()).isEqualTo(1);
        PriceFilter priceFilter2 = new PriceFilter(400.0F,"equal");
        test.setPriceFilter(priceFilter2);
        assertThat(filterLogic.filterAll().size()).isEqualTo(1);
        PriceFilter priceFilter3 = new PriceFilter(200.0F,"more");
        test.setPriceFilter(priceFilter3);
        assertThat(filterLogic.filterAll().size()).isEqualTo(1);
        PriceFilter priceFilter4 = new PriceFilter(400.1F,"more");
        test.setPriceFilter(priceFilter4);
        assertThat(filterLogic.filterAll().size()).isEqualTo(0);
    }
    @Test
    public void roomsFilterTests() throws Exception {
        OverallFilter test = new OverallFilter();
        filterLogic.setOverallFilter(test);
        RoomsFilter roomsFilter1 = new RoomsFilter(3,"less");
        test.setRoomsFilter(roomsFilter1);
        assertThat(filterLogic.filterAll().size()).isEqualTo(1);
        RoomsFilter roomsFilter2 = new RoomsFilter(2,"equal");
        test.setRoomsFilter(roomsFilter2);
        assertThat(filterLogic.filterAll().size()).isEqualTo(1);
        RoomsFilter roomsFilter3 = new RoomsFilter(1,"more");
        test.setRoomsFilter(roomsFilter3);
        assertThat(filterLogic.filterAll().size()).isEqualTo(1);
        RoomsFilter roomsFilter4 = new RoomsFilter(3,"more");
        test.setRoomsFilter(roomsFilter4);
        assertThat(filterLogic.filterAll().size()).isEqualTo(0);
    }
    @Test
    public void multipleFiltersTests() throws Exception {
        OverallFilter test = new OverallFilter();
        filterLogic.setOverallFilter(test);
        RoomsFilter roomsFilter1 = new RoomsFilter(3,"less");
        test.setRoomsFilter(roomsFilter1);
        PriceFilter priceFilter1 = new PriceFilter(400.0F,"equal");
        test.setPriceFilter(priceFilter1);
        AddressFilter addressFilter1 = new AddressFilter("Test","contains");
        test.setAddressFilter(addressFilter1);
        assertThat(filterLogic.filterAll().size()).isEqualTo(1);

        RoomsFilter roomsFilter2 = new RoomsFilter(1,"more");
        test.setRoomsFilter(roomsFilter2);
        PriceFilter priceFilter2 = new PriceFilter(350.0F,"less");
        test.setPriceFilter(priceFilter2);
        AddressFilter addressFilter2 = new AddressFilter("Test","equals");
        test.setAddressFilter(addressFilter2);
        assertThat(filterLogic.filterAll().size()).isEqualTo(0);
    }




}
