package com.github.codingpot.github_org_member_manage_action.producers;

import dagger.Module;
import dagger.Provides;
import dagger.producers.Production;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/** Default ExecutorService to use for Producers. */
@Module
public class ExecutorModule {
    @Provides
    @Production
    static Executor executor() {
        return Executors.newCachedThreadPool();
    }
}
