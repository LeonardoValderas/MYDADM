package com.valdroide.gonzalezdanielaadm.main.notification.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmApp;
import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.main.notification.NotificationActivityPresenter;
import com.valdroide.gonzalezdanielaadm.main.tab_fragment.ui.TabActivity;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity implements NotificationActivityView {

    @Bind(R.id.editTextTitle)
    EditText editTextTitle;
    @Bind(R.id.editTextContent)
    EditText editTextContent;
    @Bind(R.id.activity_notification)
    LinearLayout conteiner;
    @Inject
    NotificationActivityPresenter presenter;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        setupInjection();
        presenter.onCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("NOTIFICACION");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Procesando...");
        pDialog.setCancelable(false);

    }

    private void setupInjection() {
        GonzalezDanielaAdmApp app = (GonzalezDanielaAdmApp) getApplication();
        app.getNotificationActivityComponent(this, this).inject(this);
    }

    @Override
    public void sentSuccess() {
        pDialog.hide();
        Utils.showSnackBar(conteiner, "Notificación enviada");
    }

    @Override
    public void sentError(String msg) {
        pDialog.hide();
        Utils.showSnackBar(conteiner, msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.send_notification) {
            if (editTextTitle.getText().toString().equals(""))
                Utils.showSnackBar(conteiner, "Ingrese un titulo.");
            else if (editTextContent.getText().toString().equals(""))
                Utils.showSnackBar(conteiner, "Ingrese el mensaje.");
            else {
                pDialog.show();
                presenter.sendNotification(this, editTextTitle.getText().toString(), editTextContent.getText().toString());
            }
            return true;
        }
        if (id == android.R.id.home) {
            startActivity(new Intent(this, TabActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
