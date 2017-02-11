package com.valdroide.gonzalezdanielaadm.main.category;


import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.db.ClothesDatabase;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.Result;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.category.events.ActivityCategoryEvent;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.events.FragmentEditClothesEvent;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityCategoryRepositoryImpl implements ActivityCategoryRepository {
    private EventBus eventBus;
    private List<Category> categories;
    private APIService service;
    final String[] success = {""};
    final String[] message = {""};
    final int[] id = {0};

    public ActivityCategoryRepositoryImpl(EventBus eventBus, APIService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getListCategory() {
        categories = SQLite.select().from(Category.class).where().orderBy(new NameAlias("CATEGORY"), true).queryList();
        if (categories != null)
            post(ActivityCategoryEvent.GETLISTCATEGORY, categories);
        else
            post(FragmentEditClothesEvent.ERROR, "Error en la base de datos.");
    }

    @Override
    public void editCategory(final Category category, final DateTable dateTable) {
        try {
            Call<Result> categoryService = service.updateCategory(category.getID_CATEGORY_KEY(), category.getCATEGORY(), dateTable.getDATE());
            categoryService.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (response.isSuccessful()) {
                        message[0] = response.body().getMessage();
                        success[0] = response.body().getSuccess();

                        if (success[0].equals("0")) {
                            category.update();
                            if (Utils.dateTables() != null) {
                                if (Utils.dateTables().size() <= 0) {
                                    Utils.switchTable();
                                }
                            }
                            Utils.updateDateTable(dateTable);
                            post(ActivityCategoryEvent.UPDATECATEGORY, category);
                        } else {
                            post(ActivityCategoryEvent.ERROR, message[0]);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    post(ActivityCategoryEvent.ERROR, t.getMessage());
                }
            });
        } catch (Exception e) {
            post(ActivityCategoryEvent.ERROR, e.getMessage());
        }
    }


    @Override
    public void saveCategory(final Category category,
                             final DateTable dateTable) {
        try {
            Call<Result> categoryService = service.insertCategory(category.getCATEGORY(), dateTable.getDATE());
            categoryService.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (response.isSuccessful()) {
                        message[0] = response.body().getMessage();
                        success[0] = response.body().getSuccess();
                        id[0] = response.body().getId();

                        if (success[0].equals("0")) {
                            if (id[0] != 0) {
                                category.setID_CATEGORY_KEY(id[0]);
                                category.save();
                                if (Utils.dateTables() != null) {
                                    if (Utils.dateTables().size() <= 0) {
                                        Utils.switchTable();
                                    }
                                }
                                Utils.updateDateTable(dateTable);
                                post(ActivityCategoryEvent.SAVECATEGORY, category);
                            } else {
                                post(ActivityCategoryEvent.ERROR, "Error al guardar la categoria");
                            }
                        } else {
                            post(ActivityCategoryEvent.ERROR, message[0]);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    post(ActivityCategoryEvent.ERROR, t.getMessage());
                }
            });
        } catch (Exception e) {
            post(ActivityCategoryEvent.ERROR, e.getMessage());
        }
    }

    @Override
    public void deleteCategory(final Category category, final DateTable dateTable) {
        try {
            Call<Result> categoryService = service.deleteCategory(category.getID_CATEGORY_KEY(), dateTable.getDATE());
            categoryService.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (response.isSuccessful()) {
                        message[0] = response.body().getMessage();
                        success[0] = response.body().getSuccess();

                        if (success[0].equals("0")) {
                            category.delete();
                            if (Utils.dateTables() != null) {
                                if (Utils.dateTables().size() <= 0) {
                                    Utils.switchTable();
                                }
                            }
                            Utils.updateDateTable(dateTable);
                            post(ActivityCategoryEvent.DELETECATEGORY, category);
                        } else {
                            post(ActivityCategoryEvent.ERROR, message[0]);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    post(ActivityCategoryEvent.ERROR, t.getMessage());
                }
            });
        } catch (Exception e) {
            post(ActivityCategoryEvent.ERROR, e.getMessage());
        }
    }

    public void post(int type) {
        post(type, null, null, null);
    }

    public void post(int type, String error) {
        post(type, null, error, null);
    }

    public void post(int type, List<Category> categories) {
        post(type, categories, null, null);
    }

    public void post(int type, Category category) {
        post(type, null, null, category);
    }

    public void post(int type, List<Category> categories, String error, Category
            category) {
        ActivityCategoryEvent event = new ActivityCategoryEvent();
        event.setType(type);
        event.setListCategories(categories);
        event.setError(error);
        event.setCategory(category);
        eventBus.post(event);

    }
}
