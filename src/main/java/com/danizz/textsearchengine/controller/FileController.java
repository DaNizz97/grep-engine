package com.danizz.textsearchengine.controller;

import com.danizz.textsearchengine.dto.SearchFileRequest;
import com.danizz.textsearchengine.dto.SearchTextRequest;
import com.danizz.textsearchengine.exception.FileOrDirNotFoundException;
import com.danizz.textsearchengine.service.FileSearchService;
import com.danizz.textsearchengine.service.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;

@RestController
public class FileController {

    private FileSearchService searchService;

    @Autowired
    public FileController(FileSearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/files")
    public Node getFiles(@RequestBody SearchFileRequest request) {
        final File file = new File(request.getPath());
        if (!file.exists()) {
            throw new FileOrDirNotFoundException("File " + request.getPath() + " not exists!");
        }
        return searchService.getTreeOfFilteredFiles(file, request.getPostfix());
    }

    @PostMapping("/search")
    public ResponseEntity<StreamingResponseBody> findByFile(@RequestBody SearchTextRequest request) {
        StreamingResponseBody stream = searchService.getMatchesLinesAsStream(request);
        return new ResponseEntity<>(stream, HttpStatus.OK);
    }

}
