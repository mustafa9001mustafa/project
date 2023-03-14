package com.konden.freedom.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.konden.freedom.R;
import com.konden.freedom.adapter.OnBoardingAdapter;
import com.konden.freedom.databinding.ActivityViewPagerScrollBinding;
import com.konden.freedom.databinding.ActivityViewPagerScrollBinding;
import com.konden.freedom.model.OnBoardingItem;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerScrollActivity extends AppCompatActivity {
    ActivityViewPagerScrollBinding binding;
    List<OnBoardingItem> itemList = new ArrayList<>();
    private OnBoardingAdapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

//    Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPagerScrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        getdata();
//
//        Info info = new Info();
//        info.setName_freedom("captives");
//        info.setNumber_freedom(4700);
//        databaseReference.child("Info Freedom").push().setValue("Info Freedom");
    }



    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.

//        databaseReference.child("Worksheet").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String x = snapshot.getValue().toString();
////                binding.text.setText(String.valueOf(x));
//
//                Log.e("TAG", "onDataChange: "+x );
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // calling on cancelled method when we receive
//                // any error or we are not able to get the data.
//                Toast.makeText(ViewPagerScrollActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

/*    void enterReveal() {
        // previously invisible view

        // get the center for the clipping circle
        int cx = binding.btnOnBoardingAction.getMeasuredWidth() / 2;
        int cy = binding.btnOnBoardingAction.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(binding.btnOnBoardingAction.getWidth(), binding.btnOnBoardingAction.getHeight()) / 2;

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(binding.btnOnBoardingAction, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        binding.btnOnBoardingAction.setVisibility(View.VISIBLE);
        anim.start();
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        Set_OnBoarding();
    }

    private void Set_OnBoarding() {

        itemList.add(new OnBoardingItem("برنامج توفر حياة كريمة للأسرى", "وذلك لضمان حياة كريمة لهم ورعاية أبنائهم وأسرهم\n" +
                " وذلك من خلال عدة صرف مخصصات رواتب شهرية \n" +
                "وتسهيلات و إعفاءات خاصة لعائلات الأسرى"));


        itemList.add(new OnBoardingItem("برنامج تمكين الأسرى المحررين", "وذلك من خلال الإندماج في المجتمع وتأمين وظائف للأسرى\n" +
                " ومشروع الرعاية الصحية وإسكان الأسرى المحررين "));


        itemList.add(new OnBoardingItem("برنامج الدعم النفسي والإجتماعي ", "وهذا من خلال مشروع تواصل وزيارة عائلات الأسرى في بيوتهم \n" +
                "ومشروع مؤازرة عبر مشاركة الوزارة لحظة تجمعهم \n" +
                "أمام مقر الصليب"));


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
            ///////////////////////////////////////////
            ///////////////////////////////////////////
            ///////////////////////////////////////////
            ///////////////////////////////////////////
            ///////////////////////////////////////////
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
            binding.btnOnBoardingAction.setText("دخول");
        else
            binding.btnOnBoardingAction.setText("التالي");

    }
}