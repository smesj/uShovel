package com.example.smesj.ushovel;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Smesj on 10/5/2017.
 */

public class MainActivity extends AppCompatActivity {

    Fragment map = new MapFragment();
    Fragment jobs = new JobsFragment();
    Fragment user = new UserFragment();



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_jobs:
                    transaction.replace(R.id.content, jobs);
                    transaction.commit();
                    return true;
                case R.id.navigation_map:
                    transaction.replace(R.id.content, map);
                    transaction.commit();
                    return true;
                case R.id.navigation_user:
                    transaction.replace(R.id.content, user);
                    transaction.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}