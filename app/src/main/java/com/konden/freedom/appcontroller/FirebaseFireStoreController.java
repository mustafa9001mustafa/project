package com.konden.freedom.appcontroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.konden.freedom.fragment.ListCallback;
import com.konden.freedom.model.Test;

import java.util.ArrayList;

public class FirebaseFireStoreController {

    private FirebaseFireStoreController() {
    }

    private static FirebaseFireStoreController instance;

    private FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    public static synchronized FirebaseFireStoreController getInstance() {
        if (instance == null) {
            instance = new FirebaseFireStoreController();
        }
        return instance;
    }

    public void read(ListCallback<Test> callback) {
        fireStore.collection("info freedom").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error == null) {
                    ArrayList<Test> notes = new ArrayList<>();
                    if (!value.getDocuments().isEmpty()) {
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            Test note = snapshot.toObject(Test.class);
//                            note.setId(snapshot.getId());
                            notes.add(note);
                        }
                    }
                    callback.onSuccess(notes);
                }else {
                    callback.onFailure();
                }
            }
        });
    }
}
