package com.github.codingpot.github_org_member_manage_action.producers;

import com.github.codingpot.github_org_member_manage_action.ConfigData;
import com.github.codingpot.github_org_member_manage_action.ConfigManager;
import com.github.codingpot.github_org_member_manage_action.github.GitHubService;
import com.github.codingpot.github_org_member_manage_action.github.GitHubUser;
import com.github.codingpot.github_org_member_manage_action.status.StatusOr;
import dagger.producers.ProducerModule;
import dagger.producers.Produces;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

/** This module contains actual producer logics. Each producer runs asynchronously. */
@ProducerModule
public class ProducersModule {
    @SneakyThrows
    @Produces
    @LocalConfigData
    static ConfigData produceConfigDataFromLocal(ConfigManager manager) {
        return manager.readFromLocal();
    }

    @Produces
    @GitHubConfigData
    static ConfigData produceConfigDataFromGitHub(
            @LocalConfigData ConfigData localConfigData,
            @GitHubMembers Set<String> members,
            @GitHubAdmins Set<String> admins) {
        return ConfigData.builder()
                .orgName(localConfigData.getOrgName())
                .admins(admins)
                .members(members)
                .build();
    }

    @SneakyThrows
    @Produces
    @GitHubMembers
    static Set<String> produceMembers(GitHubService service) {
        return validateDataOrThrow(service.listMembers());
    }

    @SneakyThrows
    @Produces
    @GitHubAdmins
    static Set<String> produceAdmins(GitHubService service) {
        return validateDataOrThrow(service.listAdmins());
    }

    private static Set<String> validateDataOrThrow(StatusOr<List<GitHubUser>> members)
            throws Exception {
        if (members.hasError()) {
            throw members.toException();
        }
        return members.getData().stream().map(GitHubUser::getUsername).collect(Collectors.toSet());
    }
}
