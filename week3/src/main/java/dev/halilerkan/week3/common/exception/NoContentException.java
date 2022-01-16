package dev.halilerkan.week3.common.exception;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class NoContentException extends RuntimeException {

    private static final HttpStatus httpStatus = HttpStatus.NO_CONTENT;

    private NoContentException() {
        super();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static Supplier<NoContentException> withSupplier() {
        return NoContentException::new;
    }

}