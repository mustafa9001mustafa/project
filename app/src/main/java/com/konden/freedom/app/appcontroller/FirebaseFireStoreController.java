package com.konden.freedom.app.appcontroller;

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
import com.konden.freedom.app.interfaces.ListCallback;
import com.konden.freedom.app.interfaces.ProcessCallback;
import com.konden.freedom.test.Test;

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


    //CRUD
    public void create(Test note, ProcessCallback callback) {
        fireStore.collection("Notes").add(note.toMap()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                callback.onSuccess("Note created successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure("Failed to create note");
            }
        });
    }
}
