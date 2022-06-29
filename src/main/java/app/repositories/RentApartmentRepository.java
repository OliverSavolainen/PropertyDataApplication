package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.properties.RentApartment;
import org.springframework.stereotype.Repository;

@Repository
public interface RentApartmentRepository extends JpaRepository<RentApartment, Long> {

}
