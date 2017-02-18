package com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes;

import android.content.Context;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.Result;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.events.FragmentAddClothesEvent;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAddClothesRepositoryImpl implements FragmentAddClothesRepository {
    private EventBus eventBus;
    private List<Category> categories;
    private List<SubCategory> subCategories;
    private APIService service;
    final String[] success = {""};
    final String[] message = {""};
    final int[] id = {0};

    public FragmentAddClothesRepositoryImpl(EventBus eventBus, APIService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getListCategory() {
        categories = SQLite.select().from(Category.class).where().orderBy(new NameAlias("CATEGORY"), true).queryList();
        if (categories != null)
            post(FragmentAddClothesEvent.GETLISTCATEGORY, categories);
    }

    @Override
    public void getListSubCategory(int id_category) {
        ConditionGroup conditionGroup = ConditionGroup.clause();
        conditionGroup.and(Condition.column(new NameAlias("ID_CATEGORY")).is(id_category));
        subCategories = SQLite.select().from(SubCategory.class).where(conditionGroup).orderBy(new NameAlias("SUBCATEGORY"), true).queryList();

        if (subCategories != null)
            post(FragmentAddClothesEvent.GETLISTSUBCATEGORY, subCategories, true);
    }

    @Override
    public void updateClothe(Context context, final Clothes clothe, final DateTable dateTable) {
        if (Utils.isNetworkAvailable(context)) {
            try {
                Call<Result> clothesService = service.updateClothes(clothe.getID_CLOTHES_KEY(), clothe.getID_CATEGORY(), clothe.getID_SUBCATEGORY(), clothe.getURL_PHOTO(), clothe.getNAME_PHOTO(), clothe.getDESCRIPTION(), clothe.getISACTIVE(), clothe.getENCODEBYTE(), clothe.getNAME_BEFORE(), dateTable.getDATE());
                clothesService.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()) {
                            message[0] = response.body().getMessage();
                            success[0] = response.body().getSuccess();
                            if (success[0].equals("0")) {
                                clothe.update();
                                if (Utils.dateTables() != null) {
                                    if (Utils.dateTables().size() <= 0) {
                                        Utils.switchTable();
                                    }
                                }
                                Utils.updateDateTable(dateTable);
                                post(FragmentAddClothesEvent.UPDATECLOTHE);
                            } else {
                                post(FragmentAddClothesEvent.ERROR, message[0]);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        post(FragmentAddClothesEvent.ERROR, t.getMessage());
                    }
                });
            } catch (Exception e) {
                post(FragmentAddClothesEvent.ERROR, e.getMessage());
            }
        } else {
            post(FragmentAddClothesEvent.ERROR, "Verificar su conexión de Internet.");
        }
    }

    @Override
    public void saveClothe(Context context, final Clothes clothe, final DateTable dateTable) {
        if (Utils.isNetworkAvailable(context)) {
            try {
                Call<Result> clothesService = service.insertClothes(clothe.getID_CATEGORY(), clothe.getID_SUBCATEGORY(), clothe.getURL_PHOTO(), clothe.getNAME_PHOTO(), clothe.getDESCRIPTION(), clothe.getISACTIVE(), clothe.getENCODEBYTE(), dateTable.getDATE());
                clothesService.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()) {
                            message[0] = response.body().getMessage();
                            success[0] = response.body().getSuccess();
                            id[0] = response.body().getId();

                            if (success[0].equals("0")) {
                                if (id[0] != 0) {
                                    clothe.setID_CLOTHES_KEY(id[0]);
                                    clothe.setENCODEBYTE("");
                                    clothe.setNAME_BEFORE("");
                                    clothe.save();
                                    if (Utils.dateTables() != null) {
                                        if (Utils.dateTables().size() <= 0) {
                                            Utils.switchTable();
                                        }
                                    }
                                    Utils.updateDateTable(dateTable);
                                    post(FragmentAddClothesEvent.SAVECLOTHE);
                                } else {
                                    post(FragmentAddClothesEvent.ERROR, "Error al guardar el producto.");
                                }
                            } else {
                                post(FragmentAddClothesEvent.ERROR, message[0]);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        post(FragmentAddClothesEvent.ERROR, t.getMessage());
                    }
                });
            } catch (Exception e) {
                post(FragmentAddClothesEvent.ERROR, e.getMessage());
            }
        } else {
            post(FragmentAddClothesEvent.ERROR, "Verificar su conexión de Internet.");
        }
    }

    public void post(int type) {
        post(type, null, null, null);
    }

    public void post(int type, String error) {
        post(type, null, null, error);
    }

    public void post(int type, List<Category> categories) {
        post(type, categories, null, null);
    }

    public void post(int type, List<SubCategory> subCategories, boolean isSub) {
        post(type, null, subCategories, null);
    }

    public void post(int type, List<Category> categories, List<SubCategory> subCategories, String error) {
        FragmentAddClothesEvent event = new FragmentAddClothesEvent();
        event.setType(type);
        event.setError(error);
        event.setListCategories(categories);
        event.setListSubCategories(subCategories);
        eventBus.post(event);

    }

}
