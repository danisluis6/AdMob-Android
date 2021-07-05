package tutorial.lorence.admobandroid.di.module;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.AdLoader;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.admobandroid.R;
import tutorial.lorence.admobandroid.di.scope.ActivityScope;
import tutorial.lorence.admobandroid.helper.FragmentRecycler;
import tutorial.lorence.admobandroid.helper.JsonData;
import tutorial.lorence.admobandroid.view.activity.HomeActivity;
import tutorial.lorence.admobandroid.view.activity.adapter.RecyclerViewAdapter;
import tutorial.lorence.admobandroid.view.activity.adapter.ViewType;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

@Module
public class HomeModule {

    private HomeActivity mHomeActivity;

    public HomeModule(HomeActivity homeActivity) {
        this.mHomeActivity = homeActivity;
    }

    @Provides
    @ActivityScope
    HomeActivity provideHomeActivity() {
        return mHomeActivity;
    }

    /**
     * Show up recyclerView adapter
     * @return FragmentTransaction
     */
    @Provides
    @ActivityScope
    JsonData provideJsonData(Context context) {
        return new JsonData(context);
    }

    /**
     * Setup automatically load advertisement
     * @return FragmentTransaction
     */
    @Provides
    @ActivityScope
    AdLoader.Builder provideAdLoaderBuilder(Context context) {
        return new AdLoader.Builder(context, context.getString(R.string.ad_unit_id));
    }

    /**
     * Show up recyclerView adapter
     * @return FragmentTransaction
     */
    @Provides
    @ActivityScope
    FragmentTransaction provideFragmentTransaction() {
        return mHomeActivity.getSupportFragmentManager().beginTransaction();
    }

    @Provides
    @ActivityScope
    ViewType provideViewType() {
        return new ViewType();
    }

    @Provides
    @ActivityScope
    RecyclerViewAdapter provideRecyclerViewAdapter(Context context, ViewType viewType) {
        return new RecyclerViewAdapter(context, viewType, new ArrayList<>());
    }

    /**
     * Show up fragment
     * @return FragmentTransaction
     */
    @Provides
    @ActivityScope
    RecyclerView.LayoutManager provideLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    @ActivityScope
    FragmentRecycler provideFragmentRecycler(Context context, HomeActivity homeActivity, RecyclerViewAdapter recyclerViewAdapter, RecyclerView.LayoutManager layoutManager) {
        return new FragmentRecycler(context, homeActivity, recyclerViewAdapter, layoutManager);
    }
}
