package com.ng.ngleetcode;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.ng.code.util.CodeBean;
import com.ng.code.util.CodeDir;
import com.ng.code.util.ProblemAndroidUtil;
import com.ng.ngleetcode.databinding.ActivityMainBinding;
import com.ng.ngleetcode.ui.home.HomeFragment;
import com.ng.ngleetcode.utils.UIUtil;
import com.ng.ngleetcode.view.rounded.RoundedImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView mNavigationView;
    private ActivityMainBinding binding;
    private HomeFragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = com.ng.ngleetcode.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            float degree = 180;

            @Override
            public void onClick(View view) {
                binding.appBarMain.fab.animate().rotation(degree).start();
                degree += 180;
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        mNavigationView = binding.navView;

        RoundedImageView headerIv = ((RoundedImageView) binding.navView.getHeaderView(0).findViewById(R.id.header_imageView));
        headerIv.setCornerRadius(UIUtil.dp2px(this, 22.5f));

        initMenu();


        // Passing each menu ID as a set of Ids because each65
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mNavigationView, navController);

        mHomeFragment = (HomeFragment) getFragment(HomeFragment.class);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            float degree = 180;

            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                mHomeFragment.refreshData();
                binding.appBarMain.fab.animate().rotation(degree).start();
                degree += 180;
            }
        });

    }

    //clazz传入fragment类：如：HomeFragment.class
    public Fragment getFragment(Class<?> clazz) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.size() > 0) {
            NavHostFragment navHostFragment = (NavHostFragment) fragments.get(0);
            List<Fragment> childfragments = navHostFragment.getChildFragmentManager().getFragments();
            if (childfragments.size() > 0) {
                for (int j = 0; j < childfragments.size(); j++) {
                    Fragment fragment = childfragments.get(j);
                    if (fragment.getClass().isAssignableFrom(clazz)) {
                        return fragment;
                    }
                }
            }
        }
        return null;
    }

    private void initMenu() {
        List<CodeDir> codeList = ProblemAndroidUtil.getJavaCodeList(this);
        if (codeList.size() == 0) {
            return;
        }
        for (CodeDir codeDir : codeList) {
            List<CodeBean> tempCodeList = codeDir.dataList;
            if (tempCodeList == null || tempCodeList.size() == 0) {
                continue;
            }
            SubMenu subMenu = mNavigationView.getMenu().addSubMenu("【" + codeDir.title + "】");
            for (CodeBean codeBean : tempCodeList) {
                subMenu.add(codeDir.id, codeBean.id, 0, codeBean.title);
                subMenu.findItem(codeBean.id).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        mHomeFragment.refreshData(codeBean);
                        binding.drawerLayout.closeDrawers();
                        return false;
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}