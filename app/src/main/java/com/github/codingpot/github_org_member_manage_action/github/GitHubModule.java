package com.github.codingpot.github_org_member_manage_action.github;

import com.github.codingpot.github_org_member_manage_action.config.ConfigData;
import com.github.codingpot.github_org_member_manage_action.config.ConfigManager;
import com.github.codingpot.github_org_member_manage_action.context.Context;
import dagger.Module;
import dagger.Provides;
import lombok.SneakyThrows;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GitHubBuilder;

@Module
public class GitHubModule {
    @SneakyThrows
    @Provides
    GHOrganization provideGitHubOrganization(Context context, ConfigData configData) {
        String ghToken = context.getGithubToken();
        GitHubBuilder builder = new GitHubBuilder();
        if (!ghToken.isBlank()) {
            builder.withOAuthToken(ghToken);
        }
        return builder.build().getOrganization(configData.getOrgName());
    }

    @Provides
    static GitHubService provideGitHubService(GitHubServiceV3Impl service) {
        return service;
    }

    @SneakyThrows
    @Provides
    static ConfigData provideConfigData(ConfigManager manager) {
        return manager.readFromLocal();
    }
}
