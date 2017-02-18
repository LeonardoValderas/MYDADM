package com.valdroide.gonzalezdanielaadm.main.subcategory.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmApp;
import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.adapters.AdapterSpinnerCategory;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ActivitySubCategoryPresenter;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.adapters.ActivitySubCategoryAdapter;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.adapters.OnItemClickListener;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitySubCategory extends AppCompatActivity implements OnItemClickListener, ActivitySubCategoryView {

    @Bind(R.id.editTextCategory)
    EditText editTextCategory;
    @Bind(R.id.conteiner)
    LinearLayout conteiner;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.imageButtonSave)
    ImageButton imageButtonSave;
    @Bind(R.id.spinnerCategory)
    Spinner spinnerCategory;
    @Bind(R.id.linearCategorySpinner)
    LinearLayout linearCategorySpinner;
    private List<Category> categories;
    @Inject
    ActivitySubCategoryAdapter adapter;
    @Inject
    AdapterSpinnerCategory adapterSpinner;
    @Inject
    ActivitySubCategoryPresenter presenter;
    private Category category;
    int id_category;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        setupInjection();

        presenter.onCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("SUBCATEGORIA");
        linearCategorySpinner.setVisibility(View.VISIBLE);
        presenter.getListCategory();
        initRecyclerViewAdapter();
        setOnItemSelectedCategory();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Procesando...");
        pDialog.setCancelable(false);
    }

    public void initRecyclerViewAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        GonzalezDanielaAdmApp app = (GonzalezDanielaAdmApp) getApplication();
        app.getActivitySubCategoryComponent(this, this, this, this).inject(this);
    }

    @OnClick(R.id.imageButtonSave)
    public void saveCategory() {
        if (spinnerCategory.getSelectedItem().toString().equals(getResources().getString(R.string.spinner_category_empty)))
            Utils.showSnackBar(conteiner, getString(R.string.msg_empty_category));
        else if (editTextCategory.getText().toString().isEmpty())
            Utils.showSnackBar(conteiner, getString(R.string.msg_empty_subcategory_adapter));
        else {
            pDialog.show();
            category = (Category) spinnerCategory.getSelectedItem();
            presenter.saveSubCategory(this, new SubCategory(0, editTextCategory.getText().toString(), category.getID_CATEGORY_KEY()), new DateTable(Utils.SUBCATEGORY, Utils.getFechaOficial()));
        }
    }

    public void setOnItemSelectedCategory() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_category = categories.get(position).getID_CATEGORY_KEY();
                presenter.getListSubCategoryForCategory(id_category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClickEdit(SubCategory subcategory) {
        pDialog.show();
        presenter.editSubCategory(this, subcategory, new DateTable(Utils.SUBCATEGORY, Utils.getFechaOficial()));
    }

    @Override
    public void onClickDelete(SubCategory subcategory, int position) {
        pDialog.show();
        presenter.deleteSubCategory(this, subcategory, new DateTable(Utils.SUBCATEGORY, Utils.getFechaOficial()));
    }

    @Override
    public void editEmpty() {
        Utils.showSnackBar(conteiner, getString(R.string.msg_empty_category_adapter));
    }

    @Override
    public void getCategory() {
        presenter.getListCategory();
    }

    @Override
    public void setCategory(List<Category> categories) {
        this.categories = categories;
        adapterSpinner = new AdapterSpinnerCategory(this, R.layout.support_simple_spinner_dropdown_item, categories);
        adapterSpinner.setNotifyOnChange(true);
        spinnerCategory.setAdapter(adapterSpinner);
    }

    @Override
    public void setSubCategoryForCategory(List<SubCategory> subcategories) {
        adapter.setSubCategories(subcategories);
    }

    @Override
    public void saveSuccess(SubCategory subcategory) {
        pDialog.hide();
        adapter.adddAdapter(subcategory);
        editTextCategory.setText("");
        Utils.showSnackBar(conteiner, getString(R.string.msg_save_subcategory_success));
    }

    @Override
    public void updateSuccess(SubCategory subcategory) {
        pDialog.hide();
        adapter.updateAdapter(subcategory);
        Utils.showSnackBar(conteiner, getString(R.string.msg_edit_subcategory_success));
    }

    @Override
    public void deleteSuccess(SubCategory subcategory) {
        pDialog.hide();
        adapter.removeSubCategory(subcategory);
        Utils.showSnackBar(conteiner, getString(R.string.msg_delete_subcategory_success));
    }

    @Override
    public void error(String msg) {
        pDialog.hide();
        Utils.showSnackBar(conteiner, msg);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
