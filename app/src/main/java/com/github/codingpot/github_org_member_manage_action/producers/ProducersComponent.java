package com.github.codingpot.github_org_member_manage_action.producers;

import com.github.codingpot.github_org_member_manage_action.config.ConfigData;
import com.github.codingpot.github_org_member_manage_action.status.Status;
import com.google.common.util.concurrent.ListenableFuture;
import dagger.producers.ProductionSubcomponent;

/** ProducersComponent is a component for asynchronous tasks. */
@ProductionSubcomponent(modules = {ProducersModule.class, ExecutorModule.class})
public interface ProducersComponent {
    @GitHubConfigData
    ListenableFuture<ConfigData> configDataFromGitHub();

    @LocalConfigData
    ListenableFuture<ConfigData> configDataFromLocal();

    ListenableFuture<Status> execute();

    @ProductionSubcomponent.Builder
    interface Builder {
        ProducersComponent build();
    }
}
