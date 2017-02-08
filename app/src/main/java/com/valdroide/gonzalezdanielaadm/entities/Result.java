package com.valdroide.gonzalezdanielaadm.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LEO on 6/2/2017.
 */

public class Result {
    @SerializedName("success")
    @Expose
    String success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("id")
    @Expose
    int id;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
