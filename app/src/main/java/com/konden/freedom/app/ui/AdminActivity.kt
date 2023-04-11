package com.konden.freedom.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.databinding.ActivityAdminBinding
import com.konden.freedom.databinding.ActivityLoginBinding

class AdminActivity : AppCompatActivity() {

    lateinit var binding: ActivityAdminBinding

    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()
        AllFun()

    }

    private fun AllFun() {
        GetData()
    }

    private fun GetData() {
        db.collection("Admin").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (data in it.documents) {
                    val Guest = data.get("Guest").toString()
                    binding.guestNumber.text = Guest.toString()

                    val Login = data.get("Login").toString()
                    binding.loginNumber.text = Login.toString()

                    val Logout = data.get("Logout").toString()
                    binding.logoutNumber.text = Logout.toString()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}