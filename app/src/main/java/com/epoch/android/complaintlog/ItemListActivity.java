package com.epoch.android.complaintlog;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.epoch.android.complaintlog.dummy.DummyContent;
import com.twitter.sdk.android.core.Twitter;

import java.util.ArrayList;
import java.util.List;
import com.epoch.android.complaintlog.MyDataset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ItemListActivity extends AppCompatActivity {

    private static final String TAG = "ItemListActivity";
    private boolean mTwoPane;
    private LinearLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private List<MyDataset> listItems;
    Context acticityContext = this;
    private static final String URL_CREATE = "172.16.234.109:5000/capp?create=True&department=money";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Twitter.initialize(this);

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
                    i,
                    "Complaint Dept",
                    "Complaint Complaint Complaint " + i,
                    "example@google.com",
                    "pts" + i,
                    "1200" + i,
                    "Train Name: " + i,
                    "S/0" + i,
                    "Station: " + i,
                    "temp.link/" + i,
                    0,
                    1,
                    "HH:MM"
            );

            listItems.add(listItem);
        }


//        mAdapter = new SimpleItemRecyclerViewAdapter(this, mTwoPane, this, listItems);
//        recyclerView.setAdapter(mAdapter);
//        setupRecyclerView(recyclerView);
//        mAdapter = new MyAdapter(myDataset);
//        recyclerView.setAdapter(mAdapter);

     //   loadRecyclerViewData();
        mAdapter = new SimpleItemRecyclerViewAdapter(this, mTwoPane, this, listItems);
        recyclerView.setAdapter(mAdapter);
    }


//    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
//        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane));
//    }


        private void loadRecyclerViewData() {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading Complaints");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_CREATE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("complaints");

                                for(int i=0; i<jsonArray.length(); i++) {
                                    JSONObject jsonComplaint = jsonArray.getJSONObject(i);
                                    MyDataset item = new MyDataset(
                                            jsonComplaint.getInt("id"),
                                            jsonComplaint.getString("dept"),
                                            jsonComplaint.getString("query"),
                                            jsonComplaint.getString("email"),
                                            jsonComplaint.getString("pts"),
                                            jsonComplaint.getString("train-no"),
                                            jsonComplaint.getString("train-name"),
                                            jsonComplaint.getString("seat-no"),
                                            jsonComplaint.getString("station"),
                                            jsonComplaint.getString("link"),
                                            jsonComplaint.getInt("resolved"),
                                            jsonComplaint.getInt("new"),
                                            jsonComplaint.getString("time")
                                    );
                                listItems.add(item);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Log.d(TAG, "onErrorResponse: volley error");
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }


    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        //        private final ItemListActivity mParentActivity;
//        private final List<DummyContent.DummyItem> mValues;
        private final boolean mTwoPane;
        private Context context;
        private final ItemListActivity mParentActivity;
        private List<MyDataset> listItems;

        public SimpleItemRecyclerViewAdapter(ItemListActivity parent, boolean mTwoPane, Context context, List<MyDataset> listItems) {
            this.mTwoPane = mTwoPane;
            this.context = context;
            this.listItems = listItems;
            mParentActivity = parent;
        }


        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
//                MyDataset item = (MyDataset) view.getTag();

//                Log.d(TAG, "onClick: MyDataset"+item.toString());


//                Log.d(TAG, "onClick: View Clicked");
//                if (mTwoPane) {
//                    Bundle arguments = new Bundle();
//                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
//                    ItemDetailFragment fragment = new ItemDetailFragment();
//                    fragment.setArguments(arguments);
//                    mParentActivity.getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.item_detail_container, fragment)
//                            .commit();
//                } else {
//                Context context = view.getContext();
//                Context context = view.getContext();
//                Intent intent = new Intent(context, ItemDetailActivity.class);
//
//                try{
//                    intent.putExtra("complatintId", item.getComplaintIdString());
//                    intent.putExtra("complaint", item.getComplaint());
//                    intent.putExtra("time", item.getTime());
//                }catch (Exception e) {
//                    Log.e(TAG, "onClick: " + e.getMessage());
//                }

                MyDataset item = (MyDataset) view.getTag();
                Log.d(TAG, "onClick: MyDataset"+item.toString());
//                Log.d(TAG, "onClick: view: ");
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.getComplaintIdString());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
//                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.getComplaintIdString());

                    context.startActivity(intent);
                }

//                Log.d(TAG, "onClick: Extra added");
//                context.startActivity(intent);
//                }
            }
        };


        class ViewHolder extends RecyclerView.ViewHolder {
            //            final TextView mIdView;
            private TextView textViewComplaint;
            private TextView textViewComplaintId;
            private TextView textViewTime;


            ViewHolder(View view) {
                super(view);
//                mIdView = (TextView) view.findViewById(R.id.id_text);
                textViewComplaint = view.findViewById(R.id.complaint);
                textViewComplaintId = view.findViewById(R.id.complaint_id);
                textViewTime = view.findViewById(R.id.complaint_time);

            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.complaint_view_small, parent, false);
            view.setOnClickListener(mOnClickListener);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mIdView.setText(mValues.get(position).id);
//            holder.mContentView.setText(mValues.get(position).content);
//            holder.itemView.setTag(mValues.get(position));
//            holder.itemView.setOnClickListener(mOnClickListener);
            MyDataset listItem = listItems.get(position);
            holder.itemView.setTag(listItem);
            holder.textViewComplaint.setText(listItem.getQuery());
            holder.textViewTime.setText(listItem.getTime());
            holder.textViewComplaintId.setText(listItem.getComplaintIdString());
        }

        @Override
        public int getItemCount() {
            return listItems.size();
        }
    }
}
