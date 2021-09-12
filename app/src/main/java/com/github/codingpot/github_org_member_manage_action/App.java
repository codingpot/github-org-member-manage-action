package com.github.codingpot.github_org_member_manage_action;

import com.github.codingpot.github_org_member_manage_action.context.Context;
import com.github.codingpot.github_org_member_manage_action.github.GitHubService;
import java.util.List;
import javax.inject.Inject;

/** Main entry point of the application. */
public class App {
    @Inject Context context;
    @Inject GitHubService gitHubService;

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        DaggerAppComponent.create().inject(this);
        System.out.println("context = " + context);
        final List<String> listStatusOr = gitHubService.listMembers().getData();
        System.out.println("listStatusOr = " + listStatusOr);
    }
}
