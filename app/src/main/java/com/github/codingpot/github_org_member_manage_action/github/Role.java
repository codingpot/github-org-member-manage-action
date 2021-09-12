package com.github.codingpot.github_org_member_manage_action.github;

/** There are 2 roles in GitHub users: ADMIN or MEMBER */
public enum Role {
    ADMIN("admin"),
    MEMBER("member"),
    ;

    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
