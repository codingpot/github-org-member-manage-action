package com.github.codingpot.github_org_member_manage_action.github;

import com.github.codingpot.github_org_member_manage_action.ConfigData;
import com.github.codingpot.github_org_member_manage_action.context.Context;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import java.util.Optional;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GitHubBuilder;

@Module
public class GitHubModule {
    @Provides
    Optional<GHOrganization> provideGitHubOrganization(Context context, ConfigData configData) {
        return context.getGithubToken()
                .flatMap(
                        ghToken -> {
                            try {
                                return Optional.of(
                                        new GitHubBuilder()
                                                .withOAuthToken(ghToken)
                                                .build()
                                                .getOrganization(configData.getOrgName()));
                            } catch (IOException e) {
                                return Optional.empty();
                            }
                        });
    }
}
