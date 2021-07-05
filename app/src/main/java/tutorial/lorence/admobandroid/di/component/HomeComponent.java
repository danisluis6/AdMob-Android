package tutorial.lorence.admobandroid.di.component;

import dagger.Subcomponent;
import tutorial.lorence.admobandroid.di.module.HomeModule;
import tutorial.lorence.admobandroid.di.scope.ActivityScope;
import tutorial.lorence.admobandroid.view.activity.HomeActivity;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@ActivityScope
@Subcomponent(

        modules = {
                HomeModule.class
        }
)
public interface HomeComponent {
    HomeActivity inject(HomeActivity homeActivity);
}


