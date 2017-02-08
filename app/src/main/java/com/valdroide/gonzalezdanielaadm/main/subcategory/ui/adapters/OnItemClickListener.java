package com.valdroide.gonzalezdanielaadm.main.subcategory.ui.adapters;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;

public interface OnItemClickListener {
    void onClickEdit(SubCategory subcategory);
    void onClickDelete(SubCategory subcategory, int position);
    void editEmpty();
}
