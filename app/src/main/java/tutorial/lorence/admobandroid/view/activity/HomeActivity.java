package tutorial.lorence.admobandroid.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import tutorial.lorence.admobandroid.R;
import tutorial.lorence.admobandroid.app.Application;
import tutorial.lorence.admobandroid.di.module.HomeModule;
import tutorial.lorence.admobandroid.helper.FragmentLoading;
import tutorial.lorence.admobandroid.helper.FragmentRecycler;
import tutorial.lorence.admobandroid.helper.JsonData;
import tutorial.lorence.admobandroid.view.BaseActivity;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class HomeActivity extends BaseActivity {

    public static final int NUMBER_OF_ADS = 5;
    private List<Object> mArrMenuItems = new ArrayList<>();
    private List<NativeAd> mArrNativeAds = new ArrayList<>();

    @Inject
    FragmentLoading mFragmentLoading;

    @Inject
    FragmentTransaction mFragmentTransaction;

    @Inject
    JsonData mJsonData;

    @Inject
    AdLoader.Builder mBuilder;

    @Inject
    FragmentRecycler mFragmentRecycler;

    @Override
    public void distributedDaggerComponents() {
        Application.getInstance()
                .getAppComponent()
                .plus(new HomeModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initAttributes(Bundle savedInstanceState) {
        super.initAttributes(savedInstanceState);
        if (savedInstanceState == null) {
            mFragmentTransaction.add(R.id.fragment_container, mFragmentLoading);
            mFragmentTransaction.commit();
            mJsonData.addMenuItemsFromJson(mArrMenuItems);
            loadNativeAd();
        }
    }

    private void loadNativeAd() {
        loadNativeAd(0);
    }

    private void loadNativeAd(final int adLoadCount) {
        if (adLoadCount >= NUMBER_OF_ADS) {
            insertAdsInMenuItems();
            loadMenu();
        } else {
            AdLoader adLoader = mBuilder.forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
                @Override
                public void onAppInstallAdLoaded(NativeAppInstallAd ad) {
                    mArrNativeAds.add(ad);
                    loadNativeAd(adLoadCount + 1);

                }
            }).forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
                @Override
                public void onContentAdLoaded(NativeContentAd ad) {
                    mArrNativeAds.add(ad);
                    loadNativeAd(adLoadCount + 1);
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int errorCode) {
                    loadNativeAd(adLoadCount + 1);
                }
            }).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        }
    }

    private void insertAdsInMenuItems() {
        if (mArrNativeAds.size() <= 0) {
            return;
        }
        int offset = (mArrMenuItems.size() / mArrNativeAds.size()) + 1;
        int index = 0;
        for (NativeAd ad : mArrNativeAds) {
            mArrMenuItems.add(index, ad);
            index = index + offset;
        }
    }

    private void loadMenu() {
        mFragmentTransaction.replace(R.id.fragment_container, mFragmentRecycler);
        mFragmentTransaction.disallowAddToBackStack();
        mFragmentTransaction.commit();
    }

    public List<Object> getRecyclerViewItems() {
        return mArrMenuItems;
    }

}
