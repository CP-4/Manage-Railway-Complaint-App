package com.epoch.android.complaintlog;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.epoch.android.complaintlog.dummy.DummyContent;
import com.twitter.sdk.android.core.Twitter;

import java.util.ArrayList;
import java.util.List;
import com.epoch.android.complaintlog.MyDataset;

public class ItemListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    private LinearLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private List<MyDataset> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Twitter.initialize(this);

        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitle(getResources().getString(R.string.open_complaints));
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        RecyclerView recyclerView = findViewById(R.id.item_list);
        recyclerView.setHasFixedSize(true);
//        assert recyclerView != null;
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        listItems = new ArrayList<>();

        for (int i=0; i<=10; i++) {
            MyDataset listItem = new MyDataset(
                    "Complaint "+i,
                    "Location "+i,
                    "HH:MM",
                    "http://temp.link/complaint_"+i,
                    true,
                    i
            );

            listItems.add(listItem);
        }




        mAdapter = new SimpleItemRecyclerViewAdapter(mTwoPane, this, listItems);
        recyclerView.setAdapter(mAdapter);
//        setupRecyclerView(recyclerView);
//        mAdapter = new MyAdapter(myDataset);
//        recyclerView.setAdapter(mAdapter);
    }

//    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
//        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
//    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

//        private final ItemListActivity mParentActivity;
//        private final List<DummyContent.DummyItem> mValues;
        private final boolean mTwoPane;
        private Context context;

        private List<MyDataset> listItems;

        public SimpleItemRecyclerViewAdapter(boolean mTwoPane, Context context, List<MyDataset> listItems) {
            this.mTwoPane = mTwoPane;
            this.context = context;
            this.listItems = listItems;
        }
//
//        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
//                if (mTwoPane) {
//                    Bundle arguments = new Bundle();
//                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
//                    ItemDetailFragment fragment = new ItemDetailFragment();
//                    fragment.setArguments(arguments);
//                    mParentActivity.getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.item_detail_container, fragment)
//                            .commit();
//                } else {
//                    Context context = view.getContext();
//                    Intent intent = new Intent(context, ItemDetailActivity.class);
//                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);
//
//                    context.startActivity(intent);
//                }
//            }
//        };


        class ViewHolder extends RecyclerView.ViewHolder {
//            final TextView mIdView;
            public TextView textViewComplaint;

            ViewHolder(View view) {
                super(view);
//                mIdView = (TextView) view.findViewById(R.id.id_text);
                textViewComplaint = view.findViewById(R.id.complaint);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.complaint_view_small, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mIdView.setText(mValues.get(position).id);
//            holder.mContentView.setText(mValues.get(position).content);
//            holder.itemView.setTag(mValues.get(position));
//            holder.itemView.setOnClickListener(mOnClickListener);
            MyDataset listItem = listItems.get(position);
            holder.textViewComplaint.setText(listItem.getComplaint());
        }

        @Override
        public int getItemCount() {
            return listItems.size();
        }

    }
}
