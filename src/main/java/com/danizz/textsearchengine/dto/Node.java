package com.danizz.textsearchengine.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class Node {
    private File file;
    private List<Node> children;
    @JsonIgnore
    private Node parent;

    public Node(File file) {
        this.file = file;
        this.children = new LinkedList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }
}
