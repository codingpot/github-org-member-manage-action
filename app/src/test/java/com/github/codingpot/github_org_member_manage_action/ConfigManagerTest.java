package com.github.codingpot.github_org_member_manage_action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.codingpot.github_org_member_manage_action.ConfigManager.ConfigDataDummy;
import com.github.codingpot.github_org_member_manage_action.context.Context;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

class ConfigManagerTest {

    private Path path;
    private ConfigManager configManager;
    private ConfigDataDummy dummy;

    @BeforeEach
    void setUp() throws IOException {
        path = Files.createTempFile("members", ".yaml");
        configManager =
                new ConfigManager(
                        new Context(Optional.of(path.toUri().getPath()), Optional.empty()));

        dummy = new ConfigDataDummy();
        dummy.setOrgName("orgName");
        dummy.setMembers(List.of("member1", "member2"));
        dummy.setOwners(List.of("owner1", "owner2"));

        Yaml yaml = new Yaml();
        yaml.dump(dummy, new PrintWriter(path.toFile()));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.delete(path);
    }

    @Test
    void readFromLocal() {
        assertTrue(configManager.readFromLocal().isPresent());
        assertEquals(dummy, configManager.readFromLocal().get());
    }
}
