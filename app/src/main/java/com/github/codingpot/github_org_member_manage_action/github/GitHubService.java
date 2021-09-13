package com.github.codingpot.github_org_member_manage_action.github;

import com.github.codingpot.github_org_member_manage_action.status.Status;
import com.github.codingpot.github_org_member_manage_action.status.StatusOr;
import java.util.List;

/** Provide a service interacting with GitHub. */
public interface GitHubService {
    StatusOr<List<GitHubUser>> listAdmins();

    StatusOr<List<GitHubUser>> listMembers();

    Status addMembers(Iterable<String> asList);

    Status addOwners(Iterable<String> newOwners);
}
