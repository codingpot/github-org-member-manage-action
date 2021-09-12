package com.github.codingpot.github_org_member_manage_action;

import com.github.codingpot.github_org_member_manage_action.context.Context;
import com.github.codingpot.github_org_member_manage_action.github.GitHubService;
import com.github.codingpot.github_org_member_manage_action.github.GitHubUser;
import com.github.codingpot.github_org_member_manage_action.producers.ProducersComponent;
import java.util.List;
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
        System.out.println("context = " + context);
        final List<GitHubUser> listStatusOr = gitHubService.listAdmins().getData();
        System.out.println("listStatusOr = " + listStatusOr);

        final ProducersComponent producersComponent = builder.build();

        final ConfigData localConfigData = producersComponent.configDataFromLocal().get();
        System.out.println("localConfigData = " + localConfigData);
        final ConfigData githubConfigData = producersComponent.configDataFromGitHub().get();
        System.out.println("githubConfigData = " + githubConfigData);

        System.exit(0);
    }
}
