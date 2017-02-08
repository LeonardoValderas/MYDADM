package com.valdroide.gonzalezdanielaadm.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = ClothesDatabase.NAME, version = ClothesDatabase.VERSION)
public class ClothesDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "ClothesDatabase";
}