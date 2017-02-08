package com.valdroide.gonzalezdanielaadm.main.category.events;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;

import java.util.List;

/**
 * Created by LEO on 30/1/2017.
 */
public class ActivityCategoryEvent {
    private int type;
    private String error;
    public static final int GETLISTCATEGORY = 0;
    public static final int SAVECATEGORY = 1;
    public static final int UPDATECATEGORY = 2;
    public static final int DELETECATEGORY = 3;
    public static final int ERROR = 4;

    private List<Category> listCategories;
    private Category category;

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
}
