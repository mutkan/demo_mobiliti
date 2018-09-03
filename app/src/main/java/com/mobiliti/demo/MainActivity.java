package com.mobiliti.demo;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mobiliti.demo.adapter.VehiclesListRecyclerViewAdapter;
import com.mobiliti.demo.rest.ServiceGenerator;
import com.mobiliti.demo.rest.model.vehicles.Vehicle;
import com.mobiliti.demo.rest.service.VehicleService;
import com.mobiliti.demo.util.Singleton;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private VehiclesListRecyclerViewAdapter mVehiclesListRecyclerViewAdapter;
    private RecyclerView rv_vehicleslist;
    private MaterialSearchView msv_vehicleslist;
    private Toolbar tb_vehicleslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialViews();
        setAdapter();

        getVehicles();
    }

    void setInitialViews() {
        tb_vehicleslist = findViewById(R.id.tb_vehicleslist);
        setSupportActionBar(tb_vehicleslist);

        rv_vehicleslist = findViewById(R.id.rv_vehicleslist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_vehicleslist.setLayoutManager(linearLayoutManager);

        msv_vehicleslist = findViewById(R.id.msv_vehicleslist);
        msv_vehicleslist.setVoiceSearch(false);
        msv_vehicleslist.setEllipsize(true);
        msv_vehicleslist.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mVehiclesListRecyclerViewAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mVehiclesListRecyclerViewAdapter.filter(newText);
                return false;
            }
        });
    }

    void setAdapter() {
        mVehiclesListRecyclerViewAdapter = new VehiclesListRecyclerViewAdapter();
        rv_vehicleslist.setAdapter(mVehiclesListRecyclerViewAdapter);
    }

    private void getVehicles() {
        VehicleService client = ServiceGenerator.createService(VehicleService.class);
        Call<List<Vehicle>> call = client.getVehicles();
        call.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                if (response.isSuccessful()) {
                    List<Vehicle> vehicles = response.body();
                    if (vehicles != null && vehicles.size() > 0) {
                        mVehiclesListRecyclerViewAdapter.setList(vehicles);
                        Singleton.getInstance().setmVehiclesList(vehicles);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Unexpected code " + response, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        msv_vehicleslist.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (msv_vehicleslist.isSearchOpen()) {
            msv_vehicleslist.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
