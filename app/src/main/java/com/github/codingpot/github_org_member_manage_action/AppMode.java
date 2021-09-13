package com.github.codingpot.github_org_member_manage_action;

/**
 * SYNC tries to update Org memberships based on YAML. WRITE will just create a YAML(members.yaml)
 * based on GitHub state.
 */
public enum AppMode {
    SYNC,
    WRITE
}
