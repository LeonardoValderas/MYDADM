package com.valdroide.gonzalezdanielaadm.main.category.ui.adapters;

import android.view.View;

import com.valdroide.gonzalezdanielaadm.entities.Category;

public interface OnItemClickListener {
    void onClickEdit(Category category);
    void onClickDelete(Category category, int position);
    void editEmpty();
}
