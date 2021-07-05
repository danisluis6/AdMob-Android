package tutorial.lorence.admobandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import tutorial.lorence.admobandroid.di.module.AppModule;
import tutorial.lorence.admobandroid.di.module.FragmentModule;
import tutorial.lorence.admobandroid.di.module.HomeModule;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@Singleton
@Component(
        modules = {
                AppModule.class, FragmentModule.class
        }
)
public interface AppComponent {
        HomeComponent plus(HomeModule module);
}
