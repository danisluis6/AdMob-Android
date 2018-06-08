package tutorial.lorence.admobandroid.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tutorial.lorence.admobandroid.R;
import tutorial.lorence.admobandroid.view.activity.HomeActivity;
import tutorial.lorence.admobandroid.view.activity.adapter.RecyclerViewAdapter;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@SuppressLint("ValidFragment")
public class FragmentRecycler extends Fragment {

    private List<Object> mRecyclerViewItems;
    private HomeActivity mHomeActivity;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;

    @SuppressLint("ValidFragment")
    public FragmentRecycler(Context context, HomeActivity homeActivity, RecyclerViewAdapter recyclerViewAdapter, RecyclerView.LayoutManager layoutManager) {
        this.mContext = context;
        this.mHomeActivity = homeActivity;
        this.mLayoutManager = layoutManager;
        this.mRecyclerViewAdapter = recyclerViewAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mRecyclerViewItems = mHomeActivity.getRecyclerViewItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        return rootView;
    }
}