package com.valdroide.gonzalezdanielaadm.main.subcategory.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.entities.Category;

import java.util.List;

public class AdapterSpinnerCategory extends ArrayAdapter<Category> {
    private Context context;
    private List<Category> categoriesArray = null;
    private int resource;

    public AdapterSpinnerCategory(Context context, int resource, List<Category> categoriesArray) {
        super(context, resource, categoriesArray);
        this.context = context;
        this.resource = resource;
        this.categoriesArray = categoriesArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
          //  LayoutInflater inflater = context.getLayoutInflater();
            LayoutInflater inflater = LayoutInflater.from(context);

            //      row = inflater.inflate(resource, parent, false);
            row = inflater.inflate(R.layout.spinner_item, parent, false);
        }
        Category category = categoriesArray.get(position);

        if (category != null) {
            TextView textViewCategory = (TextView) row.findViewById(R.id.textViewGeneral);
            if (textViewCategory != null)
                textViewCategory.setText(category.getCATEGORY());
        }
        return row;
    }

    public void setCategoriesArray(List<Category> categories) {
        this.categoriesArray = categories;
        notifyDataSetChanged();
    }
}

