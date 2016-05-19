package com.major.dustincontroller.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Faiz on 19-05-2016.
 */
public class Location {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("latitude")
    @Expose
    Double latitude;

    @SerializedName("longitude")
    @Expose
    Double longitude;

    @SerializedName("location_time")
    @Expose
    String locationTime;

    @SerializedName("status")
    @Expose
    Boolean status;

    @SerializedName("message")
    @Expose
    String message;

    public Location() {
    }

    public Location(String id, Double latitude, Double longitude, String locationTime, Boolean status, String message) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationTime = locationTime;
        this.status = status;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(String locationTime) {
        this.locationTime = locationTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
