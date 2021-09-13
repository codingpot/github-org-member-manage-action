package com.github.codingpot.github_org_member_manage_action.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Collections;
import org.junit.jupiter.api.Test;

class DiffServiceTest {

    @Test
    void Should_add_owner_when_local_has_a_single_admin() {
        final ConfigData local =
                ConfigData.builder()
                        .admins(Collections.singleton("kkweon"))
                        .members(Collections.emptySet())
                        .build();
        final ConfigData remote =
                ConfigData.builder()
                        .admins(Collections.emptySet())
                        .members(Collections.emptySet())
                        .build();

        assertThat(
                DiffService.diff(local, remote),
                equalTo(
                        Diff.builder()
                                .newAdmins(Collections.singleton("kkweon"))
                                .newMembers(Collections.emptySet())
                                .membersToBeDeleted(Collections.emptySet())
                                .build()));
    }

    @Test
    void Should_add_members_when_local_has_a_member() {
        final ConfigData local =
                ConfigData.builder()
                        .admins(Collections.emptySet())
                        .members(Collections.singleton("kkweon"))
                        .build();
        final ConfigData remote =
                ConfigData.builder()
                        .admins(Collections.emptySet())
                        .members(Collections.emptySet())
                        .build();
        assertThat(
                DiffService.diff(local, remote),
                equalTo(
                        Diff.builder()
                                .newAdmins(Collections.emptySet())
                                .newMembers(Collections.singleton("kkweon"))
                                .membersToBeDeleted(Collections.emptySet())
                                .build()));
    }
}
