package com.konden.freedom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.konden.freedom.databinding.ItemInfoBinding;
import com.konden.freedom.model.Test;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MYTestHolder>{
    private ArrayList<Test> list;
    private Context context;

    public TestAdapter(ArrayList<Test> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public TestAdapter(Context context) {
        this.context = context;
    }

    public void add(Test test){
        list.add(test);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MYTestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemInfoBinding  binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MYTestHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MYTestHolder holder, int position) {
        Test test = list.get(position);
        holder.binding.textInfoName.setText(test.getName());
        holder.binding.textInfoNumber.setText(String.valueOf(test.getNumber()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MYTestHolder extends RecyclerView.ViewHolder {
        ItemInfoBinding  binding;
        public MYTestHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemInfoBinding.bind(itemView);
        }
    }
}
