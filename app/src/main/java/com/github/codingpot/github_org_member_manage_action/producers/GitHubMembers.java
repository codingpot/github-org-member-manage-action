package com.github.codingpot.github_org_member_manage_action.producers;

import com.github.codingpot.github_org_member_manage_action.github.GitHubService;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/** Members from {@link GitHubService#listMembers()}. */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface GitHubMembers {}
