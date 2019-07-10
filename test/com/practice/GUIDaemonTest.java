package com.practice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GUIDaemonTest {
    private static Path testPath010;

    @BeforeAll
    static void setUp() throws IOException {
        testPath010 = Files.createFile(Paths.get("testPath010.txt"));

        Files.writeString(testPath010, "010");
    }

    @AfterAll
    static void tearDown() throws IOException {
        if (Files.exists(testPath010))
            Files.delete(testPath010);
    }

    @Test
    void return_true_for_0_in_third_digit() {
        var daemon = new GUIDaemon();

        daemon.checkUpdateState(testPath010);

        assertTrue(Config.isUpdate);
    }
}