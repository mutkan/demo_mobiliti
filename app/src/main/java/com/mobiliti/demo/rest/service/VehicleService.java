package com.mobiliti.demo.rest.service;

import com.mobiliti.demo.rest.model.vehicles.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VehicleService {
    @GET("/api/vehicles")
    Call<List<Vehicle>> getVehicles();
}
