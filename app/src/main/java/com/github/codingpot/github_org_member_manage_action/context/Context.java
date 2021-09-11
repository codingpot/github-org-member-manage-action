package com.github.codingpot.github_org_member_manage_action.context;

import com.github.codingpot.github_org_member_manage_action.annotations.GitHubToken;
import com.github.codingpot.github_org_member_manage_action.annotations.MembersFilePath;
import java.util.Optional;
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
    Optional<String> membersFilePath;
    Optional<String> githubToken;

    @Inject
    public Context(
            @MembersFilePath Optional<String> membersFilePath,
            @GitHubToken Optional<String> githubToken) {
        this.membersFilePath = membersFilePath;
        this.githubToken = githubToken;
    }

    public Optional<String> error() {
        StringBuilder builder = new StringBuilder();
        if (githubToken.isEmpty()) {
            builder.append("- GitHub token w/ admin:org access should be provided\n");
        }

        if (membersFilePath.isEmpty()) {
            builder.append("- members_filepath should be provided\n");
        }

        final String errMsg = builder.toString();
        return errMsg.isBlank()
                ? Optional.empty()
                : Optional.of(String.format("Errors were found:\n%s", errMsg));
    }
}
