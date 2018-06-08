package tutorial.lorence.admobandroid.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.admobandroid.app.Application;
import tutorial.lorence.admobandroid.helper.FragmentLoading;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class FragmentModule {

    private Application mApplication;
    private Context mContext;

    public FragmentModule(Application application, Context context) {
        this.mApplication = application;
        this.mContext = context;
    }

    @Provides
    @Singleton
    FragmentLoading provideFragmentLoading() {
        return new FragmentLoading();
    }
}
