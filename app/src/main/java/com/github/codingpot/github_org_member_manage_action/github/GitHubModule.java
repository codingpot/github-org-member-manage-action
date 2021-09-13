package com.github.codingpot.github_org_member_manage_action.github;

import com.github.codingpot.github_org_member_manage_action.config.ConfigData;
import com.github.codingpot.github_org_member_manage_action.config.ConfigManager;
import com.github.codingpot.github_org_member_manage_action.context.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Provider;
import javax.inject.Singleton;
import lombok.SneakyThrows;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

@Module
public class GitHubModule {
    @Provides
    static GitHubService provideGitHubService(
            Context context,
            Provider<FakeGitHubService> fakeGitHubServiceProvider,
            Provider<GitHubServiceV3Impl> serviceProvider) {
        if (context.isDryRun()) {
            return fakeGitHubServiceProvider.get();
        }
        return serviceProvider.get();
    }

    @SneakyThrows
    @Provides
    static ConfigData provideConfigData(ConfigManager manager) {
        return manager.readFromLocal();
    }

    @SneakyThrows
    @Provides
    @Singleton
    GHOrganization provideGitHubOrganization(GitHub github, ConfigData configData) {
        return github.getOrganization(configData.getOrgName());
    }

    @SneakyThrows
    @Provides
    @Singleton
    GitHub provideGitHub(Context context) {
        String ghToken = context.getGithubToken();
        GitHubBuilder builder = new GitHubBuilder();
        if (!ghToken.isBlank()) {
            builder.withOAuthToken(ghToken);
        }
        return builder.build();
    }
}
