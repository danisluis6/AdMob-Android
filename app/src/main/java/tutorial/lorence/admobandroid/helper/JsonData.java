package tutorial.lorence.admobandroid.helper;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

import tutorial.lorence.admobandroid.view.activity.HomeActivity;
import tutorial.lorence.admobandroid.view.activity.adapter.bean.MenuItem;

/**
 * Created by lorence on 28/12/2017.
 *
 * @version master
 * @since 12/28/2017
 */

public class JsonData {

    private Context mContext;

    @Inject
    public JsonData(Context context) {
        mContext = context;
    }

    /**
     * Adds {@link MenuItem}'s from a JSON file.
     */
    public void addMenuItemsFromJson(List<Object> arrMenuItems) {
        try {
            String jsonDataString = converJsonToString();
            JSONArray menuItemsJsonArray = new JSONArray(jsonDataString);
            for (int i = 0; i < menuItemsJsonArray.length(); ++i) {
                JSONObject menuItemObject = menuItemsJsonArray.getJSONObject(i);
                String menuItemName = menuItemObject.getString("name");
                String menuItemDescription = menuItemObject.getString("description");
                String menuItemPrice = menuItemObject.getString("price");
                String menuItemCategory = menuItemObject.getString("category");
                String menuItemImageName = menuItemObject.getString("photo");
                MenuItem menuItem = new MenuItem(menuItemName, menuItemDescription, menuItemPrice,
                        menuItemCategory, menuItemImageName);
                arrMenuItems.add(menuItem);
            }
        } catch (IOException | JSONException exception) {
            Log.e(HomeActivity.class.getName(), "Unable to parse JSON file.", exception);
        }
    }

    /**
     * Reads the JSON file and converts the JSON data to a {@link String}.
     *
     * @return A {@link String} representation of the JSON data.
     * @throws IOException if unable to read the JSON file.
     */
    private String converJsonToString() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonDataString = null;
            inputStream = mContext.getResources().openRawResource(mContext.getResources().getIdentifier("json_items", "raw", mContext.getPackageName()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(builder);
    }

}
