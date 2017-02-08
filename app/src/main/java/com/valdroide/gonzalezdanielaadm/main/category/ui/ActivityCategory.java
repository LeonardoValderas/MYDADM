package com.valdroide.gonzalezdanielaadm.main.category.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmApp;
import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.main.category.ActivityCategoryPresenter;
import com.valdroide.gonzalezdanielaadm.main.category.ui.adapters.ActivityCategoryAdapter;
import com.valdroide.gonzalezdanielaadm.main.category.ui.adapters.OnItemClickListener;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityCategory extends AppCompatActivity implements OnItemClickListener, ActivityCategoryView {

    @Bind(R.id.editTextCategory)
    EditText editTextCategory;
    @Bind(R.id.conteiner)
    LinearLayout conteiner;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    ActivityCategoryPresenter presenter;
    @Bind(R.id.imageButtonSave)
    ImageButton imageButtonSave;
    private List<Category> categories;
    @Inject
    ActivityCategoryAdapter adapter;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        setupInjection();

        presenter.onCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CATEGORIA");

        presenter.getListCategory();
        initRecyclerViewAdapter();
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
        app.getActivityCategoryComponent(this, this, this).inject(this);
    }

    @OnClick(R.id.imageButtonSave)
    public void saveCategory() {
        if (editTextCategory.getText().toString().isEmpty())
            Utils.showSnackBar(conteiner, getString(R.string.msg_empty_category_adapter));
        else {
            pDialog.show();
            presenter.saveCategory(new Category(0, editTextCategory.getText().toString()),new DateTable(Utils.CATEGORY, Utils.getFechaOficial()));
        }
    }

    @Override
    public void onClickEdit(Category category) {
        pDialog.show();
        presenter.editCategory(category, new DateTable(Utils.CATEGORY, Utils.getFechaOficial()));
    }

    @Override
    public void onClickDelete(Category category, int position) {
        pDialog.show();
        presenter.deleteCategory(category, new DateTable(Utils.CATEGORY, Utils.getFechaOficial()));
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
        adapter.setCategories(categories);
    }

    @Override
    public void saveSuccess(Category category) {
        pDialog.hide();
        adapter.addAdapter(category);
        editTextCategory.setText("");
        Utils.showSnackBar(conteiner, getString(R.string.msg_save_categoty_success));
    }

    @Override
    public void updateSuccess(Category category) {
        pDialog.hide();
        adapter.updateAdapter(category);
        Utils.showSnackBar(conteiner, getString(R.string.msg_edit_categoty_success));
    }

    @Override
    public void deleteSuccess(Category category) {
        pDialog.hide();
        adapter.removeCategory(category);
        Utils.showSnackBar(conteiner, getString(R.string.msg_delete_categoty_success));
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
