package com.github.codingpot.github_org_member_manage_action.context;

import com.github.codingpot.github_org_member_manage_action.annotations.DryRun;
import com.github.codingpot.github_org_member_manage_action.annotations.GitHubToken;
import com.github.codingpot.github_org_member_manage_action.annotations.MembersFilePath;
import javax.inject.Inject;
import lombok.Value;

/**
 * Context holds INPUT variables that are provided by GitHub Actions. Then, this context can be
 * injected into other services to fetch input values.
 *
 * <p>See action.yaml for supported input values.
 */
@Value
public class Context {
    String membersFilePath;
    String githubToken;
    boolean dryRun;

    @Inject
    public Context(
            @MembersFilePath String membersFilePath,
            @GitHubToken String githubToken,
            @DryRun boolean dryRun) {
        this.membersFilePath = membersFilePath;
        this.githubToken = githubToken;
        this.dryRun = dryRun;
    }
}
