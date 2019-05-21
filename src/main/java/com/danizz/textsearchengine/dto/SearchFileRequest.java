package com.danizz.textsearchengine.dto;

import lombok.Data;

@Data
public class SearchFileRequest {
    private String path;
    private String postfix;
}
