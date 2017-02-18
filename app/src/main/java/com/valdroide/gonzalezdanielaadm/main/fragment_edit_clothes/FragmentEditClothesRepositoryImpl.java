package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes;

import android.content.Context;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.Clothes_Table;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.ResponseWS;
import com.valdroide.gonzalezdanielaadm.entities.Result;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory_Table;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.events.FragmentEditClothesEvent;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentEditClothesRepositoryImpl implements FragmentEditClothesRepository {
    private EventBus eventBus;
    private List<Category> categories;
    private List<SubCategory> subCategories;
    private List<Clothes> clothesList;
    private APIService service;
    final String[] success = {""};
    final String[] message = {""};

    public FragmentEditClothesRepositoryImpl(EventBus eventBus, APIService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getListCategory() {
        categories = SQLite.select().from(Category.class).where().orderBy(new NameAlias("CATEGORY"), true).queryList();
        if (categories != null)
            post(FragmentEditClothesEvent.GETLISTCATEGORY, categories);
        else
            post(FragmentEditClothesEvent.ERROR, "Error en la base de datos.");
    }

    @Override
    public void getListSubCategory(int id_category) {
        ConditionGroup conditionGroup = ConditionGroup.clause();
        conditionGroup.and(Condition.column(new NameAlias("ID_CATEGORY")).is(id_category));
        subCategories = SQLite.select().from(SubCategory.class).where(conditionGroup).orderBy(new NameAlias("SUBCATEGORY"), true).queryList();

        if (subCategories != null)
            post(FragmentEditClothesEvent.GETLISTSUBCATEGORY, subCategories, true);
        else
            post(FragmentEditClothesEvent.ERROR, "Error en la base de datos.");
    }

    @Override
    public void getListClothes(int id_category, int id_sub_category) {
        ConditionGroup conditionGroup = ConditionGroup.clause();
        conditionGroup.and(Condition.column(new NameAlias("Clothes.ID_CATEGORY")).is(id_category));
        conditionGroup.and(Condition.column(new NameAlias("Clothes.ID_SUBCATEGORY")).is(id_sub_category));

        clothesList = SQLite.select()
                .from(Clothes.class).innerJoin(SubCategory.class)
                .on(Clothes_Table.ID_SUBCATEGORY.withTable().eq(SubCategory_Table.ID_SUBCATEGORY_KEY.withTable()))
                .where(conditionGroup).orderBy(new NameAlias("SUBCATEGORY"), true).queryList();

        if (clothesList != null)
            post(FragmentEditClothesEvent.GETLISTCLOTHES, 0, clothesList);
        else
            post(FragmentEditClothesEvent.ERROR, "Error en la base de datos.");
    }

    @Override
    public void deleteClothes(Context context, final Clothes clothes, final DateTable dateTable) {
        if (Utils.isNetworkAvailable(context)) {
            try {
                Call<Result> clothesService = service.deleteClothes(clothes.getID_CLOTHES_KEY(), clothes.getNAME_PHOTO(), dateTable.getDATE());
                clothesService.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()) {
                            message[0] = response.body().getMessage();
                            success[0] = response.body().getSuccess();

                            if (success[0].equals("0")) {
                                clothes.delete();
                                if (Utils.dateTables() != null) {
                                    if (Utils.dateTables().size() <= 0) {
                                        Utils.switchTable();
                                    }
                                }
                                Utils.updateDateTable(dateTable);
                                post(FragmentEditClothesEvent.DELETE, clothes);
                            } else {
                                post(FragmentEditClothesEvent.ERROR, message[0]);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        post(FragmentEditClothesEvent.ERROR, t.getMessage());
                    }
                });
            } catch (Exception e) {
                post(FragmentEditClothesEvent.ERROR, e.getMessage());
            }
        } else {
            post(FragmentEditClothesEvent.ERROR, "Verificar su conexión de Internet.");
        }
    }

    @Override
    public void clickSwitch(Context context, final Clothes clothes, final DateTable dateTable) {
        if (Utils.isNetworkAvailable(context)) {
            try {
                Call<Result> clothesService = service.updateActiveClothes(clothes.getID_CLOTHES_KEY(), clothes.getISACTIVE(), dateTable.getDATE());
                clothesService.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()) {
                            message[0] = response.body().getMessage();
                            success[0] = response.body().getSuccess();

                            if (success[0].equals("0")) {
                                clothes.update();
                                if (Utils.dateTables() != null) {
                                    if (Utils.dateTables().size() <= 0) {
                                        Utils.switchTable();
                                    }
                                }
                                Utils.updateDateTable(dateTable);
                                post(FragmentEditClothesEvent.ACTIVE, clothes);
                            } else {
                                post(FragmentEditClothesEvent.ERROR, message[0]);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        post(FragmentEditClothesEvent.ERROR, t.getMessage());
                    }
                });
            } catch (Exception e) {
                post(FragmentEditClothesEvent.ERROR, e.getMessage());
            }
        } else {
            post(FragmentEditClothesEvent.ERROR, "Verificar su conexión de Internet.");
        }
    }

    public void post(int type) {
        post(type, null, null, null, null, null);
    }

    public void post(int type, List<Category> categories) {
        post(type, categories, null, null, null, null);
    }

    public void post(int type, List<SubCategory> subCategories, boolean isSub) {
        post(type, null, subCategories, null, null, null);
    }

    public void post(int type, int nothing, List<Clothes> clothesList) {
        post(type, null, null, clothesList, null, null);
    }

    public void post(int type, Clothes clothes) {
        post(type, null, null, null, null, clothes);
    }

    public void post(int type, String error) {
        post(type, null, null, null, error, null);
    }

    public void post(int type, List<Category> categories, List<SubCategory> subCategories, List<Clothes> clothesList, String error, Clothes clothes) {
        FragmentEditClothesEvent event = new FragmentEditClothesEvent();
        event.setType(type);
        event.setListCategories(categories);
        event.setListSubCategories(subCategories);
        event.setClothesList(clothesList);
        event.setError(error);
        event.setClothes(clothes);
        eventBus.post(event);

    }
//
//    encodedImage = Base64.encodeToString(imageComision,
//    Base64.DEFAULT);
}
