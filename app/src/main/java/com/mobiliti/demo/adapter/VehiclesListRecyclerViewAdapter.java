package com.mobiliti.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobiliti.demo.R;
import com.mobiliti.demo.rest.model.vehicles.Vehicle;
import com.mobiliti.demo.views.activity.VehicleDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class VehiclesListRecyclerViewAdapter extends
        RecyclerView.Adapter<VehiclesListRecyclerViewAdapter.VehiclesListViewHolder> {
    private List<Vehicle> mResults, mResultsCopy;

    @Override
    public VehiclesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_vehicleslist_item, parent, false);
        final VehiclesListViewHolder viewHolder = new VehiclesListViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();

                Intent intent = new Intent(view.getContext(), VehicleDetail.class);
                intent.putExtra("vehicle_id",mResults.get(position).getVehicleId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VehiclesListViewHolder holder, int position) {
        if (mResults != null) {
            Vehicle result = mResults.get(position);
            Context context = holder.imgv_vehicleslist_cardview_img.getContext();

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(R.drawable.default_car);
            Glide.with(context)
                    .load(result.getVehicleImage())
                    .apply(requestOptions)
                    .into(holder.imgv_vehicleslist_cardview_img);
            if (result.getSubscriptionPrice() != null) {
                holder.txtv_vehicleslist_car_fee.setText(context.getString(R.string.fee, result.getSubscriptionPrice()));
            }
            if (result.getVehicleTitle() != null) {
                holder.txtv_vehicleslist_car_model.setText(result.getVehicleTitle());
            }
        }
    }

    @Override
    public int getItemCount() {
        return (mResults == null) ? 0 : mResults.size();
    }

    public class VehiclesListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgv_vehicleslist_cardview_img;
        TextView txtv_vehicleslist_car_fee, txtv_vehicleslist_car_model;

        VehiclesListViewHolder(View itemView) {
            super(itemView);
            imgv_vehicleslist_cardview_img = itemView.findViewById(R.id.imgv_vehicleslist_cardview_img);
            txtv_vehicleslist_car_fee = itemView.findViewById(R.id.txtv_vehicleslist_car_fee);
            txtv_vehicleslist_car_model = itemView.findViewById(R.id.txtv_vehicleslist_car_model);
        }
    }

    public void setList(List<Vehicle> resultList) {
        mResults = new ArrayList<>();
        mResultsCopy = new ArrayList<>();
        mResults.addAll(resultList);
        mResultsCopy.addAll(resultList);
        notifyDataSetChanged();
    }

    public void filter(String searchText) {
        mResults.clear();
        if (searchText.isEmpty()) {
            mResults.addAll(mResultsCopy);
        } else {
            searchText = searchText.toLowerCase();
            for (Vehicle vehicle : mResultsCopy) {
                if (vehicle.getVehicleTitle().toLowerCase(Locale.ROOT).contains(searchText) || vehicle.getDealerCity().toLowerCase(Locale.ROOT).contains(searchText)) {
                    mResults.add(vehicle);
                }
            }
        }
        notifyDataSetChanged();
    }
}
