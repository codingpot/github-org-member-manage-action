package com.github.codingpot.github_org_member_manage_action.config;

import com.google.common.collect.Sets;
import java.util.Collections;

/** DiffService compares local ConfigData and remote ConfigData and returns Diff. */
public class DiffService {

    public static Diff diff(ConfigData local, ConfigData remote) {
        return Diff.builder()
                .newAdmins(Sets.difference(local.getAdmins(), remote.getAdmins()))
                .newMembers(Sets.difference(local.getMembers(), remote.getMembers()))
                .membersToBeDeleted(Collections.emptySet())
                .build();
    }
}
