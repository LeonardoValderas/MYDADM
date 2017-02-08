package com.valdroide.gonzalezdanielaadm.main.tab_fragment.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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
//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
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
//        if (!update)
//            viewPager.setCurrentItem(1);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
////        if (id == R.id.item_delete) {
////            getFragmentChecks().deleteClick();
////            return true;
////        }
////        if (id == R.id.action_share) {
////            getFragmentChecks().shareClick();
////            return true;
////        }
////        if (id == R.id.email) {
////            getFragmentChecks().emailClick();
////            return true;
////        }
////        if (id == android.R.id.home) {
////            if (is_action_mode) {
////                clearActionMode();
////            }
////            return true;
////        }
//        return true;
//    }
}
