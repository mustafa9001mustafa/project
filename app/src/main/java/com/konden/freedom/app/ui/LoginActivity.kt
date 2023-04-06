package com.konden.freedom.app.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.konden.freedom.app.shard.ShardPreferans
import com.konden.freedom.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
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
            ShardPreferans.getInstance().GustLogin(true)
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
//        for (i in 0..10) {
//            print(i)
//        }

        binding.textNotNecessary.visibility = View.VISIBLE
        binding.backNotNecessary.visibility = View.VISIBLE
        binding.lottieIconLoading.visibility = View.VISIBLE
        binding.login.visibility = View.INVISIBLE
        binding.lottieIconLoading.bringToFront()
        binding.textNotNecessary.bringToFront()

        val mDatabaseRef = FirebaseDatabase.getInstance().getReference("Worksheet")

        val query = mDatabaseRef.orderByChild("رقم الهوية").equalTo(number.toString())

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                if (dataSnapshot.exists()) {
                    binding.login.visibility = View.VISIBLE
                    binding.textNotNecessary.visibility = View.GONE
                    binding.backNotNecessary.visibility = View.GONE
                    binding.lottieIconLoading.visibility = View.GONE

                    for (data in dataSnapshot.children) {
                        val name = data.child("الاسم").value
                        val data_aser = data.child("تاريخ الأسر").value
                        val data_freedom = data.child("تاريخ الافراج المتوقع").value
                        val number_id = data.child("رقم الهوية").value


                        ShardPreferans.getInstance().saveName(name.toString())
                        ShardPreferans.getInstance().saveDataAser(data_aser.toString())
                        ShardPreferans.getInstance().saveDataFreedom(data_freedom.toString())
                        ShardPreferans.getInstance().saveNumber(number_id.toString())
                        ShardPreferans.getInstance().saveLogin(true)

                        if (ShardPreferans.getInstance().statesLogin == true) {
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            Toast.makeText(
                                this@LoginActivity,
                                "تم تسجيل الدخول",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {

                            Toast.makeText(
                                this@LoginActivity,
                                "Something with wrong",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            binding.login.visibility = View.VISIBLE
                            binding.textNotNecessary.visibility = View.GONE
                            binding.backNotNecessary.visibility = View.GONE
                            binding.lottieIconLoading.visibility = View.GONE
                        }


                    }

                } else {
                    binding.login.visibility = View.VISIBLE
                    binding.textNotNecessary.visibility = View.GONE
                    binding.backNotNecessary.visibility = View.GONE
                    binding.lottieIconLoading.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, "رقم الهوية غير موجود", Toast.LENGTH_SHORT)
                        .show()
                }


            }

            override fun onCancelled(error: DatabaseError) {
                binding.login.visibility = View.VISIBLE
                binding.textNotNecessary.visibility = View.GONE
                binding.backNotNecessary.visibility = View.GONE
                binding.lottieIconLoading.visibility = View.GONE

                Toast.makeText(this@LoginActivity, "Something with wrong", Toast.LENGTH_SHORT)
                    .show()


            }
        })

//        database = FirebaseDatabase.getInstance().getReference("Worksheet")

//        database
////            .child()
//            .child(number.toString()).get().addOnSuccessListener {
//
//
//
//
//
//                if (it.exists()) {
//
//
//
//
//
//                    val number_id = it.child("رقم الهوية").value
//                    Toast.makeText(this@LoginActivity, "ok" + number_id, Toast.LENGTH_SHORT).show()
//                    binding.login.text = number_id.toString()
//
//                } else
//                    Toast.makeText(this@LoginActivity, "المستخدم غير موجود", Toast.LENGTH_SHORT)
//                        .show()
//
//            }.addOnCanceledListener {
//                Toast.makeText(
//                    this@LoginActivity,
//                    "حدث خطأ ما , أعد تسجيل الدخول لاحقا",
//                    Toast.LENGTH_SHORT
//                ).show()
//
//            }
    }


    private fun SizeALlText() {
        if (ShardPreferans.getInstance().GetSize)
            size_larg()
        else
            size_mid()
    }
    private fun size_mid() {
        binding.login.textSize = 16f
        binding.textNotNecessary.textSize = 16f
        binding.registerBtn.textSize = 16f
        binding.editTextEmail.textSize = 16f
        binding.welcome.textSize = 16f

    }

    private fun size_larg() {
        binding.login.textSize = 16f
        binding.textNotNecessary.textSize = 16f
        binding.registerBtn.textSize = 16f
        binding.editTextEmail.textSize = 16f
        binding.welcome.textSize = 16f
    }
    override fun onStop() {
        super.onStop()

        finish()
    }
}