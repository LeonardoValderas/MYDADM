package com.valdroide.gonzalezdanielaadm.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.DateTable_Table;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by LEO on 31/1/2017.
 */
public class Utils {

    public static String URL_IMAGE = "http://10.0.2.2:8080/md/clothes/image_clothes/";
    public static String CATEGORY = "CATEGORY";
    public static String SUBCATEGORY = "SUBCATEGORY";
    public static String CLOTHES = "CLOTHES";
    public static String TABLES = "TABLES";

    //FECHAS
    public static String getFechaOficial() {
        Date dateOficial = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(dateOficial);
    }

    public static boolean oldPhones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return false;
        else
            return true;
    }

    public static void showSnackBar(View conteiner, String msg) {
        Snackbar.make(conteiner, msg, Snackbar.LENGTH_SHORT).show();
    }

    public static byte[] readBytes(Uri uri, Context context) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public static void setPicasso(Context context, String url, final int resource, final ImageView imageView) {
        Picasso.with(context)
                .load(url).fit()
                .placeholder(resource)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        imageView.setImageResource(resource);
                    }
                });
    }

    public static void setPicasso(Context context, Uri uri, final int resource, final ImageView imageView) {
        Picasso.with(context)
                .load(uri.toString()).fit()
                .placeholder(resource)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        imageView.setImageResource(resource);
                    }
                });
    }

    public static List<DateTable> dateTables() {
        return SQLite.select().from(DateTable.class).queryList();
    }

    public static void switchTable() {
        for (int i = 0; i < 4; i++) {
            DateTable dt;
            switch (i) {
                case 0:
                    dt = new DateTable(0, Utils.CATEGORY, "12345678");
                    dt.save();
                    break;
                case 1:
                    dt = new DateTable(0, Utils.SUBCATEGORY, "12345678");
                    dt.save();
                    break;
                case 2:
                    dt = new DateTable(0, Utils.CLOTHES, "12345678");
                    dt.save();
                    break;
                case 3:
                    dt = new DateTable(0, Utils.TABLES, "12345678");
                    dt.save();
                    break;
            }
        }
    }

    public static void updateDateTable(DateTable dateTable) {
        SQLite.update(DateTable.class)
                .set(DateTable_Table.DATE.eq(dateTable.getDATE()))
                .where(DateTable_Table.TABLENAME.is(dateTable.getTABLENAME()))
                .async()
                .execute();
        SQLite.update(DateTable.class)
                .set(DateTable_Table.DATE.eq(dateTable.getDATE()))
                .where(DateTable_Table.TABLENAME.is(Utils.TABLES))
                .async()
                .execute();
    }
}
