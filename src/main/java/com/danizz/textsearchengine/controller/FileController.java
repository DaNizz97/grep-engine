package com.danizz.textsearchengine.controller;

import com.danizz.textsearchengine.ReverseLineReader;
import com.danizz.textsearchengine.dto.Node;
import com.danizz.textsearchengine.dto.SearchFileRequest;
import com.danizz.textsearchengine.dto.SearchTextRequest;
import com.danizz.textsearchengine.service.FileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

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
    public StreamingResponseBody findByFile(@RequestBody SearchTextRequest request) {
        //TODO: Move method implementation into service
        return out -> {
            ReverseLineReader reader = new ReverseLineReader(new File(request.getFileName()));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < request.getMaxLinesCount()) {
                if (line.contains(request.getPattern())) {
                    out.write((line + "\n").getBytes());
                    out.flush();
                    i++;
                }
            }
        };
    }

//    @GetMapping("/test")
//    public StreamingResponseBody handleRequest() {
//        return out -> {
//            ReverseLineReader reader = new ReverseLineReader(new File("/home/da-nizz/IdeaProjects/textsearchengine/src/main/java/com/danizz/file.txt"));
//            String line;
//            int i = 0;
//            while ((line = reader.readLine()) != null) {
//                if (line.contains("v")) {
//                    out.write((line + "\n").getBytes());
//                    out.flush();
//                }
//            }
//        };
//    }
}
