package com.github.codingpot.github_org_member_manage_action.annotations;

import com.github.codingpot.github_org_member_manage_action.github.FakeGitHubService;
import com.github.codingpot.github_org_member_manage_action.github.GitHubServiceV3Impl;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/** Use {@link FakeGitHubService} instead of {@link GitHubServiceV3Impl}. */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DryRun {}
