package com.konden.freedom.app.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.konden.freedom.databinding.ItemOnBoardingBinding;
import com.konden.freedom.app.model.OnBoardingItem;
import com.konden.freedom.app.viewholder.OnBoardingViewHolder;


import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingViewHolder> {

    private List<OnBoardingItem> onBoardingItems;

    public OnBoardingAdapter(List<OnBoardingItem> onBoardingItems) {
        this.onBoardingItems = onBoardingItems;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemOnBoardingBinding binding = ItemOnBoardingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OnBoardingViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        holder.SetData(onBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }
}
