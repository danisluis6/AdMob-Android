package tutorial.lorence.admobandroid.view.activity.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tutorial.lorence.admobandroid.R;

/**
 * The {@link AdItem} class.
 * Provides a reference to each view in the menu item view.
 */

public class AdItem extends RecyclerView.ViewHolder {
    public TextView menuItemName;
    public TextView menuItemDescription;
    public TextView menuItemPrice;
    public TextView menuItemCategory;
    public ImageView menuItemImage;

    public AdItem(View view) {
        super(view);
        menuItemImage = view.findViewById(R.id.menu_item_image);
        menuItemName = view.findViewById(R.id.menu_item_name);
        menuItemPrice = view.findViewById(R.id.menu_item_price);
        menuItemCategory = view.findViewById(R.id.menu_item_category);
        menuItemDescription = view.findViewById(R.id.menu_item_description);
    }
}
