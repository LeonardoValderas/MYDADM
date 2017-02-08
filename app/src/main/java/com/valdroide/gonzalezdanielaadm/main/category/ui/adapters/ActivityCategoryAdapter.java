package com.valdroide.gonzalezdanielaadm.main.category.ui.adapters;

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
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.main.category.ui.ActivityCategory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityCategoryAdapter extends RecyclerView.Adapter<ActivityCategoryAdapter.ViewHolder> {

    private List<Category> categoriesList;
    private OnItemClickListener onItemClickListener;

    private Activity activity;
    //   private Category category;

    public ActivityCategoryAdapter(List<Category> categoriesList, OnItemClickListener onItemClickListener, Activity activity) {
        this.categoriesList = categoriesList;
        this.onItemClickListener = onItemClickListener;
        this.activity = (ActivityCategory) activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_categories, parent, false);
        return new ViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = categoriesList.get(position);

        holder.setOnItemClickListener(category, onItemClickListener, position);
        holder.textViewGeneral.setText(category.getCATEGORY());
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public void removeCategory(Category category) {
        categoriesList.remove(category);
        notifyDataSetChanged();
    }

    public void setCategories(List<Category> categories) {
        this.categoriesList = categories;
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

        public void setOnItemClickListener(final Category category,
                                           final OnItemClickListener listener, final int position) {
            imageButtonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textViewGeneral.setVisibility(View.GONE);
                    linearButtons.setVisibility(View.GONE);
                    linearButtonSave.setVisibility(View.VISIBLE);
                    editTextEdit.setVisibility(View.VISIBLE);
                    editTextEdit.setText(category.getCATEGORY());

                }
            });
            imageButtonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editTextEdit.getText().toString().isEmpty())
                        listener.editEmpty();
                    else {
                        category.setCATEGORY(editTextEdit.getText().toString());
                        textViewGeneral.setVisibility(View.VISIBLE);
                        linearButtons.setVisibility(View.VISIBLE);
                        linearButtonSave.setVisibility(View.GONE);
                        editTextEdit.setText("");
                        editTextEdit.setVisibility(View.GONE);

                        listener.onClickEdit(category);

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
                    listener.onClickDelete(category, position);

                }
            });
        }
    }


    public void updateAdapter(Category category) {
        for (int i = 0; i < this.categoriesList.size(); i++) {
            if (this.categoriesList.get(i).getID_CATEGORY_KEY() == category.getID_CATEGORY_KEY())
                this.categoriesList.get(i).setCATEGORY(category.getCATEGORY());
            break;
        }
        notifyDataSetChanged();
    }

    public void addAdapter(Category category) {
        this.categoriesList.add(0, category);
        notifyDataSetChanged();
    }
}
