package com.example.ph45160_bt_fragment.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ph45160_bt_fragment.fragment.CatFragment;
import com.example.ph45160_bt_fragment.fragment.ProductFragment;

public class PagerAdapter extends FragmentStateAdapter {

    int tabCount = 2;

    CatFragment catFragment;
    ProductFragment productFragment;


    public PagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
        catFragment = new CatFragment();
        productFragment = new ProductFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return catFragment;
            case 1:
                return  productFragment;
            default:
                return catFragment;
        }
    }

    @Override
    public int getItemCount() {
        return tabCount;
    }
}
