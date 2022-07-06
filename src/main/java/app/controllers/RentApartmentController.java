package app.controllers;

import app.RentApartmentModelAssembler;
import app.RentApartmentNotFoundException;
import app.filters.*;
import app.properties.RentApartment;
import app.repositories.RentApartmentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RentApartmentController {
    private final RentApartmentRepository repository;
    private final RentApartmentModelAssembler assembler;

    public RentApartmentController(RentApartmentRepository repository, RentApartmentModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/rentApartments")
    public CollectionModel<EntityModel<RentApartment>> all() {
        List<EntityModel<RentApartment>> rentApartments = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(rentApartments, //
                linkTo(methodOn(RentApartmentController.class).all()).withSelfRel());
    }

    @GetMapping("/rentApartments/{id}")
    public EntityModel<RentApartment> one(@PathVariable Long id) throws RentApartmentNotFoundException {
        RentApartment rentApartment = repository.findById(id) //
                .orElseThrow(() -> new RentApartmentNotFoundException(id));

        return assembler.toModel(rentApartment);
    }

    @PostMapping("/rentApartments")
    ResponseEntity<EntityModel<RentApartment>> newRentApartment(@RequestBody RentApartment rentApartment) throws RentApartmentNotFoundException {
        RentApartment newRentApartment = repository.save(rentApartment);

        return ResponseEntity //
                .created(linkTo(methodOn(RentApartmentController.class).one(newRentApartment.getId())).toUri()) //
                .body(assembler.toModel(newRentApartment));
    }


    @GetMapping(path = "/filtered")
    public CollectionModel<EntityModel<RentApartment>> getFilteredApartments(@RequestBody OverallFilter overallFilter) throws Exception {
        List<RentApartment> currentList = repository.findAll();
        integerValueFilter(currentList, overallFilter.getFloorFilter(), "floor");
        stringValueFilter(currentList, overallFilter.getAddressFilter(), "address");
        stringValueFilter(currentList, overallFilter.getEnergyLabelFilter(), "energy");
        floatValueFilter(currentList, overallFilter.getSizeFilter(), "size");
        floatValueFilter(currentList, overallFilter.getPriceFilter(), "price");
        integerValueFilter(currentList, overallFilter.getRoomsFilter(), "rooms");
        integerValueFilter(currentList, overallFilter.getBedroomsFilter(), "bedrooms");
        stringValueFilter(currentList, overallFilter.getConditionFilter(), "condition");
        integerValueFilter(currentList, overallFilter.getRenovationYearFilter(), "year");

        List<EntityModel<RentApartment>> filtered = currentList.stream() //
                .map(assembler::toModel).toList();

        return CollectionModel.of(filtered);
    }

    private void floatValueFilter(List<RentApartment> currentList, FilterPart filterPart, String comparable) throws Exception {
        if (filterPart != null) {
            switch (filterPart.getComparison()) {
                case "more" -> {
                    for (int i = 0; i < currentList.size(); i++) {
                        Float value = getComparatorFloat(currentList.get(i), comparable);
                        if (value == null || value <= (Float) filterPart.getValue()) {
                            RentApartment removed = currentList.remove(i);
                        }
                    }
                }
                case "less" -> {
                    for (int i = 0; i < currentList.size(); i++) {
                        Float value = getComparatorFloat(currentList.get(i), comparable);
                        if (value == null || value >= (Float) filterPart.getValue()) {
                            RentApartment removed = currentList.remove(i);
                        }
                    }
                }
                case "equal" -> {
                    for (int i = 0; i < currentList.size(); i++) {
                        Float value = getComparatorFloat(currentList.get(i), comparable);
                        if (value == null || !Objects.equals(value, filterPart.getValue())) {
                            RentApartment removed = currentList.remove(i);
                        }
                    }
                }
                default -> {
                }
            }
        }
    }

    private void stringValueFilter(List<RentApartment> currentList, FilterPart filterPart, String comparable) throws Exception {
        if (filterPart != null) {
            switch (filterPart.getComparison()) {
                case "contains" -> {
                    for (int i = 0; i < currentList.size(); i++) {
                        String value = getComparatorString(currentList.get(i), comparable);
                        String apartmentsTextValue = (String) filterPart.getValue();
                        if (value == null || !apartmentsTextValue.contains(value)) {
                            RentApartment removed = currentList.remove(i);
                        }
                    }
                }
                case "equal" -> {
                    for (int i = 0; i < currentList.size(); i++) {
                        String value = getComparatorString(currentList.get(i), comparable);
                        if (value == null || !Objects.equals(value, filterPart.getValue())) {
                            RentApartment removed = currentList.remove(i);
                        }
                    }
                }
                default -> {
                }
            }
        }
    }

    private void integerValueFilter(List<RentApartment> currentList, FilterPart filterPart, String comparable) throws Exception {
        if (filterPart != null) {
            switch (filterPart.getComparison()) {
                case "more" -> {
                    for (int i = 0; i < currentList.size(); i++) {
                        Integer value = getComparatorInteger(currentList.get(i), comparable);
                        if (value == null || value <= (Integer) filterPart.getValue()) {
                            RentApartment removed = currentList.remove(i);
                        }
                    }
                }
                case "less" -> {
                    for (int i = 0; i < currentList.size(); i++) {
                        Integer value = getComparatorInteger(currentList.get(i), comparable);
                        if (value == null || value >= (Integer) filterPart.getValue()) {
                            RentApartment removed = currentList.remove(i);
                        }
                    }
                }
                case "equal" -> {
                    for (int i = 0; i < currentList.size(); i++) {
                        Integer value = getComparatorInteger(currentList.get(i), comparable);
                        if (value == null || !Objects.equals(value, filterPart.getValue())) {
                            RentApartment removed = currentList.remove(i);
                        }
                    }
                }
                default -> {
                }
            }
        }
    }

    private Integer getComparatorInteger(RentApartment rentApartment, String comparable) throws Exception {
        switch (comparable) {
            case "rooms" -> {
                return rentApartment.getRooms();
            }
            case "bedrooms" -> {
                return rentApartment.getBedrooms();
            }
            case "year" -> {
                return rentApartment.getRenovationYear();
            }
            case "floor" -> {
                return rentApartment.getFloor();
            }
        }
        throw new Exception("Wrong comparable");
    }

    private String getComparatorString(RentApartment rentApartment, String comparable) throws Exception {
        switch (comparable) {
            case "address" -> {
                return rentApartment.getAddress();
            }
            case "condition" -> {
                return rentApartment.getPropertyCondition();
            }
            case "energy" -> {
                return rentApartment.getEnergyLabel();
            }
        }
        throw new Exception("Wrong comparable");
    }

    private Float getComparatorFloat(RentApartment rentApartment, String comparable) throws Exception {
        switch (comparable) {
            case "price" -> {
                return rentApartment.getPrice();
            }
            case "size" -> {
                return rentApartment.getSize();
            }
        }
        throw new Exception("Wrong comparable");
    }


}

