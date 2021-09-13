package com.github.codingpot.github_org_member_manage_action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.codingpot.github_org_member_manage_action.config.ConfigData;
import com.github.codingpot.github_org_member_manage_action.config.ConfigManager;
import com.github.codingpot.github_org_member_manage_action.context.Context;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
                        new Context(
                                path.toUri().getPath(), "FAKE_GITHUB_TOKEN", true, AppMode.SYNC));

        String yaml =
                "org_name: orgName"
                        + NEWLINE
                        + "admins:"
                        + NEWLINE
                        + "- admin1"
                        + NEWLINE
                        + "- admin2"
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
    void readFromLocal() throws IOException {
        ConfigData configData = configManager.readFromLocal();
        assertEquals(
                ConfigData.builder()
                        .orgName("orgName")
                        .admins(Set.of("admin1", "admin2"))
                        .members(Set.of("member1", "member2"))
                        .build(),
                configData);
    }
}
