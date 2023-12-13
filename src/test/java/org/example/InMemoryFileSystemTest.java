package org.example;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.lang.*;

public class InMemoryFileSystemTest {
    private InMemoryFileSystem fileSystem;

    @BeforeEach
    public void setUp() {
        fileSystem = new InMemoryFileSystem();
    }

    @Test
    public void testMkdir() {
        fileSystem.mkdir("test_directory");
        assertTrue(fileSystem.ls().contains("test_directory"));
    }

    @Test
    public void testCd() {
        fileSystem.mkdir("test_directory");
        fileSystem.cd("test_directory");
        assertEquals("/test_directory", fileSystem.getCurrentDirectory());
    }

    @Test
    public void testTouchAndCat() {
        fileSystem.touch("test_file.txt");
        fileSystem.echo("Hello, this is some text.", "test_file.txt");
        assertEquals("Hello, this is some text.", fileSystem.cat("test_file.txt"));
    }

    @Test
    public void testMv() {
        fileSystem.mkdir("source_directory");
        fileSystem.touch("source_file.txt");
        fileSystem.mv("source_file.txt", "destination_directory/");
        assertFalse(fileSystem.ls().contains("source_file.txt"));
        assertTrue(fileSystem.ls("destination_directory").contains("source_file.txt"));
    }

    @Test
    public void testCp() {
        fileSystem.mkdir("source_directory");
        fileSystem.touch("source_file.txt");
        fileSystem.cp("source_file.txt", "destination_directory/");
        assertTrue(fileSystem.ls().contains("source_file.txt"));
        assertTrue(fileSystem.ls("destination_directory").contains("source_file.txt"));
    }

    @Test
    public void testRm() {
        fileSystem.touch("test_file.txt");
        fileSystem.rm("test_file.txt");
        assertFalse(fileSystem.ls().contains("test_file.txt"));
    }

    public static void main(String[] args) {
        ls().toString(); // Error: void cannot be dereferenced
    }
}


