package com.mobiliti.demo.views.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.mobiliti.demo.MainApp;
import com.mobiliti.demo.R;
import com.mobiliti.demo.rest.model.vehicles.Vehicle;
import com.mobiliti.demo.util.MainUtils;
import com.mobiliti.demo.util.Singleton;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VehicleDetail extends AppCompatActivity {
    private int mVehicleId;
    private Vehicle mVehicle;
    private Map<Integer, Boolean> mFavoriteMap;
    private String mJson;

    private Toolbar tb_vehicledetail;
    private TextView txtv_vehicledetail_model, txtv_vehicledetail_mile, txtv_vehicledetail_fee,
            txtv_vehicledetail_dealername, txtv_vehicledetail_dealeraddress;
    private ImageView imgv_vehicledetail_head;
    private LikeButton lb_vehicledetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicledetail);

        mVehicleId = getIntent().getIntExtra("vehicle_id", 0);
        mVehicle = Singleton.getInstance().getmVehiclesMap().get(mVehicleId);

        setInitialDatas();
        setInitialViews();
        setListeners();
        setVehicleDetail();
    }

    void setInitialDatas() {
        mJson = MainUtils.readJson();
        if (!mJson.isEmpty()) {
            mFavoriteMap = MainUtils.getFavoriteMapFromJson(mJson);
        } else {
            mFavoriteMap = new HashMap<>();
        }
    }

    void setInitialViews() {
        tb_vehicledetail = findViewById(R.id.tb_vehicledetail);
        setSupportActionBar(tb_vehicledetail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tb_vehicledetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        txtv_vehicledetail_model = findViewById(R.id.txtv_vehicledetail_model);
        txtv_vehicledetail_mile = findViewById(R.id.txtv_vehicledetail_mile);
        txtv_vehicledetail_fee = findViewById(R.id.txtv_vehicledetail_fee);
        txtv_vehicledetail_dealername = findViewById(R.id.txtv_vehicledetail_dealername);
        txtv_vehicledetail_dealeraddress = findViewById(R.id.txtv_vehicledetail_dealeraddress);
        imgv_vehicledetail_head = findViewById(R.id.imgv_vehicledetail_head);
        lb_vehicledetail = findViewById(R.id.lb_vehicledetail);
    }

    void setListeners() {
        lb_vehicledetail.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mFavoriteMap.put(mVehicleId, likeButton.isLiked());
                MainUtils.saveJson(MainUtils.setFavoriteMapToJson(mFavoriteMap));

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mFavoriteMap.put(mVehicleId, likeButton.isLiked());
                MainUtils.saveJson(MainUtils.setFavoriteMapToJson(mFavoriteMap));
            }
        });
    }

    void setVehicleDetail() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.drawable.default_car);
        Glide.with(this)
                .load(mVehicle.getVehicleImage())
                .apply(requestOptions)
                .into(imgv_vehicledetail_head);
        txtv_vehicledetail_model.setText(mVehicle.getVehicleTitle());
        txtv_vehicledetail_mile.setText(getString(R.string.mile, mVehicle.getCount()));
        txtv_vehicledetail_fee.setText(getString(R.string.fee, mVehicle.getSubscriptionPrice()));
        txtv_vehicledetail_dealername.setText(mVehicle.getDealerName());
        txtv_vehicledetail_dealeraddress.setText(mVehicle.getDealerAddress());
        boolean isLiked = false;
        if (mFavoriteMap != null && mFavoriteMap.get(mVehicleId) != null) {
            isLiked = mFavoriteMap.get(mVehicleId);
        }
        lb_vehicledetail.setLiked(isLiked);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
