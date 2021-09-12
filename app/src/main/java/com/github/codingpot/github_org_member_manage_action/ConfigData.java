package com.github.codingpot.github_org_member_manage_action;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/** ConfigData holds members.yaml data */
@Value
@Jacksonized
@Builder
public class ConfigData {
    @JsonProperty("org_name")
    String orgName;

    Set<String> admins;
    Set<String> members;
}
