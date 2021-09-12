package com.github.codingpot.github_org_member_manage_action;

import java.util.Set;
import lombok.Data;

/** Diff holds the difference between members.yaml and repo state from GitHub. */
@Data
public class Diff {
    /** Owners to be added. */
    private Set<String> newOwners;
    /** Members to be added. */
    private Set<String> newMembers;
    /** Members to be removed from the org. */
    private Set<String> membersToBeDeleted;
}
