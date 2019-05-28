package com.danizz.textsearchengine.service;

import com.danizz.textsearchengine.dto.SearchTextRequest;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;

public interface FileSearchService {

    Node getTreeOfFilteredFiles(File file, String postfix);

    StreamingResponseBody getMatchesLinesAsStream(SearchTextRequest request);

}
