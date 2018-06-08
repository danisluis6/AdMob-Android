package tutorial.lorence.admobandroid.view.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;

import java.util.List;

import tutorial.lorence.admobandroid.R;
import tutorial.lorence.admobandroid.view.activity.adapter.bean.MenuItem;
import tutorial.lorence.admobandroid.view.activity.adapter.viewholder.AdAppInstall;
import tutorial.lorence.admobandroid.view.activity.adapter.viewholder.AdContent;
import tutorial.lorence.admobandroid.view.activity.adapter.viewholder.AdItem;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ViewType mViewType;
    private final Context mContext;
    private final List<Object> mListObject;

    /**
     * For this example app, the recyclerViewItems list contains only
     * {@link android.view.MenuItem} and {@link NativeAd} types.
     */
    public RecyclerViewAdapter(Context context, ViewType viewType, List<Object> list) {
        this.mContext = context;
        this.mListObject = list;
        this.mViewType = viewType;
    }

    @Override
    public int getItemCount() {
        return mListObject.size();
    }

    /**
     * Creates a new view for a menu item view or a Native Express ad view
     * based on the viewType. This method is invoked by the layout manager.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == mViewType.NATIVE_APP_INSTALL_AD_VIEW_TYPE) {
            View nativeAppInstallLayoutView = LayoutInflater.from(
                    viewGroup.getContext()).inflate(R.layout.ad_app_install, viewGroup, false);
            return new AdAppInstall(nativeAppInstallLayoutView);
        } else if (viewType == mViewType.NATIVE_CONTENT_AD_VIEW_TYPE) {
            View nativeContentLayoutView = LayoutInflater.from(
                    viewGroup.getContext()).inflate(R.layout.ad_content, viewGroup, false);
            return new AdContent(nativeContentLayoutView);
        } else {
            View menuItemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.ad_item_container, viewGroup, false);
            return new AdItem(menuItemLayoutView);
        }
    }

    /**
     * Determines the view type for the given position.
     */
    @Override
    public int getItemViewType(int position) {
        Object recyclerViewItem = mListObject.get(position);
        if (recyclerViewItem instanceof NativeAppInstallAd) {
            return mViewType.NATIVE_APP_INSTALL_AD_VIEW_TYPE;
        } else if (recyclerViewItem instanceof NativeContentAd) {
            return mViewType.NATIVE_CONTENT_AD_VIEW_TYPE;
        }
        return mViewType.MENU_ITEM_VIEW_TYPE;
    }

    /**
     * Replaces the content in the views that make up the menu item view and the
     * Native Express ad view. This method is invoked by the layout manager.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == mViewType.NATIVE_APP_INSTALL_AD_VIEW_TYPE) {
            NativeAppInstallAd appInstallAd = (NativeAppInstallAd) mListObject.get(position);
            populateAppInstallAdView(appInstallAd, (NativeAppInstallAdView) holder.itemView);
        } else if (viewType == mViewType.NATIVE_CONTENT_AD_VIEW_TYPE) {
            NativeContentAd contentAd = (NativeContentAd) mListObject.get(position);
            populateContentAdView(contentAd, (NativeContentAdView) holder.itemView);
        } else {
            AdItem menuItemHolder = (AdItem) holder;
            MenuItem menuItem = (MenuItem) mListObject.get(position);
            // Get the menu item image resource ID.
            String imageName = menuItem.getImageName();
            int imageResID = mContext.getResources().getIdentifier(imageName, "drawable",
                    mContext.getPackageName());
            // Add the menu item details to the menu item view.
            menuItemHolder.menuItemImage.setImageResource(imageResID);
            menuItemHolder.menuItemName.setText(menuItem.getName());
            menuItemHolder.menuItemPrice.setText(menuItem.getPrice());
            menuItemHolder.menuItemCategory.setText(menuItem.getCategory());
            menuItemHolder.menuItemDescription.setText(menuItem.getDescription());

        }
    }

    private void populateAppInstallAdView(NativeAppInstallAd nativeAppInstallAd,
                                          NativeAppInstallAdView adView) {
        // Some assets are guaranteed to be in every NativeAppInstallAd.
        ((ImageView) adView.getIconView()).setImageDrawable(nativeAppInstallAd.getIcon()
                .getDrawable());
        ((TextView) adView.getHeadlineView()).setText(nativeAppInstallAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeAppInstallAd.getBody());
        ((Button) adView.getCallToActionView()).setText(nativeAppInstallAd.getCallToAction());

        // These assets aren't guaranteed to be in every NativeAppInstallAd, so it's important to
        // check before trying to display them.
        if (nativeAppInstallAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAppInstallAd.getPrice());
        }

        if (nativeAppInstallAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAppInstallAd.getStore());
        }

        if (nativeAppInstallAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAppInstallAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeAppInstallAd);
    }

    /**
     * Populates a {@link NativeContentAdView} object with data from a given
     * {@link NativeContentAd}.
     *
     * @param nativeContentAd the object containing the ad's assets
     * @param adView          the view to be populated
     */
    private void populateContentAdView(NativeContentAd nativeContentAd,
                                       NativeContentAdView adView) {
        // Some assets are guaranteed to be in every NativeContentAd.
        ((TextView) adView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeContentAd.getBody());
        ((TextView) adView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
        ((TextView) adView.getAdvertiserView()).setText(nativeContentAd.getAdvertiser());

        List<NativeAd.Image> images = nativeContentAd.getImages();

        if (images.size() > 0) {
            ((ImageView) adView.getImageView()).setImageDrawable(images.get(0).getDrawable());
        }

        // Some aren't guaranteed, however, and should be checked.
        NativeAd.Image logoImage = nativeContentAd.getLogo();

        if (logoImage == null) {
            adView.getLogoView().setVisibility(View.INVISIBLE);
        } else {
            ((ImageView) adView.getLogoView()).setImageDrawable(logoImage.getDrawable());
            adView.getLogoView().setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeContentAd);
    }
}
