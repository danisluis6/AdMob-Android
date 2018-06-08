package tutorial.lorence.admobandroid.app;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.android.gms.ads.MobileAds;

import tutorial.lorence.admobandroid.R;
import tutorial.lorence.admobandroid.di.component.AppComponent;
import tutorial.lorence.admobandroid.di.component.DaggerAppComponent;
import tutorial.lorence.admobandroid.di.module.AppModule;
import tutorial.lorence.admobandroid.di.module.FragmentModule;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

public class Application extends android.app.Application {

    private AppComponent mApplicationComponent;
    private Context mContext;
    private static Application sInstance;

    public static synchronized Application getInstance() {
        if (sInstance == null) {
            sInstance = new Application();
        }
        return sInstance;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        sInstance = this;
        initializeGoogle();
        initAppComponent();
    }

    private void initializeGoogle() {
        // Initialize the Google Mobile Ads SDK
        MobileAds.initialize(getApplicationContext(),
                getString(R.string.admob_app_id));
    }

    private void initAppComponent() {
        mApplicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this,mContext))
                .fragmentModule(new FragmentModule(this,mContext))
                .build();
    }

    public AppComponent getAppComponent() {
        return mApplicationComponent;
    }

}
