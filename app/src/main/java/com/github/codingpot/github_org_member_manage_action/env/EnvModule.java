package com.github.codingpot.github_org_member_manage_action.env;

import com.github.codingpot.github_org_member_manage_action.annotations.GitHubToken;
import com.github.codingpot.github_org_member_manage_action.annotations.MembersFileContent;
import com.github.codingpot.github_org_member_manage_action.annotations.MembersFilePath;
import dagger.Module;
import dagger.Provides;
import java.util.Optional;
import javax.inject.Singleton;

/** EnvModule provides environment variables. */
@Module
public class EnvModule {
    @Singleton
    @Provides
    @GitHubToken
    static Optional<String> provideGitHubToken() {
        return toOptional(System.getenv("INPUT_GH_TOKEN"));
    }

    @Singleton
    @Provides
    @MembersFilePath
    static Optional<String> provideMembersFilePath() {
        return toOptional(System.getenv("INPUT_MEMBERS_FILEPATH"));
    }

    @Singleton
    @Provides
    @MembersFileContent
    static Optional<String> provideMembersFileContent() {
        return toOptional(System.getenv("INPUT_MEMBERS_FILECONTENT"));
    }

    private static Optional<String> toOptional(String s) {
        if (s == null || s.isBlank()) {
            return Optional.empty();
        }
        return Optional.of(s);
    }
}
