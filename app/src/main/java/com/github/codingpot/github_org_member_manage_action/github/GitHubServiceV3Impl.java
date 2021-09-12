package com.github.codingpot.github_org_member_manage_action.github;

import com.github.codingpot.github_org_member_manage_action.status.StatusOr;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHPerson;

public class GitHubServiceV3Impl implements GitHubService {
    private final GHOrganization organization;

    @Inject
    GitHubServiceV3Impl(GHOrganization organization) {
        this.organization = organization;
    }

    @Override
    public StatusOr<List<String>> listMembers() {
        try {
            return StatusOr.createOk(
                    organization.listMembers().toList().stream()
                            .map(GHPerson::getLogin)
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            return StatusOr.createError(e.getMessage());
        }
    }
}
