package com.valdroide.gonzalezdanielaadm.main.subcategory.ui.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.ActivitySubCategory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivitySubCategoryAdapter extends RecyclerView.Adapter<ActivitySubCategoryAdapter.ViewHolder> {

    private List<SubCategory> subcategoriesList;
    private OnItemClickListener onItemClickListener;

    private Activity activity;
    //   private Category category;

    public ActivitySubCategoryAdapter(List<SubCategory> subcategoriesList, OnItemClickListener onItemClickListener, Activity activity) {
        this.subcategoriesList = subcategoriesList;
        this.onItemClickListener = onItemClickListener;
        this.activity = (ActivitySubCategory) activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_categories, parent, false);
        return new ViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubCategory subcategory = subcategoriesList.get(position);

        holder.setOnItemClickListener(subcategory, onItemClickListener, position);
        holder.textViewGeneral.setText(subcategory.getSUBCATEGORY());
    }

    @Override
    public int getItemCount() {
        return subcategoriesList.size();
    }

    public void removeSubCategory(SubCategory subcategory) {
        subcategoriesList.remove(subcategory);
        notifyDataSetChanged();
    }

    public void setSubCategories(List<SubCategory> subcategories) {
        this.subcategoriesList = subcategories;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textViewGeneral)
        TextView textViewGeneral;
        @Bind(R.id.editTextEdit)
        EditText editTextEdit;
        @Bind(R.id.imageButtonEdit)
        ImageButton imageButtonEdit;
        @Bind(R.id.imageButtonDelete)
        ImageButton imageButtonDelete;
        @Bind(R.id.linearButtons)
        LinearLayout linearButtons;
        @Bind(R.id.imageButtonSave)
        ImageButton imageButtonSave;
        @Bind(R.id.linearButtonSave)
        LinearLayout linearButtonSave;
        @Bind(R.id.imageButtonCancel)
        ImageButton imageButtonCancel;

        public ViewHolder(View view, Activity activity) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final SubCategory subcategory,
                                           final OnItemClickListener listener, final int position) {
            imageButtonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textViewGeneral.setVisibility(View.GONE);
                    linearButtons.setVisibility(View.GONE);
                    linearButtonSave.setVisibility(View.VISIBLE);
                    editTextEdit.setVisibility(View.VISIBLE);
                    editTextEdit.setText(subcategory.getSUBCATEGORY());

                }
            });
            imageButtonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editTextEdit.getText().toString().isEmpty())
                        listener.editEmpty();
                    else {
                        subcategory.setSUBCATEGORY(editTextEdit.getText().toString());
                        textViewGeneral.setVisibility(View.VISIBLE);
                        linearButtons.setVisibility(View.VISIBLE);
                        linearButtonSave.setVisibility(View.GONE);
                        editTextEdit.setText("");
                        editTextEdit.setVisibility(View.GONE);

                        listener.onClickEdit(subcategory);

                    }
                }
            });
            imageButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textViewGeneral.setVisibility(View.VISIBLE);
                    linearButtons.setVisibility(View.VISIBLE);
                    linearButtonSave.setVisibility(View.GONE);
                    editTextEdit.setText("");
                    editTextEdit.setVisibility(View.GONE);

                }
            });
            imageButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickDelete(subcategory, position);

                }
            });
        }
    }

    public void updateAdapter(SubCategory subcategory) {
        for (int i = 0; i < this.subcategoriesList.size(); i++) {
            if (this.subcategoriesList.get(i).getID_SUBCATEGORY_KEY() == subcategory.getID_SUBCATEGORY_KEY())
                this.subcategoriesList.get(i).setSUBCATEGORY(subcategory.getSUBCATEGORY());
            break;
        }
        notifyDataSetChanged();
    }

    public void adddAdapter(SubCategory subcategory) {
        this.subcategoriesList.add(0, subcategory);
        notifyDataSetChanged();
    }
}
