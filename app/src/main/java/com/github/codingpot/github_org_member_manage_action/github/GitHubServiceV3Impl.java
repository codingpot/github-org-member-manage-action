package com.github.codingpot.github_org_member_manage_action.github;

import com.github.codingpot.github_org_member_manage_action.status.StatusOr;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.kohsuke.github.GHOrganization;

public class GitHubServiceV3Impl implements GitHubService {
    private final GHOrganization organization;

    @Inject
    GitHubServiceV3Impl(GHOrganization organization) {
        this.organization = organization;
    }

    @Override
    public StatusOr<List<GitHubUser>> listAdmins() {
        return listMembersByRole(Role.ADMIN);
    }

    @Override
    public StatusOr<List<GitHubUser>> listMembers() {
        return listMembersByRole(Role.MEMBER);
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
