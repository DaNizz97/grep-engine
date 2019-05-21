package com.danizz.textsearchengine.service;

import com.danizz.textsearchengine.dto.Node;

import java.io.File;

public interface FileSearchService {

    Node getTreeOfFilteredFiles(File file, String postfix);

}
