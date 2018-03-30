package com.epoch.android.complaintlog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {

    private static final String TAG = "ItemDetailActivity";

    private TextView textViewComplaintId;
    private TextView textViewComplaint;
    private TextView textViewTime;
    private Button buttonMarkResolved;
    private Spinner spinnerForward;
    private static final String URL_RESOLVED = "0.0.0.0";
    Activity activity = new Activity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_view_full);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, "onCreate: "+TAG);

        Bundle bundle = getIntent().getExtras();
        final MyDataset item = (MyDataset) getIntent().getSerializableExtra("MyDataset");

        textViewComplaint = findViewById(R.id.complaint_text_full);
        textViewTime = findViewById(R.id.complaint_time_full);
        textViewComplaintId = findViewById(R.id.complaint_id_full);

        textViewComplaint.setText(item.getQuery());
        textViewComplaintId.setText(item.getComplaintIdString());
        textViewTime.setText(item.getTime());

        buttonMarkResolved = findViewById(R.id.button_mark_resolved);
        spinnerForward = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerForward.setAdapter(adapter);

        spinnerForward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ItemDetailActivity.this, "Forwarding to " + adapterView.getItemIdAtPosition(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonMarkResolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Context context = view.getContext();
//                Intent intent = new Intent(context, ItemListActivity.class);
//                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.getComplaintIdString());
                markResolved();
                item.complaintId = 100;
                NavUtils.navigateUpFromSameTask(ItemDetailActivity.this);

            }
        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // Show the Up button in the action bar.
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
//                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));
//            ItemDetailFragment fragment = new ItemDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.item_detail_container, fragment)
//                    .commit();
//        }
    }

    private void markResolved() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_RESOLVED,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            // This ID represents the Home or Up button. In the case of this
//            // activity, the Up button is shown. For
//            // more details, see the Navigation pattern on Android Design:
//            //
//            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
//            //
//            navigateUpTo(new Intent(this, ItemListActivity.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
