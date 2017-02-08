package com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmApp;
import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.main.category.ui.ActivityCategory;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.Communicator;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.FragmentAddClothesPresenter;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.di.FragmentAddClothesComponent;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.adapters.AdapterSpinnerCategory;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.adapters.AdapterSpinnerSubCategory;
import com.valdroide.gonzalezdanielaadm.main.notification.ui.NotificationActivity;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.ActivitySubCategory;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentAddClothes extends Fragment implements FragmentAddClothesView {


    @Bind(R.id.spinnerCategory)
    Spinner spinnerCategory;
    @Bind(R.id.spinnerSubCategory)
    Spinner spinnerSubCategory;
    @Bind(R.id.imageViewClothes)
    ImageView imageViewClothes;
    @Bind(R.id.editTextDescription)
    EditText editTextDescription;
    @Bind(R.id.conteiner)
    FrameLayout conteiner;

    @Inject
    FragmentAddClothesPresenter presenter;
    @Inject
    AdapterSpinnerCategory adapterSpinner;
    @Inject
    AdapterSpinnerSubCategory adapterSpinnerSub;
    List<Category> categories = new ArrayList<>();
    List<SubCategory> subCategories = new ArrayList<>();
    private FragmentAddClothesComponent component;
    private int id_category;
    public static final int PERMISSION_GALERY = 1;
    public static final int GALERY = 1;
    private byte[] imageByte;
    private Clothes clothes;
    private int id_category_save = 0;
    private int id_subcategory_save = 0;
    private Category category;
    private SubCategory subCategory;
    private Communicator communicator;
    private boolean update = false;
    private ProgressDialog pDialog;

    private String name_before = "";

    public FragmentAddClothes() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        communicator = (Communicator) getActivity();
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
        clothes = new Clothes();
        isUpdate();
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Procesando...");
        pDialog.setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_clothes, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setupInjection() {
        GonzalezDanielaAdmApp app = (GonzalezDanielaAdmApp) getActivity().getApplication();
        component = app.getFragmentAddClothesComponent(this, this);
        presenter = component.getPresenter();
    }

    private void isUpdate() {
        update = getActivity().getIntent().getBooleanExtra("update", false);
        if (update) {
            clothes.setID_CLOTHES_KEY(getActivity().getIntent().getIntExtra("id", 0));
            clothes.setDESCRIPTION(getActivity().getIntent().getStringExtra("description"));
            clothes.setID_CATEGORY(getActivity().getIntent().getIntExtra("id_category", 0));
            clothes.setID_SUBCATEGORY(getActivity().getIntent().getIntExtra("id_sub_category", 0));
            clothes.setISACTIVE(getActivity().getIntent().getBooleanExtra("is_activo", false));
            name_before = getActivity().getIntent().getStringExtra("photo_name");
            clothes.setNAME_PHOTO(name_before);
            clothes.setURL_PHOTO(getActivity().getIntent().getStringExtra("url"));

            fillClothesUpdate(clothes);
        }
    }

    private void fillClothesUpdate(Clothes clothes) {
        spinnerCategory.setSelection(getPositionSpinner(clothes.getID_CATEGORY(), true));
        spinnerSubCategory.setSelection(getPositionSpinner(clothes.getID_SUBCATEGORY(), false));
        Utils.setPicasso(getActivity(), clothes.getURL_PHOTO(), R.mipmap.ic_imge_clothes, imageViewClothes);
        editTextDescription.setText(clothes.getDESCRIPTION());
    }

    private int getPositionSpinner(int id, boolean isCategory) {
        int index = 0;
        if (isCategory) {
            for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).getID_CATEGORY_KEY() == (id)) {
                    index = i;
                }
            }
        } else {
            for (int i = 0; i < subCategories.size(); i++) {
                if (subCategories.get(i).getID_SUBCATEGORY_KEY() == (id)) {
                    index = i;
                }
            }
        }
        return index;
    }

    private void initAdapterCategory() {
        adapterSpinner = new AdapterSpinnerCategory(getActivity(), R.layout.support_simple_spinner_dropdown_item, categories);
        adapterSpinner.setNotifyOnChange(true);
        spinnerCategory.setAdapter(adapterSpinner);
    }

    private void initAdapterSubCategory() {
        adapterSpinnerSub = new AdapterSpinnerSubCategory(getActivity(), R.layout.support_simple_spinner_dropdown_item, subCategories);
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

    @Override
    public void setListSubCategoryForCategory(List<SubCategory> subCategories) {
        if (subCategories.isEmpty())
            subCategories.add(new SubCategory(0, getString(R.string.spinner_subcategory_empty)));
        this.subCategories = subCategories;
    }

    @Override
    @OnClick(R.id.imageViewClothes)
    public void getPhoto() {
        if (!Utils.oldPhones())
            checkForPermission();

        if (hasPermission())
            ImageDialogComision();
    }

    @Override
    public void error(String mgs) {
        pDialog.hide();
        Utils.showSnackBar(conteiner, mgs);
    }

    @Override
    public void saveSuccess() {
        pDialog.hide();
        communicator.refresh();
        cleanView();
        Utils.showSnackBar(conteiner, getString(R.string.msg_save_clothes_success));
    }

    @Override
    public void updateSuccess() {
        pDialog.hide();
        communicator.refresh();
        cleanView();
        Utils.showSnackBar(conteiner, getString(R.string.msg_edit_clothes_success));
    }

    private void checkForPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_GALERY);
        }
    }

    private boolean hasPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_GALERY)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ImageDialogComision();
            }
    }

    public void ImageDialogComision() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
                getActivity());
        myAlertDialog.setTitle("Galeria");
        myAlertDialog.setMessage("Seleccione una foto.");

        myAlertDialog.setPositiveButton("Galeria",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent pickIntent = new Intent(
                                Intent.ACTION_GET_CONTENT, null);
                        pickIntent.setType("image/*");
                        pickIntent.putExtra(
                                "return-data", true);
                        startActivityForResult(
                                pickIntent,
                                GALERY);
                    }
                });
        myAlertDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALERY) {
            Uri imageUri = CropImage.getPickImageResultUri(getActivity(), data);
            startCropImageActivity(imageUri);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == getActivity().RESULT_OK) {

                Utils.setPicasso(getActivity(), result.getUri().toString(), R.mipmap.ic_imge_clothes, imageViewClothes);
                try {
                    imageByte = Utils.readBytes(result.getUri(), getActivity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                if (!result.getError().toString().contains("ENOENT"))
                    Utils.showSnackBar(conteiner, "Error al asignar imagen: " + result.getError());
            }
        }
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(getContext(), FragmentAddClothes.this);
    }

    public Clothes fillClothe(int id, boolean isActive, boolean update) {
        clothes.setID_CATEGORY(id_category_save);
        clothes.setID_SUBCATEGORY(id_subcategory_save);
        clothes.setDESCRIPTION(editTextDescription.getText().toString());
        clothes.setISACTIVE(isActive);

        String encodedImage = "";

        if(imageByte != null) {
            try {
                encodedImage = Base64.encodeToString(imageByte,
                        Base64.DEFAULT);
            } catch (Exception e) {
                encodedImage = "";
            }
            String phto_name = Utils.getFechaOficial();
            clothes.setURL_PHOTO(Utils.URL_IMAGE + phto_name +".PNG");
            clothes.setNAME_PHOTO(phto_name+".PNG");
            clothes.setNAME_BEFORE(name_before);
            clothes.setENCODEBYTE(encodedImage);
        }

        if(update)
        if (id != 0)
            clothes.setID_CLOTHES_KEY(id);

        return clothes;
    }

    public void cleanView() {
        editTextDescription.setText("");
        imageViewClothes.setImageResource(R.mipmap.ic_imge_clothes);
        name_before = "";
        imageByte = null;
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
//        menu.getItem(1).setVisible(false);// posicion
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_save) {

            if (spinnerCategory.getSelectedItem().toString().equals(getResources().getString(R.string.spinner_category_empty)))
                Utils.showSnackBar(conteiner, getString(R.string.msg_empty_category));
            else if (spinnerSubCategory.getSelectedItem().toString().equals(getResources().getString(R.string.spinner_subcategory_empty)))
                Utils.showSnackBar(conteiner, getString(R.string.msg_empty_subcategory));
            else if (imageByte == null && !update)
                Utils.showSnackBar(conteiner, getString(R.string.msg_empty_image));
            else if (editTextDescription.getText().toString().isEmpty())
                Utils.showSnackBar(conteiner, getString(R.string.msg_empty_description));
            else {
                category = (Category) spinnerCategory.getSelectedItem();
                subCategory = (SubCategory) spinnerSubCategory.getSelectedItem();
                id_category_save = category.getID_CATEGORY_KEY();
                id_subcategory_save = subCategory.getID_SUBCATEGORY_KEY();

                if (!update) {
                    pDialog.show();
                    presenter.saveClothe(fillClothe(0, true, update), new DateTable(Utils.CLOTHES, Utils.getFechaOficial()));
                } else {
                    pDialog.show();
                    presenter.updateClothe(fillClothe(clothes.getID_CLOTHES_KEY(), clothes.getISACTIVE(), update), new DateTable(Utils.CLOTHES, Utils.getFechaOficial()));
                }
            }

        } else if (id == R.id.add_category) {
            startActivity(new Intent(getActivity(), ActivityCategory.class));
        } else if (id == R.id.add_sub_category) {
            startActivity(new Intent(getActivity(), ActivitySubCategory.class));
        } else if (id == R.id.add_notification) {
            startActivity(new Intent(getActivity(), NotificationActivity.class));
        }

        return true;

    }
}
