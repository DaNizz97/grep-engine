package com.danizz.textsearchengine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchTextRequest {
    private String fileName;
    private String pattern;
    private int maxLinesCount;
    private boolean revert;
}
