package com.github.codingpot.github_org_member_manage_action.env;

import static com.github.codingpot.github_org_member_manage_action.Constants.*;

import com.github.codingpot.github_org_member_manage_action.AppMode;
import com.github.codingpot.github_org_member_manage_action.Constants;
import com.github.codingpot.github_org_member_manage_action.annotations.DryRun;
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

    @Singleton
    @Provides
    static AppMode provideMode() {
        return AppMode.valueOf(withDefault(System.getenv(INPUT_MODE), "sync").toUpperCase());
    }

    @Singleton
    @Provides
    @DryRun
    static boolean provideDryRun() {
        final String env = System.getenv(Constants.INPUT_DRY_RUN);
        return env != null && !env.isBlank() && !env.equalsIgnoreCase("false");
    }

    private static String withDefault(String env, String defaultValue) {
        if (env == null || env.isBlank()) {
            return defaultValue;
        }
        return env;
    }
}
