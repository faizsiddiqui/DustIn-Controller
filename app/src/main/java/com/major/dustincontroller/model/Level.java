package com.major.dustincontroller.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Faiz on 19-05-2016.
 */
public class Level {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("level_status")
    @Expose
    String levelStatus;

    @SerializedName("level_time")
    @Expose
    String levelTime;

    @SerializedName("status")
    @Expose
    Boolean status;

    @SerializedName("message")
    @Expose
    String message;

    public Level() {
    }

    public Level(String id, String levelStatus, String levelTime, Boolean status, String message) {
        this.id = id;
        this.levelStatus = levelStatus;
        this.levelTime = levelTime;
        this.status = status;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevelStatus() {
        return levelStatus;
    }

    public void setLevelStatus(String levelStatus) {
        this.levelStatus = levelStatus;
    }

    public String getLevelTime() {
        return levelTime;
    }

    public void setLevelTime(String levelTime) {
        this.levelTime = levelTime;
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
