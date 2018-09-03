package com.mobiliti.demo.util;

import android.util.SparseArray;

import com.mobiliti.demo.rest.model.vehicles.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Singleton {
    private static volatile Singleton mInstance;
    private List<Vehicle> mVehiclesList;
    private Map<Integer, Vehicle> mVehiclesMap;

    private Singleton() {
        if (mInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static Singleton getInstance() {
        if (mInstance == null) {
            synchronized (Singleton.class) {
                if (mInstance == null) {
                    mInstance = new Singleton();
                }
            }
        }
        return mInstance;
    }

    public List<Vehicle> getmVehiclesList() {
        return mVehiclesList;
    }

    public void setmVehiclesList(List<Vehicle> mVehiclesList) {
        this.mVehiclesList = mVehiclesList;
        this.mVehiclesMap = new HashMap<>();
    }

    public void setmVehiclesMap(Map<Integer, Vehicle> mVehiclesMap) {
        this.mVehiclesMap = mVehiclesMap;
    }

    public Map<Integer, Vehicle> getmVehiclesMap() {
        if (mVehiclesList != null && mVehiclesList.size() > 0) {
            mVehiclesMap.clear();
            for(Vehicle v: mVehiclesList){
                mVehiclesMap.put(v.getVehicleId(),v);
            }
        }
        return mVehiclesMap;
    }
}