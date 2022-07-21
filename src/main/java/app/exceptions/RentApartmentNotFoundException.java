package app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception for cases, when an apartment can't be found from the database by the ID
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such RentApartment")
public class RentApartmentNotFoundException extends Exception {
    public RentApartmentNotFoundException(Long id) {
    }
}
