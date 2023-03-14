package com.konden.freedom.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import com.google.firebase.database.*
import com.konden.freedom.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        ALlFUN()
    }

    private fun ALlFUN() {
        CHECK_NUMBER_AND_LOGIN()
        LOGIN_GUEST()
    }

    private fun LOGIN_GUEST() {
        binding.registerBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        })
    }

    private fun CHECK_NUMBER_AND_LOGIN() {
        binding.login.setOnClickListener(View.OnClickListener {
            val number: Editable? = binding.editTextEmail.text
//            Toast.makeText(this@LoginActivity, "" + number, Toast.LENGTH_SHORT).show()

            if (number!!.isNotEmpty())
                readnumber(number)
            else
                Toast.makeText(
                    this@LoginActivity,
                    "أدخل رقم الهوية لتسجيل الدخور",
                    Toast.LENGTH_SHORT
                ).show()

        })

    }

    private fun readnumber(number: Editable?) {
//        for (i in 0..22833) {

        database = FirebaseDatabase.getInstance().getReference("Worksheet")

//        }

                database .child(number.toString())
//            .orderByChild("رقم الهوية")
//            .equalTo(number.toString())

            .get().addOnSuccessListener {
                if (it.exists()) {
                    val number_id = it.child("رقم الهوية").value
                    Toast.makeText(this@LoginActivity, "ok" + number_id, Toast.LENGTH_SHORT).show()
                    binding.login.text = number_id.toString()

                } else
                    Toast.makeText(this@LoginActivity, "المستخدم غير موجود", Toast.LENGTH_SHORT)
                        .show()

            }.addOnCanceledListener {
                Toast.makeText(
                    this@LoginActivity,
                    "حدث خطأ ما , أعد تسجيل الدخول لاحقا",
                    Toast.LENGTH_SHORT
                ).show()

            }
    }
}