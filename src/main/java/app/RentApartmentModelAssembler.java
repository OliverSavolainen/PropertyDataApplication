package app;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import app.controllers.RentApartmentController;
import app.properties.RentApartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class RentApartmentModelAssembler implements RepresentationModelAssembler<RentApartment, EntityModel<RentApartment>> {
    private static final Logger log = LoggerFactory.getLogger(PropertyDataApplication.class);

    @Override
    public EntityModel<RentApartment> toModel(RentApartment RentApartment) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<RentApartment> rentApartmentModel = null;
        try {
            rentApartmentModel = EntityModel.of(RentApartment,
                    linkTo(methodOn(RentApartmentController.class).one(RentApartment.getId())).withSelfRel(),
                    linkTo(methodOn(RentApartmentController.class).all()).withRel("RentApartments"));
        } catch (RentApartmentNotFoundException e) {
            log.error(e.getMessage());
        }

        return rentApartmentModel;
    }
}
