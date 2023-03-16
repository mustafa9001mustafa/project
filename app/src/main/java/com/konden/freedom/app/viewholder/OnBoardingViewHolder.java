package com.konden.freedom.app.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.konden.freedom.databinding.ItemOnBoardingBinding;
import com.konden.freedom.app.model.OnBoardingItem;


public class OnBoardingViewHolder extends RecyclerView.ViewHolder {
    ItemOnBoardingBinding binding;

    public OnBoardingViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ItemOnBoardingBinding.bind(itemView);
    }


    public void SetData(OnBoardingItem item) {
        binding.textTitle.setText(item.getTitle());
        binding.textDescription.setText(item.getDescription());
    }
}
