package com.car_dealer.dtos.view;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarSaleView {

    @Expose
    @SerializedName("Make")
    private String make;
    @Expose
    @SerializedName("Model")
    private String model;
    @Expose
    @SerializedName("TravelledDistance")
    private String travelledDistance;

    public CarSaleView() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
