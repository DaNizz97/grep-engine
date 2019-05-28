package com.danizz.textsearchengine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileOrDirNotFoundException extends RuntimeException {
    public FileOrDirNotFoundException() {
        super();
    }

    public FileOrDirNotFoundException(String message) {
        super(message);
    }
}
