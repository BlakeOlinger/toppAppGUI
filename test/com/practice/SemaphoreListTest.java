package com.practice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SemaphoreListTest {
    private Path testPath;

    @BeforeEach
    void setUp() throws IOException {
        testPath = Files.createFile(Paths.get("test.txt"));
    }

    @AfterEach
    void tearDown() throws IOException {
        if (Files.exists(testPath))
            Files.delete(testPath);
    }

    @Test
    void is_SW_action_Open() throws IOException {
        Files.writeString(testPath, "00");

        assertTrue(SemaphoreList.is_SW_action_Open(testPath));
    }
}