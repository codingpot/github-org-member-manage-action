package com.github.codingpot.github_org_member_manage_action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.codingpot.github_org_member_manage_action.context.Context;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigManagerTest {

    private static final char NEWLINE = '\n';
    private Path path;
    private ConfigManager configManager;

    @BeforeEach
    void setUp() throws IOException {
        path = Files.createTempFile("members", ".yaml");
        configManager =
                new ConfigManager(
                        new Context(Optional.of(path.toUri().getPath()), Optional.empty()));

        String yaml =
                "org_name: orgName"
                        + NEWLINE
                        + "owners:"
                        + NEWLINE
                        + "- owner1"
                        + NEWLINE
                        + "- owner2"
                        + NEWLINE
                        + "members:"
                        + NEWLINE
                        + "- member1"
                        + NEWLINE
                        + "- member2"
                        + NEWLINE;
        Files.writeString(path, yaml);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.delete(path);
    }

    @Test
    void readFromLocal() {
        Optional<ConfigData> configData = configManager.readFromLocal();
        assertTrue(configData.isPresent());
        assertEquals(
                ConfigData.builder()
                        .orgName("orgName")
                        .members(Set.of("member1", "member2"))
                        .owners(Set.of("owner1", "owner2"))
                        .build(),
                configData.get());
    }
}
