package com.github.codingpot.github_org_member_manage_action.github;

import com.github.codingpot.github_org_member_manage_action.status.Status;
import com.github.codingpot.github_org_member_manage_action.status.StatusOr;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/** Instead of sending requests, it just prints to stdout. */
public class FakeGitHubService implements GitHubService {

    @Getter @Setter private List<GitHubUser> admins;
    @Getter @Setter private List<GitHubUser> members;

    @Inject
    FakeGitHubService() {}

    @Override
    public StatusOr<List<GitHubUser>> listAdmins() {
        return StatusOr.createOk(admins);
    }

    @Override
    public StatusOr<List<GitHubUser>> listMembers() {
        return StatusOr.createOk(members);
    }

    @Override
    public Status addMembers(Iterable<String> newMembers) {
        System.out.println("Adding members = " + newMembers);
        this.members.addAll(
                StreamSupport.stream(newMembers.spliterator(), false)
                        .map(m -> new GitHubUser(m, Role.MEMBER))
                        .collect(Collectors.toList()));
        return Status.ok();
    }

    @Override
    public Status addAdmins(Iterable<String> newAdmins) {
        System.out.println("Adding admins = " + newAdmins);
        this.members.addAll(
                StreamSupport.stream(newAdmins.spliterator(), false)
                        .map(m -> new GitHubUser(m, Role.ADMIN))
                        .collect(Collectors.toList()));
        return Status.ok();
    }
}
