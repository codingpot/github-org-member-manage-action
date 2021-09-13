package com.github.codingpot.github_org_member_manage_action.config;

import com.github.codingpot.github_org_member_manage_action.github.GitHubService;
import com.github.codingpot.github_org_member_manage_action.status.Status;
import com.google.common.collect.Sets;
import java.util.Collections;

/** DiffService compares local ConfigData and remote ConfigData and returns Diff. */
public class DiffService {

    private final GitHubService gitHubService;

    DiffService(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    static Diff diff(ConfigData local, ConfigData remote) {
        return Diff.builder()
                .newAdmins(Sets.difference(local.getAdmins(), remote.getAdmins()))
                .newMembers(Sets.difference(local.getMembers(), remote.getMembers()))
                .membersToBeDeleted(Collections.emptySet())
                .build();
    }

    void execute(Diff diff) throws Exception {
        Status status = gitHubService.addAdmins(diff.getNewAdmins());
        if (status.hasError()) {
            throw status.toException();
        }
        status = gitHubService.addMembers(diff.getNewMembers());
        if (status.hasError()) {
            throw status.toException();
        }
    }
}
