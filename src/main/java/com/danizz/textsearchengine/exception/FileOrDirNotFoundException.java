package com.danizz.textsearchengine.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class FileOrDirNotFoundException extends RuntimeException {

    public FileOrDirNotFoundException(String message) {
        super(message);
    }
}

