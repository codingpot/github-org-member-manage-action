package com.github.codingpot.github_org_member_manage_action.github;

import com.github.codingpot.github_org_member_manage_action.status.StatusOr;
import java.util.List;

/** Provide a service interacting with GitHub. */
public interface GitHubService {
    StatusOr<List<String>> listMembers();
}