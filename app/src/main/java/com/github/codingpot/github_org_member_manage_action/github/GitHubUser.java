package com.github.codingpot.github_org_member_manage_action.github;

import lombok.Value;

/** Represents a single GitHub user. */
@Value
public class GitHubUser {
    String username;
    Role role;
}
