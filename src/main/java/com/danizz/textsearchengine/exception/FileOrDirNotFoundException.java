package com.danizz.textsearchengine.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
@NoArgsConstructor
public class FileOrDirNotFoundException extends RuntimeException {

    public FileOrDirNotFoundException(String message) {
        super(message);
    }
}

