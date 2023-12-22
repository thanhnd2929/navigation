package com.example.ph45160_bt_fragment.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ph45160_bt_fragment.DTO.CatDTO;
import com.example.ph45160_bt_fragment.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {

    ArrayList<CatDTO> list;

    public SpinnerAdapter(ArrayList<CatDTO> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(parent.getContext(), R.layout.item_spinner, null);
        TextView txt_spidcat = convertView.findViewById(R.id.txt_spidcat);
        TextView txt_spcatname = convertView.findViewById(R.id.txt_spcatname);
        txt_spidcat.setText(list.get(position).getId()+"");
        txt_spcatname.setText(list.get(position).getName());
        return convertView;
    }
}
