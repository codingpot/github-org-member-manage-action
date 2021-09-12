package com.github.codingpot.github_org_member_manage_action.github;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GitHubBuilder;

class GitHubServiceV3ImplTest {

    private GitHubServiceV3Impl service;

    @BeforeEach
    void setUp() throws IOException {
        assumeTrue(System.getenv("INPUT_GH_TOKEN") != null);
        GHOrganization ghOrg =
                new GitHubBuilder()
                        .withOAuthToken(System.getenv("INPUT_GH_TOKEN"))
                        .build()
                        .getOrganization("codingpot");
        service = new GitHubServiceV3Impl(ghOrg);
    }

    @Test
    void listOwners() {
        assertThat(service.listAdmins().getData(), hasItem(new GitHubUser("kkweon", Role.ADMIN)));
        assertThat(
                service.listAdmins().getData(),
                not(hasItem(new GitHubUser("kkweon1238124", Role.ADMIN))));
    }

    @Test
    void listMembers() {
        assertThat(
                service.listMembers().getData(),
                not(hasItem(new GitHubUser("kkweon", Role.MEMBER))));
    }
}
