package com.danizz.textsearchengine.service;

import com.danizz.textsearchengine.dto.SearchTextRequest;
import com.danizz.textsearchengine.util.ReverseLineReader;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileSearchServiceImpl implements FileSearchService {

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
    public Optional<Node> getTreeOfFilteredFiles(File root, String postfix) {
        List<File> files = getFilteredFiles(new ArrayList<>(), root, postfix);
        System.out.println(files);
        if (files.size() == 1) {
            File file = files.get(0);
            if (file == root) {
                return Optional.of(new Node(file));
            } else {
                Node parent = new Node(root);
                parent.insert(file);
                return Optional.of(parent);
            }
        } else if (files.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(getTreeOfFilteredFiles(files, root));
        }

    }

    private Node getTreeOfFilteredFiles(List<File> files, File root) {
        Node node = new Node(root);
        for (File file : files) {
            node.insert(file);
        }
        return node;
    }

    @Override
    public StreamingResponseBody getMatchesLinesAsStream(SearchTextRequest request) {
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

    @Override
    public Node getAllFilesTree(File file) {
        Node node = new Node(file);
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                if (f.isFile()) {
                    node.addChild(new Node(f, true));
                } else {
                    node.addChild(getAllFilesTree(f));
                }
            }
        }
        return node;
    }


}
