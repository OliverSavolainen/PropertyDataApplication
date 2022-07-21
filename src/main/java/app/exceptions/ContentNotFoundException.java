package app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for cases, when request body doesn't have correct content to create an object
 */
@ResponseStatus(value= HttpStatus.NO_CONTENT, reason="No suitable content given")
public class ContentNotFoundException extends Exception {
    public ContentNotFoundException() {
    }
}
