package com.danizz.textsearchengine.dto;

import lombok.Data;

@Data
public class SearchTextRequest {
    private String fileName;
    private String pattern;
}