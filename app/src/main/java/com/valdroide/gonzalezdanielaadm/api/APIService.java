package com.valdroide.gonzalezdanielaadm.api;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Result;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIService {
    @GET("md")
    Call<List<Category>> getCategories();

    //CATEGORY
    @FormUrlEncoded
    @POST("category/insertCategory.php")
    Call<Result> insertCategory(@Field("category") String category, @Field("table_date") String table_date);

    @FormUrlEncoded
    @POST("category/updateCategory.php")
    Call<Result> updateCategory(@Field("id_category") int id_category, @Field("category") String category, @Field("table_date") String table_date);

    @FormUrlEncoded
    @POST("category/deleteCategory.php")
    Call<Result> deleteCategory(@Field("id_category") int id_category, @Field("table_date") String table_date);

    //SUBCATEGORY
    @FormUrlEncoded
    @POST("subcategory/insertSubCategory.php")
    Call<Result> insertSubCategory(@Field("subcategory") String subcategory, @Field("id_category") int id_category, @Field("table_date") String table_date);

    @FormUrlEncoded
    @POST("subcategory/updateSubCategory.php")
    Call<Result> updateSubCategory(@Field("id_subcategory") int id_subcategory, @Field("subcategory") String subcategory, @Field("id_category") int id_category, @Field("table_date") String table_date);

    @FormUrlEncoded
    @POST("subcategory/deleteSubCategory.php")
    Call<Result> deleteSubCategory(@Field("id_subcategory") int id_subcategory, @Field("table_date") String table_date);

    //CLOTHES
    @FormUrlEncoded
    @POST("clothes/insertClothes.php")
    Call<Result> insertClothes(@Field("id_category") int id_category, @Field("id_subcategory") int id_subcategory, @Field("url_photo") String url_photo, @Field("name_photo") String name_photo, @Field("description") String description, @Field("is_active") boolean is_active, @Field("encode") String encode, @Field("table_date") String table_date);

    @FormUrlEncoded
    @POST("clothes/updateClothes.php")
    Call<Result> updateClothes(@Field("id_clothes") int id_clothes, @Field("id_category") int id_category, @Field("id_subcategory") int id_subcategory, @Field("url_photo") String url_photo, @Field("name_photo") String name_photo, @Field("description") String description, @Field("is_active") boolean is_active, @Field("encode") String encode, @Field("name_before") String name_before, @Field("table_date") String table_date);

    @FormUrlEncoded
    @POST("clothes/deleteClothes.php")
    Call<Result> deleteClothes(@Field("id_clothes") int id_clothes, @Field("name_photo") String name_photo, @Field("table_date") String table_date);

    @FormUrlEncoded
    @POST("clothes/updateActiveClothes.php")
    Call<Result> updateActiveClothes(@Field("id_clothes") int id_clothes, @Field("is_active") boolean is_active, @Field("table_date") String table_date);

    //NOTIFICATION
    @FormUrlEncoded
    @POST("notification/sendNotification.php")
    Call<Result> sendNotification(@Field("title") String title, @Field("content") String content);

}
