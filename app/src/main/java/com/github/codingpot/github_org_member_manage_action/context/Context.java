package com.github.codingpot.github_org_member_manage_action.context;

import com.github.codingpot.github_org_member_manage_action.annotations.GitHubToken;
import com.github.codingpot.github_org_member_manage_action.annotations.MembersFileContent;
import com.github.codingpot.github_org_member_manage_action.annotations.MembersFilePath;
import java.util.Optional;
import javax.inject.Inject;
import lombok.Getter;
import lombok.ToString;

/**
 * Context holds INPUT variables that are provided by GitHub Actions. Then, this context can be
 * injected into other services to fetch input values.
 *
 * <p>See action.yaml for supported input values.
 */
@ToString
public class Context {
    @Getter private final Optional<String> membersFilePath;
    @Getter private final Optional<String> membersFileContent;
    @Getter private final Optional<String> githubToken;

    @Inject
    Context(
            @MembersFilePath Optional<String> membersFilePath,
            @MembersFileContent Optional<String> membersFileContent,
            @GitHubToken Optional<String> githubToken) {
        this.membersFilePath = membersFilePath;
        this.membersFileContent = membersFileContent;
        this.githubToken = githubToken;
    }

    public Optional<String> error() {
        StringBuilder builder = new StringBuilder();
        if (githubToken.isEmpty()) {
            builder.append("- GitHub token was not provided\n");
        }

        if (membersFilePath.isEmpty() && membersFileContent.isEmpty()) {
            builder.append("- Either members filepath or filecontent should be provided\n");
        }
        final String errMsg = builder.toString();
        return errMsg.isBlank()
                ? Optional.empty()
                : Optional.of(String.format("Errors were found:\n%s", errMsg));
    }
}
