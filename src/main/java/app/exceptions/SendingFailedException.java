package app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception for cases, when a notification sending went wrong
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Sending failed, check your credentials")
public class SendingFailedException extends Exception {
    public SendingFailedException() {
    }
}
