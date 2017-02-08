package com.valdroide.gonzalezdanielaadm.entities;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.valdroide.gonzalezdanielaadm.db.ClothesDatabase;

@Table(database = ClothesDatabase.class)
public class Clothes extends BaseModel {
    //@PrimaryKey(autoincrement = true)
    @Column
    @PrimaryKey
    private int ID_CLOTHES_KEY;

    @Column
    @SerializedName("id_category")
    private int ID_CATEGORY;

    @Column
    @SerializedName("id_subcategory")
    private int ID_SUBCATEGORY;

    @Column
    @SerializedName("url_photo")
    private String URL_PHOTO;

    @Column
    @SerializedName("name_photo")
    private String NAME_PHOTO;

    @Column
    @SerializedName("description")
    private String DESCRIPTION;

    @Column
    @SerializedName("is_active")
    private boolean ISACTIVE;

    private String ENCODEBYTE;
    private String NAME_BEFORE;

    public Clothes() {
    }

    public int getID_CLOTHES_KEY() {
        return ID_CLOTHES_KEY;
    }

    public void setID_CLOTHES_KEY(int ID_CLOTHES_KEY) {
        this.ID_CLOTHES_KEY = ID_CLOTHES_KEY;
    }

    public int getID_CATEGORY() {
        return ID_CATEGORY;
    }

    public void setID_CATEGORY(int ID_CATEGORY) {
        this.ID_CATEGORY = ID_CATEGORY;
    }

    public int getID_SUBCATEGORY() {
        return ID_SUBCATEGORY;
    }

    public void setID_SUBCATEGORY(int ID_SUBCATEGORY) {
        this.ID_SUBCATEGORY = ID_SUBCATEGORY;
    }

    public String getURL_PHOTO() {
        return URL_PHOTO;
    }

    public void setURL_PHOTO(String URL_PHOTO) {
        this.URL_PHOTO = URL_PHOTO;
    }

    public String getNAME_PHOTO() {
        return NAME_PHOTO;
    }

    public void setNAME_PHOTO(String NAME_PHOTO) {
        this.NAME_PHOTO = NAME_PHOTO;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public boolean getISACTIVE() {
        return ISACTIVE;
    }

    public void setISACTIVE(boolean ISACTIVE) {
        this.ISACTIVE = ISACTIVE;
    }

    public String getENCODEBYTE() {
        return ENCODEBYTE;
    }

    public void setENCODEBYTE(String ENCODEBYTE) {
        this.ENCODEBYTE = ENCODEBYTE;
    }

    public String getNAME_BEFORE() {
        return NAME_BEFORE;
    }

    public void setNAME_BEFORE(String NAME_BEFORE) {
        this.NAME_BEFORE = NAME_BEFORE;
    }
}
