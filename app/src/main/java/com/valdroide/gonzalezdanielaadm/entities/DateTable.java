package com.valdroide.gonzalezdanielaadm.entities;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.valdroide.gonzalezdanielaadm.db.ClothesDatabase;

/**
 * Created by LEO on 29/1/2017.
 */
@Table(database = ClothesDatabase.class)
public class DateTable extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int ID;

    @Column
    private String TABLENAME;
    @Column
    @SerializedName("table_date")
    private String DATE;

    public DateTable() {
    }

    public DateTable(int ID, String TABLENAME, String DATE) {
        this.ID = ID;
        this.TABLENAME = TABLENAME;
        this.DATE = DATE;
    }
    public DateTable(String TABLENAME, String DATE) {
        this.TABLENAME = TABLENAME;
        this.DATE = DATE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTABLENAME() {
        return TABLENAME;
    }

    public void setTABLENAME(String TABLENAME) {
        this.TABLENAME = TABLENAME;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }
}
