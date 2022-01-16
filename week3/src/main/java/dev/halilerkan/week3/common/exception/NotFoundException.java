package dev.halilerkan.week3.common.exception;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {

    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    private NotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static Supplier<NotFoundException> withSupplier() {
        return () -> new NotFoundException("Not Found");
    }

}
