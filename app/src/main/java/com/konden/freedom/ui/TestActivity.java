package com.konden.freedom.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.jawnnypoo.physicslayout.Physics;
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
        binding.physicsLayout.getPhysics().isFlingEnabled();
        binding.physicsLayout.getPhysics().isPhysicsEnabled();
        binding.physicsLayout.getPhysics().setOnCollisionListener(new Physics.OnCollisionListener() {
            @Override
            public void onCollisionEntered(int i, int i1) {

            }

            @Override
            public void onCollisionExited(int i, int i1) {

            }
        });



        TestRVAdapter = new TestAdapter(coursesArrayList,TestActivity.this);
        binding.rv.setAdapter(TestRVAdapter);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));

        lodeitem();
    }

    private void lodeitem() {
        FirebaseFirestore.getInstance().collection("Notes").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
}
