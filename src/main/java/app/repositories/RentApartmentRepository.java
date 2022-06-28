package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.properties.RentApartment;

public interface RentApartmentRepository extends JpaRepository<RentApartment, Long> {

}
