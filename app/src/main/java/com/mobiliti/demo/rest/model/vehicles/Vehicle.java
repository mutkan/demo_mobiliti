
package com.mobiliti.demo.rest.model.vehicles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle {

    @SerializedName("is_favorite")
    @Expose
    private Integer isFavorite;
    @SerializedName("dealer_state")
    @Expose
    private String dealerState;
    @SerializedName("dealer_zip")
    @Expose
    private String dealerZip;
    @SerializedName("category_id")
    @Expose
    private Object categoryId;
    @SerializedName("vin")
    @Expose
    private String vin;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("vehicle_id")
    @Expose
    private Integer vehicleId;
    @SerializedName("vehicle_list_id")
    @Expose
    private Object vehicleListId;
    @SerializedName("subscription_price")
    @Expose
    private Integer subscriptionPrice;
    @SerializedName("list_name")
    @Expose
    private String listName;
    @SerializedName("dealer_city")
    @Expose
    private String dealerCity;
    @SerializedName("dealer_name")
    @Expose
    private String dealerName;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("vehicle_title")
    @Expose
    private String vehicleTitle;
    @SerializedName("dealer_address")
    @Expose
    private String dealerAddress;
    @SerializedName("vehicle_status")
    @Expose
    private Integer vehicleStatus;
    @SerializedName("vehicle_image")
    @Expose
    private String vehicleImage;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("dealer_id")
    @Expose
    private Integer dealerId;
    @SerializedName("vehicle_details")
    @Expose
    private Object vehicleDetails;
    @SerializedName("vehicle_url")
    @Expose
    private Object vehicleUrl;

    public Integer getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Integer isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getDealerState() {
        return dealerState;
    }

    public void setDealerState(String dealerState) {
        this.dealerState = dealerState;
    }

    public String getDealerZip() {
        return dealerZip;
    }

    public void setDealerZip(String dealerZip) {
        this.dealerZip = dealerZip;
    }

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Object getVehicleListId() {
        return vehicleListId;
    }

    public void setVehicleListId(Object vehicleListId) {
        this.vehicleListId = vehicleListId;
    }

    public Integer getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public void setSubscriptionPrice(Integer subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDealerCity() {
        return dealerCity;
    }

    public void setDealerCity(String dealerCity) {
        this.dealerCity = dealerCity;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getVehicleTitle() {
        return vehicleTitle;
    }

    public void setVehicleTitle(String vehicleTitle) {
        this.vehicleTitle = vehicleTitle;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public Integer getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(Integer vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public Object getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(Object vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public Object getVehicleUrl() {
        return vehicleUrl;
    }

    public void setVehicleUrl(Object vehicleUrl) {
        this.vehicleUrl = vehicleUrl;
    }

}
