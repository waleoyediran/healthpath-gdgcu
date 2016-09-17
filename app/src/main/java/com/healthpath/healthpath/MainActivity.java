package com.healthpath.healthpath;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://reprohealth.devomatics.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HealthService healthService = retrofit.create(HealthService.class);

        Call<List<Hospital>> hospitalQuery = healthService.getHospitals("6.6724581,3.1582081");


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        hospitalQuery.enqueue(new Callback<List<Hospital>>() {
            @Override
            public void onResponse(Call<List<Hospital>> call, Response<List<Hospital>> response) {
                if (response.isSuccessful()){
                    // specify an adapter (see also next example)
                    mAdapter = new MyAdapter(MainActivity.this, response.body());
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Snackbar.make(mRecyclerView, "An Error Occured", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Hospital>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static List<Hospital> dummyHospitals(){
        List<Hospital> list = new ArrayList<>();
        Hospital h1 = new Hospital();
        h1.name = "Covenant University Health Center";
        list.add(h1);

        Hospital h2 = new Hospital();
        h2.name = "University Health Center";
        list.add(h2);

        Hospital h3 = new Hospital();
        h3.name = "My Health Center 2";
        list.add(h3);

        Hospital h4 = new Hospital();
        h4.name = "My Health Center 3";
        list.add(h4);

        Hospital h5 = new Hospital();
        h5.name = "My Health Center 4";
        list.add(h5);

        Hospital h6 = new Hospital();
        h6.name = "My Health Center 5";
        list.add(h6);

        Hospital h7 = new Hospital();
        h7.name = "My Health Center 6";
        list.add(h7);

        return list;
    }
}
