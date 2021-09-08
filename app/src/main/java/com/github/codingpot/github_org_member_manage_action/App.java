package com.github.codingpot.github_org_member_manage_action;

import com.github.codingpot.github_org_member_manage_action.context.Context;
import javax.inject.Inject;

/** Main entry point of the application. */
public class App {
    @Inject Context context;

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        DaggerAppComponent.create().inject(this);
        context.error()
                .map(
                        errorMsg -> {
                            System.out.println(errorMsg);
                            System.exit(1);
                            return 0;
                        });

        System.out.println("context = " + context);
    }
}
