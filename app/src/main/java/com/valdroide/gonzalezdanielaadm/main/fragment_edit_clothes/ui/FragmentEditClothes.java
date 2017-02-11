package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.UtteranceProgressListener;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmApp;
import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.FragmentEditClothesPresenter;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.adapters.ActivityRecyclerAdapter;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.adapters.AdapterSpinnerCategory;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.adapters.AdapterSpinnerSubCategory;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.adapters.OnItemClickListener;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.di.FragmentEditClothesComponent;
import com.valdroide.gonzalezdanielaadm.main.tab_fragment.ui.TabActivity;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentEditClothes extends Fragment implements FragmentEditClothesView, OnItemClickListener {

    @Bind(R.id.spinnerCategory)
    Spinner spinnerCategory;
    @Bind(R.id.spinnerSubCategory)
    Spinner spinnerSubCategory;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.conteiner)
    FrameLayout conteiner;

    @Inject
    FragmentEditClothesPresenter presenter;
    @Inject
    AdapterSpinnerCategory adapterSpinner;
    @Inject
    AdapterSpinnerSubCategory adapterSpinnerSub;
    @Inject
    ActivityRecyclerAdapter adapter;

    List<Category> categories = new ArrayList<>();
    List<SubCategory> subCategories = new ArrayList<>();
    List<Clothes> clothesList = new ArrayList<>();
    private FragmentEditClothesComponent component;
    private int id_category, id_sub_category;
    private ProgressDialog pDialog;
    private int id_delete;


    public FragmentEditClothes() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        setupInjection();
        presenter.onCreate();
        presenter.getListCategory();
        initAdapterCategory();
        initAdapterSubCategory();
        setOnItemSelectedCategory();
        setOnItemSelectedSubCategory();
        initAdapterRecycler();
        initRecyclerViewAdapter();
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Procesando...");
        pDialog.setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_clothes, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initRecyclerViewAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
    }

    public void refresh() {
        presenter.getListClothes(id_category, id_sub_category);
    }

    private void setupInjection() {
        GonzalezDanielaAdmApp app = (GonzalezDanielaAdmApp) getActivity().getApplication();
        component = app.getFragmentEditClothesComponent(this, this, this);
        presenter = component.getPresenter();
    }

    private void initAdapterCategory() {
        adapterSpinner = new AdapterSpinnerCategory(getActivity(), R.layout.support_simple_spinner_dropdown_item, categories);
        adapterSpinner.setNotifyOnChange(true);
        spinnerCategory.setAdapter(adapterSpinner);
    }

    private void initAdapterRecycler() {
        adapter = new ActivityRecyclerAdapter(clothesList, this, this);

    }

    private void initAdapterSubCategory() {
        adapterSpinnerSub = new AdapterSpinnerSubCategory(getActivity(), R.layout.simple_spinner_dropdown_item, subCategories);
        adapterSpinnerSub.setNotifyOnChange(true);
        spinnerSubCategory.setAdapter(adapterSpinnerSub);
    }

    @Override
    public void setListCategoty(List<Category> categories) {
        if (categories.isEmpty())
            categories.add(new Category(0, getString(R.string.spinner_category_empty)));
        this.categories = categories;
    }

    public void setOnItemSelectedCategory() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_category = categories.get(position).getID_CATEGORY_KEY();
                presenter.getListSubCategory(id_category);
                initAdapterSubCategory();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setOnItemSelectedSubCategory() {
        spinnerSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_sub_category = subCategories.get(position).getID_SUBCATEGORY_KEY();
                presenter.getListClothes(id_category, id_sub_category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setListSubCategoryForCategory(List<SubCategory> subcategories) {
        if (subcategories.isEmpty())
            subcategories.add(new SubCategory(0, getString(R.string.spinner_subcategory_empty)));
        this.subCategories = subcategories;
    }

    @Override
    public void error(String mgs) {
        pDialog.hide();
        Utils.showSnackBar(conteiner, mgs);
    }

    @Override
    public void setListClothes(List<Clothes> clothesList) {
        this.clothesList = clothesList;
        adapter.setCategories(clothesList);
    }

    @Override
    public void deleteSuccess(Clothes clothes) {
        pDialog.hide();
        adapter.removeClothes(clothes);
        Utils.showSnackBar(conteiner, getActivity().getResources().getString(R.string.msg_delete_clothes_success));
    }

    @Override
    public void clickSwitchSuccess(Clothes clothes) {
        pDialog.hide();
        adapter.updateAdapter(clothes);
        Utils.showSnackBar(conteiner, getActivity().getResources().getString(R.string.msg_edit_clothes_success));
    }

    @Override
    public void onClick(View view, int position) {
        intentEditClothes(this.clothesList.get(position));
    }

    @Override
    public void onLongClick(View view, int position) {
        getActivity().openContextMenu(view);
        id_delete = position;
    }

    @Override
    public void onClickSwitch(Clothes clothes) {
        pDialog.show();
        int isactive = clothes.getISACTIVE() == 0 ? 1 : 0;
        clothes.setISACTIVE(isactive);
        presenter.clickSwitch(clothes, new DateTable(Utils.CLOTHES, Utils.getFechaOficial()));
    }

    public void intentEditClothes(Clothes clothes) {

        Intent intent = new Intent(getActivity(), TabActivity.class);
        intent.putExtra("update", true);
        intent.putExtra("id", clothes.getID_CLOTHES_KEY());
        intent.putExtra("description", clothes.getDESCRIPTION());
        intent.putExtra("id_category", clothes.getID_CATEGORY());
        intent.putExtra("id_sub_category", clothes.getID_SUBCATEGORY());
        intent.putExtra("is_activo", clothes.getISACTIVE());
        intent.putExtra("photo_name", clothes.getNAME_PHOTO());
        intent.putExtra("url", clothes.getURL_PHOTO());

        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_delete, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            pDialog.show();
            presenter.deleteClothes(clothesList.get(id_delete), new DateTable(Utils.CLOTHES, Utils.getFechaOficial()));
            Utils.showSnackBar(conteiner, "Ola");
        }
        return true;
    }
}
