package com.danizz.textsearchengine.service;

import com.danizz.textsearchengine.dto.SearchTextRequest;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.util.Optional;

public interface FileSearchService {

    Optional<Node> getTreeOfFilteredFiles(File file, String postfix);

    StreamingResponseBody getMatchesLinesAsStream(SearchTextRequest request);

    Node getAllFilesTree(File file);

}
