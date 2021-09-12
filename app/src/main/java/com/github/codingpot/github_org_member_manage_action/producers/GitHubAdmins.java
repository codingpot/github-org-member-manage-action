package com.github.codingpot.github_org_member_manage_action.producers;

import com.github.codingpot.github_org_member_manage_action.github.GitHubService;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/** Owners from calling {@link GitHubService#listAdmins()}. */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface GitHubAdmins {}
