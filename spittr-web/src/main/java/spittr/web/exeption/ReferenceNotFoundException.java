package spittr.web.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,
        reason = "Reference Not Found")
public class ReferenceNotFoundException
        extends RuntimeException {
}