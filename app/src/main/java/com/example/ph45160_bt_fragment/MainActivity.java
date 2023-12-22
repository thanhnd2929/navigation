package com.example.ph45160_bt_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ph45160_bt_fragment.fragment.CatFragment;
import com.example.ph45160_bt_fragment.fragment.Info_fragment;
import com.example.ph45160_bt_fragment.fragment.ProductFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    FragmentManager fm;

    CatFragment catFragment;

    ProductFragment productFragment;

    Info_fragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);

        // set up toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setTitle("Quản lý Sản phầm");

        catFragment = new CatFragment();
        productFragment = new ProductFragment();
        infoFragment = new Info_fragment();

        fm = getSupportFragmentManager();

        fm.beginTransaction()
                .replace(R.id.frag_container, infoFragment)
                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.mCat) {
                    fragment = catFragment;
                } else if (item.getItemId() == R.id.mProduct) {
                    fragment = productFragment;
                } else if (item.getItemId() == R.id.mHome){
                    fragment = infoFragment;
                }

                fm
                        .beginTransaction()
                        .replace(R.id.frag_container, fragment)
                        .commit();

                getSupportActionBar().setTitle(item.getTitle());

                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}