package app.controllers;

import app.exceptions.ContentNotFoundException;
import app.exceptions.RentApartmentNotFoundException;
import app.filters.FilterLogic;
import app.filters.OverallFilter;
import app.notifications.SendEmail;
import app.properties.RentApartment;
import app.repositories.RentApartmentRepository;
import app.services.RentApartmentModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * REST controller that handles showing all, 1 selected or filtered apartments, also has methods for putting in a new entity to the repository and sending an email notification
 */
@RestController
public class RentApartmentController {
    @Autowired
    private RentApartmentRepository repository;
    @Autowired
    private RentApartmentModelAssembler assembler;



    @GetMapping("/rentapartments")
    public CollectionModel<EntityModel<RentApartment>> all() {
        List<EntityModel<RentApartment>> rentApartments = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(rentApartments, //
                linkTo(methodOn(RentApartmentController.class).all()).withSelfRel());
    }

    @GetMapping("/rentapartments/{id}")
    public EntityModel<RentApartment> one(@PathVariable Long id) throws RentApartmentNotFoundException {
        RentApartment rentApartment = repository.findById(id) //
                .orElseThrow(() -> new RentApartmentNotFoundException(id));

        return assembler.toModel(rentApartment);
    }

    @PostMapping("/rentapartments")
    ResponseEntity<EntityModel<RentApartment>> newRentApartment(@RequestBody RentApartment rentApartment) throws RentApartmentNotFoundException, ContentNotFoundException {
        if (rentApartment != null) {
            repository.save(rentApartment);

            return ResponseEntity //
                    .created(linkTo(methodOn(RentApartmentController.class).one(rentApartment.getId())).toUri()) //
                    .body(assembler.toModel(rentApartment));
        }
        throw new ContentNotFoundException();
    }


    @GetMapping(path = "/filtered")
    public CollectionModel<EntityModel<RentApartment>> getFilteredApartments(@RequestBody OverallFilter overallFilter) throws Exception {
        if (overallFilter == null) return all();
        FilterLogic filterLogic = new FilterLogic(overallFilter, repository.findAll());

        List<EntityModel<RentApartment>> filtered = filterLogic.filterAll().stream() //
                .map(assembler::toModel).toList();

        return CollectionModel.of(filtered);
    }

    @GetMapping(path = "/filtered/send")
    public CollectionModel<EntityModel<RentApartment>> sendFilteredApartments(@RequestBody SendEmail sendEmail) throws Exception {
        if (sendEmail == null) return all();

        List<RentApartment> suitableApartments = sendEmail.sendNotification(repository.findAll());
        List<EntityModel<RentApartment>> filtered = suitableApartments.stream() //
                .map(assembler::toModel).toList();

        return CollectionModel.of(filtered);
    }

}

