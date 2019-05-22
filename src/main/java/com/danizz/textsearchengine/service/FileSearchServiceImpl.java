package com.danizz.textsearchengine.service;

import com.danizz.textsearchengine.dto.Node;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileSearchServiceImpl implements FileSearchService {

    private List<File> getAllFiles(String root) {
        return getFilteredFiles(new ArrayList<>(), new File(root), "");
    }

    private List<File> getFilteredFiles(List<File> files, File root, String postfix) {
        if (root.isFile() && root.getPath().endsWith(postfix)) {
            files.add(root);
        } else if (root.isDirectory()) {
            for (File child : root.listFiles()) {
                if (child.isFile() && child.getPath().endsWith(postfix)) {
                    files.add(child);
                } else if (child.isDirectory()) {
                    getFilteredFiles(files, child, postfix);
                }
            }
        }
        return files;
    }

    @Override
    public Node getTreeOfFilteredFiles(File root, String postfix) {
        List<File> files = getFilteredFiles(new ArrayList<>(), root, postfix);
        if (files.size() == 1) {
            File file = files.get(0);
            if (file == root) {
                return new Node(file);
            } else {
                Node parent = new Node(root);
                parent.addChild(new Node(file));
                return parent;
            }
        } else {
            return getTreeOfFilteredFiles(files, root);
        }

    }

    private Node getTreeOfFilteredFiles(List<File> files, File root) {
        Node node = new Node(root);
        for (File file : files) {
            node.insert(file);
        }
        return node;
    }
}
