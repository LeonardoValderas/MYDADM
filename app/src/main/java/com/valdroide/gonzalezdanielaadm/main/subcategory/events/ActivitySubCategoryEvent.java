package com.valdroide.gonzalezdanielaadm.main.subcategory.events;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;

import java.util.List;

/**
 * Created by LEO on 30/1/2017.
 */
public class ActivitySubCategoryEvent {
    private int type;
    private String error;
    public static final int GETLISTCATEGORY = 0;
    public static final int GETLISTSUBCATEGORY = 1;
    public static final int SAVESUBCATEGORY = 2;
    public static final int UPDATESUBCATEGORY = 3;
    public static final int DELETESUBCATEGORY = 4;
    public static final int ERROR = 5;

    private List<Category> listCategories;
    private List<SubCategory> listSubCategories;
    private Category category;
    private SubCategory subcategory;
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Category> getListCategories() {
        return listCategories;
    }

    public void setListCategories(List<Category> listCategories) {
        this.listCategories = listCategories;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<SubCategory> getListSubCategories() {
        return listSubCategories;
    }

    public void setListSubCategories(List<SubCategory> listSubCategories) {
        this.listSubCategories = listSubCategories;
    }

    public SubCategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }
}
