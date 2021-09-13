package com.github.codingpot.github_org_member_manage_action;

import com.github.codingpot.github_org_member_manage_action.config.ConfigData;
import com.github.codingpot.github_org_member_manage_action.context.Context;
import com.github.codingpot.github_org_member_manage_action.github.GitHubService;
import com.github.codingpot.github_org_member_manage_action.producers.ProducersComponent;
import com.github.codingpot.github_org_member_manage_action.status.Status;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;

/** Main entry point of the application. */
public class App {
    @Inject Context context;
    @Inject GitHubService gitHubService;
    @Inject ProducersComponent.Builder builder;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new App().run();
    }

    private void run() throws ExecutionException, InterruptedException {
        DaggerAppComponent.create().inject(this);

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

        System.exit(0);
    }
}
