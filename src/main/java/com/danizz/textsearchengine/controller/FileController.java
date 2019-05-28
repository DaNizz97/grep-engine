package com.danizz.textsearchengine.controller;

import com.danizz.textsearchengine.dto.SearchFileRequest;
import com.danizz.textsearchengine.dto.SearchTextRequest;
import com.danizz.textsearchengine.exception.FileOrDirNotFoundException;
import com.danizz.textsearchengine.service.FileSearchService;
import com.danizz.textsearchengine.service.Node;
import org.springframework.beans.factory.annotation.Autowired;
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

}
