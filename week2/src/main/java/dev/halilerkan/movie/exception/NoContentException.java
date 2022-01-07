package dev.halilerkan.movie.exception;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class NoContentException extends RuntimeException {
    private static final HttpStatus httpStatus = HttpStatus.NO_CONTENT;

    private NoContentException() {}

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static Supplier<NoContentException> buildWithSupplier() {
        return NoContentException::new;
    }
}