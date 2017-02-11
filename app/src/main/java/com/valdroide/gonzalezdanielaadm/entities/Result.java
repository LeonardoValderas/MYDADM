package com.valdroide.gonzalezdanielaadm.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    @SerializedName("date_table")
    @Expose
    List<DateTable> date_table;
    @SerializedName("category")
    @Expose
    List<Category> category;
    @SerializedName("subcategory")
    @Expose
    List<SubCategory> subcategory;
    @SerializedName("clothes")
    @Expose
    List<Clothes> clothes;
    @SerializedName("responseData")
    @Expose
    List<ResponseWS> responseData;




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

    public List<DateTable> getDate_table() {
        return date_table;
    }

    public void setDate_table(List<DateTable> date_table) {
        this.date_table = date_table;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<SubCategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<SubCategory> subcategory) {
        this.subcategory = subcategory;
    }

    public List<Clothes> getClothes() {
        return clothes;
    }

    public void setClothes(List<Clothes> clothes) {
        this.clothes = clothes;
    }

    public List<ResponseWS> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<ResponseWS> responseData) {
        this.responseData = responseData;
    }
}
