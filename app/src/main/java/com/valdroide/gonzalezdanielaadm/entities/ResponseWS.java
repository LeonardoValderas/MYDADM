package com.valdroide.gonzalezdanielaadm.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LEO on 9/2/2017.
 */

public class ResponseWS {
    @SerializedName("success")
    @Expose
    String success;
    @SerializedName("message")
    @Expose
    String message;

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
}
