package com.danizz.textsearchengine;

import com.danizz.textsearchengine.service.FileSearchService;
import com.danizz.textsearchengine.service.FileSearchServiceImpl;
import com.danizz.textsearchengine.service.Node;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchServiceTest {

    private FileSearchService searchService;
    private List<File> files = new ArrayList<>();
    private List<File> dirs = new ArrayList<>();

    @BeforeAll
    void initSearchService() {
        searchService = new FileSearchServiceImpl();
    }

    @AfterAll
    void deleteFiles() {
        files.forEach(File::delete);
        dirs.forEach(File::delete);
    }

    @Test
    public void nodeCorrespondsToFile() throws IOException {
        File file = new File("_test.file1");
        addFileToRemove(file, file.createNewFile());
        Optional<Node> treeOfFilteredFiles = searchService.getTreeOfFilteredFiles(file, "");
        Node node = treeOfFilteredFiles.get();
        assertThat(node).isNotNull();
        assertThat(node.getName()).isEqualTo(file.getName());
    }

    @Test
    public void nodeNotContainsFileWithIncorrectPostfix() throws IOException {
        File file = new File("_test.file2");
        addFileToRemove(file, file.createNewFile());
        assertThat(searchService
                .getTreeOfFilteredFiles(file, "NOT_FILE_POSTFIX")
                .isPresent()
        ).isFalse();
    }

    @Test
    public void dirContainsFileAsChildren() throws IOException {
        File dir = new File("_TEST_DIR");
        addFileToRemove(dir, dir.mkdir());
        File file = new File(dir.getPath() + "/_TEST.FILE");
        addFileToRemove(file, file.createNewFile());

        Node node = searchService.getTreeOfFilteredFiles(dir, "").orElseThrow(RuntimeException::new);
        List<Node> children = node.getChildren();
        assertThat(children.size()).isGreaterThan(0);
        assertThat(children.get(0).getName()).isEqualTo(file.getName());
    }

    private void addFileToRemove(File file, boolean isCreated) {
        if (isCreated) {
            if (file.isDirectory()) {
                dirs.add(file);
            } else {
                files.add(file);
            }
        }
    }
}
