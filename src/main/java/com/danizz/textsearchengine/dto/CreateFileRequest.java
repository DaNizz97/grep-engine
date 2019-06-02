package com.danizz.textsearchengine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateFileRequest {
    private String filename;
    private Boolean isFile;
}
