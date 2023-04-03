package com.ng.ngleetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ng.code.util.SharedPreferencesUtils;
import com.ng.code.util.model.CodeDataModel;
import com.ng.code.util.tree.CodeNode;
import com.ng.ngleetcode.databinding.ActivityMainVpBinding;
import com.ng.ngleetcode.ui.home.HomeFragment;
import com.ng.ngleetcode.view.adapter.NodeTreeAdapter;

public class MainActivity extends AppCompatActivity implements NodeTreeAdapter.OnLeftItemClick {
    private ActivityMainVpBinding binding;
    private HomeFragment mHomeFragment;
    private NodeTreeAdapter adapter;
    private List<BaseNode> mData = new ArrayList<>();

    private static final Object sLoadDependencyLock = new Object();
    private static final SparseArray<ReentrantLock> sLocks = new SparseArray<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainVpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViewsAndEvents();
    }

    private void initViewsAndEvents() {
        mHomeFragment = (HomeFragment) getFragment(HomeFragment.class);
        binding.appBarMain.toolbar.setTitle(mHomeFragment.refreshData());
        setSupportActionBar(binding.appBarMain.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_nav);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            float degree = 360;

            @Override
            public void onClick(View view) {
                binding.appBarMain.fab.animate().rotation(degree).start();
                degree += 360;
                binding.appBarMain.toolbar.setTitle(mHomeFragment.refreshData());
            }
        });

        binding.appBarMain.check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mHomeFragment.onToggle();
            }
        });
        initRv();
        binding.drawerMain.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }


            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void initRv() {
        binding.leftRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NodeTreeAdapter(this);
        binding.leftRv.setAdapter(adapter);
        mData = CodeDataModel.getInstance().getCodeData(this);
        adapter.setList(mData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (binding.drawerMain.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerMain.closeDrawers();
                } else {
                    binding.drawerMain.openDrawer(GravityCompat.START);
                }
                break;
            case R.id.action_settings:
                SharedPreferencesUtils.saveString(MainActivity.this, CodeDataModel.CODE_STATE, "");
                mHomeFragment.refreshData();
                Toast.makeText(MainActivity.this, "清空数据", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //clazz传入fragment类：如：HomeFragment.class
    public Fragment getFragment(Class<?> clazz) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.size() > 0) {
            NavHostFragment navHostFragment = (NavHostFragment) fragments.get(0);
            List<Fragment> childfragments = navHostFragment.getChildFragmentManager()
                                                           .getFragments();
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

    @Override
    public void onItem(CodeNode codeBean) {
        binding.appBarMain.toolbar.setTitle(mHomeFragment.refreshData(codeBean));
        binding.drawerMain.closeDrawers();
    }
}
