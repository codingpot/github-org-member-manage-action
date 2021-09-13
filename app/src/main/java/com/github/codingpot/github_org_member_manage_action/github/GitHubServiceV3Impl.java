package com.github.codingpot.github_org_member_manage_action.github;

import com.github.codingpot.github_org_member_manage_action.annotations.DryRun;
import com.github.codingpot.github_org_member_manage_action.status.Status;
import com.github.codingpot.github_org_member_manage_action.status.StatusOr;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.inject.Inject;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

public class GitHubServiceV3Impl implements GitHubService {
    private final GHOrganization organization;
    private final boolean isDryRun;
    private final GitHub github;

    @Inject
    GitHubServiceV3Impl(GHOrganization organization, GitHub github, @DryRun boolean isDryRun) {
        this.github = github;
        this.organization = organization;
        this.isDryRun = isDryRun;
    }

    @Override
    public StatusOr<List<GitHubUser>> listAdmins() {
        return listMembersByRole(Role.ADMIN);
    }

    @Override
    public StatusOr<List<GitHubUser>> listMembers() {
        return listMembersByRole(Role.MEMBER);
    }

    @Override
    public Status addMembers(Iterable<String> newMembers) {
        StreamSupport.stream(newMembers.spliterator(), true)
                .forEach(
                        u -> {
                            try {
                                if (isDryRun) {
                                    System.out.printf("Adding a new member: %s%n", u);
                                    return;
                                }

                                final GHUser user = github.getUser(u);
                                organization.add(user, GHOrganization.Role.MEMBER);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
        return Status.ok();
    }

    @Override
    public Status addAdmins(Iterable<String> newAdmins) {
        StreamSupport.stream(newAdmins.spliterator(), true)
                .forEach(
                        u -> {
                            try {
                                if (isDryRun) {
                                    System.out.printf("Adding a new admin: %s%n", u);
                                    return;
                                }
                                final GHUser user = github.getUser(u);
                                organization.add(user, GHOrganization.Role.ADMIN);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
        return Status.ok();
    }

    private StatusOr<List<GitHubUser>> listMembersByRole(Role role) {
        try {
            return StatusOr.createOk(
                    organization.listMembersWithRole(role.toString()).toList().stream()
                            .map(u -> new GitHubUser(u.getLogin(), role))
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            return StatusOr.createError(e.getMessage());
        }
    }
}
