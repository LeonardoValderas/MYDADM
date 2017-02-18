package com.valdroide.gonzalezdanielaadm.main.tab_fragment.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TabHost;

import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.Communicator;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.FragmentAddClothes;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.ui.FragmentEditClothes;
import com.valdroide.gonzalezdanielaadm.main.tab_fragment.adapters.SectionsPagerAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TabActivity extends AppCompatActivity implements Communicator {
    @Inject
    SectionsPagerAdapter adapter;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        setupInjection();
        setupNavigation();
    }

    private void setupNavigation() {
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                hideKeyBoard();
            }

            @Override
            public void onPageSelected(int position) {
                hideKeyBoard();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    private void setupInjection() {
        String[] titles = new String[]{getString(R.string.add_clothes),
                getString(R.string.edit_clothes)};
        Fragment[] fragments = new Fragment[]{new FragmentAddClothes(),
                new FragmentEditClothes()};
        adapter = new SectionsPagerAdapter(getSupportFragmentManager(), titles, fragments);
    }

    @Override
    public void refresh() {
        getFragmentEditClothes().refresh();
    }

    public FragmentEditClothes getFragmentEditClothes() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentEditClothes fragment = (FragmentEditClothes) manager
                .findFragmentByTag("android:switcher:" + viewPager.getId()
                        + ":" + 1);
        return fragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return false;
        }
        return false;
    }
}
