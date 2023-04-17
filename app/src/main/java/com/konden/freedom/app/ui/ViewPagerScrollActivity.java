package com.konden.freedom.app.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.konden.freedom.R;
import com.konden.freedom.app.adapter.OnBoardingAdapter;
import com.konden.freedom.databinding.ActivityViewPagerScrollBinding;
import com.konden.freedom.app.model.OnBoardingItem;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerScrollActivity extends AppCompatActivity {
    ActivityViewPagerScrollBinding binding;
    List<OnBoardingItem> itemList = new ArrayList<>();
    private OnBoardingAdapter adapter;
//    FirebaseDatabase firebaseDatabase;
//    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPagerScrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference();
//        getdata();
//
//        Info info = new Info();
//        info.setName_freedom("captives");
//        info.setNumber_freedom(4700);
//        databaseReference.child("Info Freedom").push().setValue("Info Freedom");
    }



//    private void getdata() {
//
//        // calling add value event listener method
//        // for getting the values from database.
//
////        databaseReference.child("Worksheet").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                String x = snapshot.getValue().toString();
//////                binding.text.setText(String.valueOf(x));
////
////                Log.e("TAG", "onDataChange: "+x );
////            }
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////                // calling on cancelled method when we receive
////                // any error or we are not able to get the data.
////                Toast.makeText(ViewPagerScrollActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
////            }
////        });
//    }


    @Override
    protected void onStart() {
        super.onStart();
        Set_OnBoarding();
    }

    private void Set_OnBoarding() {

        itemList.add(new OnBoardingItem(getString(R.string.prog_1), getString(R.string.text_prog_1) +
                getString(R.string.text_prog_1_1) +
                getString(R.string.text_prog_1_2)));


        itemList.add(new OnBoardingItem(getString(R.string.prog_2), getString(R.string.text_prog_2) +
                getString(R.string.text_prog_2_1)));


        itemList.add(new OnBoardingItem(getString(R.string.prog_3), getString(R.string.text_prog_3) +
                getString(R.string.text_prog_3_1) +
                getString(R.string.text_prog_3_2)));


        adapter = new OnBoardingAdapter(itemList);
        binding.viewpagerFragmentOnBoarding.setAdapter(adapter);
        SetUpOnBoardingIndicators();
        SetCurrentOnBoardingIndicators(0);
        viewpager();
        next_btn();
    }

    private void next_btn() {
        binding.btnOnBoardingAction.setOnClickListener(view -> {
            if (binding.viewpagerFragmentOnBoarding.getCurrentItem() + 1 < adapter.getItemCount())
                binding.viewpagerFragmentOnBoarding.setCurrentItem(binding.viewpagerFragmentOnBoarding.getCurrentItem() + 1);
            else
                startActivity(new Intent(ViewPagerScrollActivity.this, LoginActivity.class));
        });
    }

    private void viewpager() {
        binding.viewpagerFragmentOnBoarding.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                SetCurrentOnBoardingIndicators(position);
            }
        });

    }

    private void SetUpOnBoardingIndicators() {
        ImageView[] indicators = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(ViewPagerScrollActivity.this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(ViewPagerScrollActivity.this, R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            binding.linearLayout.addView(indicators[i]);
        }
    }

    private void SetCurrentOnBoardingIndicators(int index) {
        int childCount = binding.linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) binding.linearLayout.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(ViewPagerScrollActivity.this, R.drawable.onboarding_indicator_activity));

            } else {

                imageView.setImageDrawable(ContextCompat.getDrawable(ViewPagerScrollActivity.this, R.drawable.onboarding_indicator_inactive));

            }
        }

        if (index == adapter.getItemCount() - 1)
            binding.btnOnBoardingAction.setText(R.string.go);
        else
            binding.btnOnBoardingAction.setText(R.string.next);

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}