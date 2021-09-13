package com.github.codingpot.github_org_member_manage_action.producers;

import com.github.codingpot.github_org_member_manage_action.config.ConfigData;
import com.github.codingpot.github_org_member_manage_action.config.ConfigManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/** {@link ConfigData} from {@link ConfigManager#readFromLocal()}. */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalConfigData {}
