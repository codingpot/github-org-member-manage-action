package com.github.codingpot.github_org_member_manage_action;

import com.github.codingpot.github_org_member_manage_action.context.Context;
import com.github.codingpot.github_org_member_manage_action.env.EnvModule;
import dagger.Component;
import javax.inject.Singleton;

/** AppComponent is the main component that lives with the Application scope. */
@Singleton
@Component(modules = EnvModule.class)
public interface AppComponent {
    Context context();

    void inject(App app);
}
