package com.ng.ngleetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
import com.ng.code.多线程.ConcurrentExecutor;
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

        ConcurrentExecutor concurrentExecutor = new ConcurrentExecutor(0);


        List<Integer> adnList = new ArrayList<>();
        //3个腾讯
        adnList.add(1);
        adnList.add(1);
        adnList.add(1);

        //2个百度
        adnList.add(2);
        adnList.add(2);

        //3个京东
        adnList.add(3);
        adnList.add(3);

        for (int i = 0; i< 10; i++) {
            //10个别的
            adnList.add(4);
        }

        List<Runnable> runnableList = new ArrayList<>();
        for (int i = 0; i < adnList.size(); i++) {
            int adnId = adnList.get(i);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    createAdn(adnId);


                }
            };
            runnableList.add(runnable);

        }
        concurrentExecutor.execute(runnableList);
    }

    private static Set<Integer> mLoadedSplitModules = new HashSet<>();

    public static void createAdn(int adnId) {
        long startTime = System.currentTimeMillis();
        Log.d("xigua", "创建adn:" + getDesc(adnId));

        ReentrantLock lock;
        synchronized (sLoadDependencyLock) {
            lock = sLocks.get(adnId);
            if (lock == null) {
                lock = new ReentrantLock();
                sLocks.put(adnId, lock);
            }
        }

        // 防止同一个adn重入
        Log.d("xigua", "尝试拿到锁:" + getDesc(adnId));

        lock.lock();
        Log.d("xigua", "拿到锁:" + getDesc(adnId));

        try {
            Thread.sleep(50);
            if (mLoadedSplitModules.contains(adnId)) {
                Log.d("xigua", "创建adn结束1:"  +
                               (System.currentTimeMillis() - startTime) + " " + getDesc(adnId));
                return;
            }

            Log.d("xigua", "创建adn结束2:"  +
                           (System.currentTimeMillis() - startTime) + " " + getDesc(adnId));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Log.d("xigua", "释放锁:" + getDesc(adnId));
            lock.unlock();
        }



    }

    private static String getDesc(final int adnId) {
        return "[" + adnId +"] -" +  Thread.currentThread()
                                         .getName();
    }

    private void test() {
        List<Runnable> runnableList = new ArrayList<>();
        ConcurrentExecutor concurrentExecutor = new ConcurrentExecutor(0);

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            Runnable tempRunnable = new Runnable() {
                @Override
                public void run() {
                    Log.d("xigua", Thread.currentThread().getName() + " 创建adn:" + finalI);

                    Runnable sucRunnable = new Runnable() {
                        @Override
                        public void run() {
                            Log.d("xigua", Thread.currentThread()
                                                 .getName() + " 创建adn 成功:" + finalI);
                        }
                    };

                    concurrentExecutor.callback(sucRunnable);
                }
            };
            runnableList.add(tempRunnable);
        }
        concurrentExecutor.execute(runnableList);

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
