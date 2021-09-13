package com.github.codingpot.github_org_member_manage_action.producers;

import com.github.codingpot.github_org_member_manage_action.config.ConfigData;
import com.github.codingpot.github_org_member_manage_action.config.ConfigManager;
import com.github.codingpot.github_org_member_manage_action.config.Diff;
import com.github.codingpot.github_org_member_manage_action.config.DiffService;
import com.github.codingpot.github_org_member_manage_action.github.GitHubService;
import com.github.codingpot.github_org_member_manage_action.github.GitHubUser;
import com.github.codingpot.github_org_member_manage_action.status.Status;
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

    @Produces
    static Status execute(
            @AddMembersStatusInternal Status addMembersStatus,
            @AddAdminsStatusInternal Status addAdminsStatus) {
        return addMembersStatus.merge(addAdminsStatus);
    }

    @Produces
    @AddMembersStatusInternal
    static Status addMembers(GitHubService service, Diff diff) {
        service.addMembers(diff.getNewMembers());
        return Status.ok();
    }

    @Produces
    @AddAdminsStatusInternal
    static Status addAdmins(GitHubService service, Diff diff) {
        service.addMembers(diff.getNewAdmins());
        return Status.ok();
    }

    @Produces
    static Diff provideDiff(
            @LocalConfigData ConfigData fromLocal, @GitHubConfigData ConfigData fromGitHub) {
        return DiffService.diff(fromLocal, fromGitHub);
    }

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
            @GitHubAdmins Set<String> admins,
            @GitHubMembers Set<String> members) {
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
