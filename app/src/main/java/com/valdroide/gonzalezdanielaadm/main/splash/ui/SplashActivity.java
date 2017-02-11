package com.valdroide.gonzalezdanielaadm.main.splash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmApp;
import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.main.splash.SplashActivityPresenter;
import com.valdroide.gonzalezdanielaadm.main.tab_fragment.ui.TabActivity;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashActivityView {

    @Bind(R.id.imageViewLogo)
    ImageView imageViewLogo;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.activity_splash)
    LinearLayout conteiner;
    @Inject
    SplashActivityPresenter presenter;
    GonzalezDanielaAdmApp app;
    @Bind(R.id.textViewDownload)
    TextView textViewDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setupInjection();
        presenter.onCreate();
        getDateTable();
    }

    private void setupInjection() {
        app = (GonzalezDanielaAdmApp) getApplication();
        app.getSplashActivityComponent(this, this).inject(this);
    }

    @Override
    public void gotToTab() {
        progressBar.setVisibility(View.INVISIBLE);
        startActivity(new Intent(this, TabActivity.class));
    }

    @Override
    public void getDateTableEmpty() {
        presenter.getAllData(app);
    }

    public void getDateTable() {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getDateTable();
    }

    @Override
    public void setError(String msg) {
        progressBar.setVisibility(View.INVISIBLE);
        textViewDownload.setText(msg);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
