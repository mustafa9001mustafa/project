package com.konden.freedom.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.konden.freedom.adapter.TestAdapter;
import com.konden.freedom.databinding.ActivityTestBinding;
import com.konden.freedom.model.Test;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    ActivityTestBinding binding;
    private ArrayList<Test> coursesArrayList = new ArrayList<>();
    private TestAdapter TestRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        TestRVAdapter = new TestAdapter(coursesArrayList,TestActivity.this);
        binding.rv.setAdapter(TestRVAdapter);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));

        lodeitem();


//        db.collection("info freedom").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        if (!queryDocumentSnapshots.isEmpty()) {
//                            binding.progressBar.setVisibility(View.GONE);
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                            for (DocumentSnapshot d : list) {
//                                Test c = d.toObject(Test.class);
//                                c.setId(d.getId());
//                                coursesArrayList.add(c);
//                            }
//                            TestRVAdapter.notifyDataSetChanged();
//                        } else {
//                            // if the snapshot is empty we are displaying a toast message.
//                            Toast.makeText(TestActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // if we do not get any data or any error we are displaying
//                        // a toast message that we do not get any data
//                        Toast.makeText(TestActivity.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    private void lodeitem() {
        FirebaseFirestore.getInstance().collection("info freedom").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
                binding.progressBar.setVisibility(View.GONE);

                for (DocumentSnapshot dc : dsList) {
                    Test test = dc.toObject(Test.class);
                    TestRVAdapter.add(test);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TestActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }


//    private void TestCode() {
//
//        //CODE TO GET CURRENT ID OR USER
//
//        //CODE TO GET THE DATA FROM FIREBASE
//
//        db.collection("info freedom").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        if (document != null) {
//                            Test obj = document.toObject(Test.class);
//                            coursesArrayList.add(obj);
//                        }
//                    }
//                    TestRVAdapter.notifyDataSetChanged();
//                } else {
//                    Log.d(TAG, task.getException().getMessage()); //Never ignore potential errors!
//                }
//            }
//        });
//    }


//    private void getNotes() {
//        FirebaseFireStoreController.getInstance().read(new ListCallback<Test>() {
//            @Override
//            public void onSuccess(ArrayList<Test> list) {
//
//                binding.progressBar.setVisibility(View.GONE);
//                coursesArrayList.clear();
//                coursesArrayList.addAll(list);
//                Log.d("NOTEs", "onSuccess: " + coursesArrayList.size());
////                adapter.notifyItemRangeChanged(0, notes.size());
////                adapter.notifyItemRangeInserted(0,notes.size());
////                adapter.notifyItemInserted(0);
//                binding.rv.setAdapter(TestRVAdapter);
//                TestRVAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure() {
//                Toast.makeText(TestActivity.this, "erroe", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}
