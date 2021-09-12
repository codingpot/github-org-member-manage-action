package com.github.codingpot.github_org_member_manage_action.context;

import static com.github.codingpot.github_org_member_manage_action.Constants.INPUT_GH_TOKEN;
import static com.github.codingpot.github_org_member_manage_action.Constants.INPUT_MEMBERS_FILEPATH;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.codingpot.github_org_member_manage_action.DaggerAppComponent;
import org.junit.jupiter.api.Test;

class ContextTest {

    @Test
    void getMembersFilePath() throws Exception {
        final Context context =
                withEnvironmentVariable(INPUT_MEMBERS_FILEPATH, "members2.yaml")
                        .and(INPUT_GH_TOKEN, "GITHUB_TOKEN")
                        .execute(() -> DaggerAppComponent.create().context());
        assertEquals("members2.yaml", context.getMembersFilePath());
    }

    @Test
    void getGithubToken() throws Exception {
        final Context context =
                withEnvironmentVariable(INPUT_GH_TOKEN, "GITHUB_TOKEN")
                        .and(INPUT_MEMBERS_FILEPATH, "members.yaml")
                        .execute(() -> DaggerAppComponent.create().context());
        assertEquals("GITHUB_TOKEN", context.getGithubToken());
    }
}
