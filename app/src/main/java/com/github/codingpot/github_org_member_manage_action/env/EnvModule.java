package com.github.codingpot.github_org_member_manage_action.env;

import static com.github.codingpot.github_org_member_manage_action.Constants.INPUT_GH_TOKEN;
import static com.github.codingpot.github_org_member_manage_action.Constants.INPUT_MEMBERS_FILEPATH;

import com.github.codingpot.github_org_member_manage_action.annotations.GitHubToken;
import com.github.codingpot.github_org_member_manage_action.annotations.MembersFilePath;
import com.github.codingpot.github_org_member_manage_action.producers.ProducersComponent;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/** EnvModule provides environment variables. */
@Module(subcomponents = ProducersComponent.class)
public class EnvModule {
    @Singleton
    @Provides
    @GitHubToken
    static String provideGitHubToken() {
        return withDefault(System.getenv(INPUT_GH_TOKEN), "");
    }

    @Singleton
    @Provides
    @MembersFilePath
    static String provideMembersFilePath() {
        return withDefault(System.getenv(INPUT_MEMBERS_FILEPATH), "members.yaml");
    }

    private static String withDefault(String env, String defaultValue) {
        if (env == null || env.isBlank()) {
            return defaultValue;
        }
        return env;
    }
}
