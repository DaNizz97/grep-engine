package com.danizz.textsearchengine.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

    private Node convertFileToNode(File file) {
        Node child = new Node(file);
        while (true) {
            Node parent = new Node(child.getFile().getParentFile());
            if (parent.equals(this)) {
                break;
            }
            parent.addChild(child);
            child = parent;
        }
        return child;
    }

    public void insert(File file) {
        Node fileNode = convertFileToNode(file);
        if (!this.children.contains(fileNode)) {
            this.children.add(fileNode);
        } else {
            Node child = this.children.get(this.children.indexOf(fileNode));
            child.insert(fileNode.getChildren().get(0));
        }
    }

    private void insert(Node fileNode) {
        if (!this.children.contains(fileNode)) {
            this.children.add(fileNode);
        } else {
            Node child = this.children.get(this.children.indexOf(fileNode));
            child.insert(fileNode.getChildren().get(0).getFile());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return file.equals(node.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }
}
