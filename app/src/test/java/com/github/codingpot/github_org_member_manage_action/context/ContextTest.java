package com.github.codingpot.github_org_member_manage_action.context;

import static com.github.stefanbirkner.systemlambda.SystemLambda.withEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.codingpot.github_org_member_manage_action.DaggerAppComponent;
import org.junit.jupiter.api.Test;

class ContextTest {

    @Test
    void getMembersFilePath() throws Exception {
        final Context context =
                withEnvironmentVariable("INPUT_MEMBERS_FILEPATH", "members.yaml")
                        .execute(() -> DaggerAppComponent.create().context());
        assertTrue(context.getMembersFilePath().isPresent());
        assertEquals("members.yaml", context.getMembersFilePath().get());
    }

    @Test
    void getMembersFileContent() throws Exception {
        final Context context =
                withEnvironmentVariable("INPUT_MEMBERS_FILECONTENT", "filecontent")
                        .execute(() -> DaggerAppComponent.create().context());
        assertTrue(context.getMembersFileContent().isPresent());
        assertEquals("filecontent", context.getMembersFileContent().get());
    }

    @Test
    void getGithubToken() throws Exception {
        final Context context =
                withEnvironmentVariable("INPUT_GH_TOKEN", "GITHUB_TOKEN")
                        .execute(() -> DaggerAppComponent.create().context());
        assertTrue(context.getGithubToken().isPresent());
        assertEquals("GITHUB_TOKEN", context.getGithubToken().get());
    }
}
