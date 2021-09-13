package com.github.codingpot.github_org_member_manage_action.producers;

import com.github.codingpot.github_org_member_manage_action.config.ConfigData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/** Represents {@link ConfigData} from GitHub. */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface GitHubConfigData {}
