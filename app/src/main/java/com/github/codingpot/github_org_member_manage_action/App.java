package com.github.codingpot.github_org_member_manage_action;

import com.github.codingpot.github_org_member_manage_action.config.ConfigData;
import com.github.codingpot.github_org_member_manage_action.config.ConfigManager;
import com.github.codingpot.github_org_member_manage_action.context.Context;
import com.github.codingpot.github_org_member_manage_action.github.GitHubService;
import com.github.codingpot.github_org_member_manage_action.producers.ProducersComponent;
import com.github.codingpot.github_org_member_manage_action.status.Status;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;

/** Main entry point of the application. */
public class App {
    @Inject Context context;
    @Inject GitHubService gitHubService;
    @Inject ProducersComponent.Builder builder;
    @Inject ConfigManager manager;

    public static void main(String[] args) {
        try {
            new App().run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void run() throws ExecutionException, InterruptedException, IOException {
        DaggerAppComponent.create().inject(this);
        System.out.println("context = " + context);

        if (context.getMode() == AppMode.SYNC) {
            runSync();
        } else if (context.getMode() == AppMode.WRITE) {
            runWrite();
        }

        System.exit(0);
    }

    private void runWrite() throws ExecutionException, InterruptedException, IOException {
        final ProducersComponent producersComponent = builder.build();
        final ConfigData data = producersComponent.configDataFromGitHub().get();
        manager.write(data);
    }

    private void runSync() throws ExecutionException, InterruptedException {
        final ProducersComponent producersComponent = builder.build();

        final ConfigData localConfigData = producersComponent.configDataFromLocal().get();
        System.out.println("localConfigData = " + localConfigData);

        final ConfigData githubConfigData = producersComponent.configDataFromGitHub().get();
        System.out.println("githubConfigData = " + githubConfigData);

        final Status status = producersComponent.execute().get();
        if (status.hasError()) {
            System.out.println(status.getErrorMessage().orElse(""));
            System.exit(1);
        }
    }
}
