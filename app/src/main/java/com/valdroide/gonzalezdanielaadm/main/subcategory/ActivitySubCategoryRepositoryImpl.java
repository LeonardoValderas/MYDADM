package com.valdroide.gonzalezdanielaadm.main.subcategory;


import android.content.Context;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.Result;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.category.events.ActivityCategoryEvent;
import com.valdroide.gonzalezdanielaadm.main.subcategory.events.ActivitySubCategoryEvent;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivitySubCategoryRepositoryImpl implements ActivitySubCategoryRepository {
    private EventBus eventBus;
    private List<Category> categories;
    private List<SubCategory> subcategories;
    private APIService service;
    final String[] success = {""};
    final String[] message = {""};
    final int[] id = {0};

    public ActivitySubCategoryRepositoryImpl(EventBus eventBus, APIService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getListCategory() {
        categories = SQLite.select().from(Category.class).where().orderBy(new NameAlias("CATEGORY"), true).queryList();
        if (categories != null)
            post(ActivityCategoryEvent.GETLISTCATEGORY, categories, true);
    }


    @Override
    public void editSubCategory(Context context, final SubCategory subcategory, final DateTable dateTable) {
        if (Utils.isNetworkAvailable(context)) {
            try {
                ConditionGroup conditionGroup = ConditionGroup.clause();
                conditionGroup.and(Condition.column(new NameAlias("SUBCATEGORY")).is(subcategory.getSUBCATEGORY()));
                conditionGroup.and(Condition.column(new NameAlias("ID_CATEGORY")).is(subcategory.getID_CATEGORY()));
                if (!SQLite.select().from(SubCategory.class).where(conditionGroup).hasData()) {
                    Call<Result> categoryService = service.updateSubCategory(subcategory.getID_SUBCATEGORY_KEY(), subcategory.getSUBCATEGORY(), subcategory.getID_CATEGORY(), dateTable.getDATE());
                    categoryService.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            if (response.isSuccessful()) {
                                message[0] = response.body().getMessage();
                                success[0] = response.body().getSuccess();

                                if (success[0].equals("0")) {

                                    subcategory.update();
                                    if (Utils.dateTables() != null) {
                                        if (Utils.dateTables().size() <= 0) {
                                            Utils.switchTable();
                                        }
                                    }
                                    Utils.updateDateTable(dateTable);
                                    post(ActivitySubCategoryEvent.UPDATESUBCATEGORY, subcategory);
                                } else {
                                    post(ActivitySubCategoryEvent.ERROR, message[0]);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            post(ActivitySubCategoryEvent.ERROR, t.getMessage());
                        }
                    });

                } else {
                    post(ActivitySubCategoryEvent.ERROR, "Ya existe la SubCategoria para esta Categoria.");
                }
            } catch (Exception e) {
                post(ActivitySubCategoryEvent.ERROR, e.getMessage());
            }
        } else {
            post(ActivitySubCategoryEvent.ERROR, "Verificar su conexión de Internet.");
        }
    }

    @Override
    public void saveSubCategory(Context context, final SubCategory subcategory, final DateTable dateTable) {
        if (Utils.isNetworkAvailable(context)) {
            try {
                ConditionGroup conditionGroup = ConditionGroup.clause();
                conditionGroup.and(Condition.column(new NameAlias("SUBCATEGORY")).is(subcategory.getSUBCATEGORY()));
                conditionGroup.and(Condition.column(new NameAlias("ID_CATEGORY")).is(subcategory.getID_CATEGORY()));
                if (!SQLite.select().from(SubCategory.class).where(conditionGroup).hasData()) {
                    Call<Result> categoryService = service.insertSubCategory(subcategory.getSUBCATEGORY(), subcategory.getID_CATEGORY(), dateTable.getDATE());
                    categoryService.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            if (response.isSuccessful()) {
                                message[0] = response.body().getMessage();
                                success[0] = response.body().getSuccess();
                                id[0] = response.body().getId();

                                if (success[0].equals("0")) {
                                    if (id[0] != 0) {
                                        subcategory.setID_SUBCATEGORY_KEY(id[0]);
                                        subcategory.save();
                                        if (Utils.dateTables() != null) {
                                            if (Utils.dateTables().size() <= 0) {
                                                Utils.switchTable();
                                            }
                                        }
                                        Utils.updateDateTable(dateTable);
                                        post(ActivitySubCategoryEvent.SAVESUBCATEGORY, subcategory);
                                    } else {
                                        post(ActivitySubCategoryEvent.ERROR, "Error al guardar la subcategoria");
                                    }
                                } else {
                                    post(ActivitySubCategoryEvent.ERROR, message[0]);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            post(ActivitySubCategoryEvent.ERROR, t.getMessage());
                        }
                    });
                } else {
                    post(ActivitySubCategoryEvent.ERROR, "Ya existe la SubCategoria para esta Categoria.");
                }
            } catch (Exception e) {
                post(ActivitySubCategoryEvent.ERROR, e.getMessage());
            }
        } else {
            post(ActivitySubCategoryEvent.ERROR, "Verificar su conexión de Internet.");
        }
    }

    @Override
    public void deleteSubCategory(Context context, final SubCategory subcategory, final DateTable dateTable) {
        if (Utils.isNetworkAvailable(context)) {
            try {
                Call<Result> categoryService = service.deleteSubCategory(subcategory.getID_SUBCATEGORY_KEY(), dateTable.getDATE());
                categoryService.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()) {
                            message[0] = response.body().getMessage();
                            success[0] = response.body().getSuccess();

                            if (success[0].equals("0")) {
                                subcategory.delete();
                                if (Utils.dateTables() != null) {
                                    if (Utils.dateTables().size() <= 0) {
                                        Utils.switchTable();
                                    }
                                }
                                Utils.updateDateTable(dateTable);
                                post(ActivitySubCategoryEvent.DELETESUBCATEGORY, subcategory);
                            } else {
                                post(ActivitySubCategoryEvent.ERROR, message[0]);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        post(ActivitySubCategoryEvent.ERROR, t.getMessage());
                    }
                });
            } catch (Exception e) {
                post(ActivitySubCategoryEvent.ERROR, e.getMessage());
            }
        } else {
            post(ActivitySubCategoryEvent.ERROR, "Verificar su conexión de Internet.");
        }
    }

    @Override
    public void getListSubCategoryForCategory(int id_category) {
        ConditionGroup conditionGroup = ConditionGroup.clause();
        conditionGroup.and(Condition.column(new NameAlias("ID_CATEGORY")).is(id_category));
        subcategories = SQLite.select().from(SubCategory.class).where(conditionGroup).orderBy(new NameAlias("SUBCATEGORY"), true).queryList();

        if (subcategories != null)
            post(ActivitySubCategoryEvent.GETLISTSUBCATEGORY, subcategories);
    }

    public void post(int type) {
        post(type, null, null, null, null);
    }

    public void post(int type, String error) {
        post(type, null, null, error, null);
    }

    public void post(int type, List<Category> categories, boolean isCategory) {
        post(type, null, null, null, categories);
    }

    public void post(int type, List<SubCategory> subcategories) {
        post(type, subcategories, null, null, null);
    }

    public void post(int type, SubCategory subcategory) {
        post(type, null, subcategory, null, null);
    }

    public void post(int type, List<SubCategory> subcategories, SubCategory subcategory, String error, List<Category> categories) {
        ActivitySubCategoryEvent event = new ActivitySubCategoryEvent();
        event.setType(type);
        event.setListSubCategories(subcategories);
        event.setSubcategory(subcategory);
        event.setError(error);
        event.setListCategories(categories);

        eventBus.post(event);
    }
}
