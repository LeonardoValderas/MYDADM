package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;

public interface OnItemClickListener {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
    void onClickSwitch(Clothes clothes);
}
