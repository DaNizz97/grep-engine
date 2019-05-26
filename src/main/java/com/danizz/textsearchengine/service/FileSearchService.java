package com.danizz.textsearchengine.service;

import java.io.File;

public interface FileSearchService {

    Node getTreeOfFilteredFiles(File file, String postfix);

}
