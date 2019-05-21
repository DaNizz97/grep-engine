package com.danizz.textsearchengine.controller;

import com.danizz.textsearchengine.dto.Node;
import com.danizz.textsearchengine.dto.SearchFileRequest;
import com.danizz.textsearchengine.dto.SearchTextRequest;
import com.danizz.textsearchengine.service.FileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
        return searchService.getTreeOfFilteredFiles(file, request.getPostfix());
    }

    @PostMapping("/search")
    public List<String> findByFile(@RequestBody SearchTextRequest request) throws IOException {
        return Files.lines(Paths.get(request.getFileName()))
                .filter(s -> s.contains(request.getPattern()))
                .collect(Collectors.toList());
    }
}
