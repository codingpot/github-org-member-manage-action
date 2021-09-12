package com.github.codingpot.github_org_member_manage_action.github;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GitHubBuilder;

class GitHubServiceV3ImplTest {

    private GitHubServiceV3Impl service;

    @BeforeEach
    void setUp() throws IOException {
        GHOrganization ghOrg = new GitHubBuilder().build().getOrganization("codingpot");
        Optional<GHOrganization> organization = Optional.of(ghOrg);
        service = new GitHubServiceV3Impl(organization);
    }

    @Test
    void listMembers() {
        assertThat(service.listMembers().getData(), hasItem("kkweon"));
        assertThat(service.listMembers().getData(), not(hasItem("kkweon1238124")));
    }
}
