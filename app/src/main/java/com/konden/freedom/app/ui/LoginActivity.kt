package com.konden.freedom.app.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.parser.IntegerParser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.app.shard.ShardPreferans
import com.konden.freedom.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.math.log


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    val db = FirebaseFirestore.getInstance()

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
        SizeALlText()

    }


    private fun LOGIN_GUEST() {
        binding.registerBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            GetData("Guest")
            GetData("GuestNow")

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
                    GetData("Login")
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
//                if (it.exists()) {
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


    private fun GetData(data_name : String) {
        db.collection("Admin").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (data in it.documents) {
                    val Login = data.get(data_name).toString().toInt()
                    val sum = Login + 1
                    val data1 = mapOf<String, Int>(data_name to sum)
                    val id: String = "W0wd10pOXrM94MCUpnFQ"
                    db.collection("Admin").document(id).update(data1).addOnSuccessListener {
                        Toast.makeText(this@LoginActivity, "yes  "+sum, Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this@LoginActivity, "no", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun GetData2() {
        val data1 = mapOf<String, Int>("Login" to 10)

        db.collection("Admin").document("Login").update(data1).addOnSuccessListener {
            Toast.makeText(this@LoginActivity, "yes  ", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this@LoginActivity, "no", Toast.LENGTH_SHORT).show()
        }


//        db.get().addOnSuccessListener {
//            if (!it.isEmpty) {
//                for (data in it.documents) {
//                    val Login = data.get("Login").toString().toInt()
//                    val id: String = "W0wd10pOXrM94MCUpnFQ"
//                    val sum = Login + 1
//                    db.whereEqualTo("Login" ,Login )
//                    val data1 = mapOf<String, Int>("Login" to sum)
////                    data_number(id,Login,sum,data1)
//                    db.document(id).update(data1).addOnSuccessListener {
//                        Toast.makeText(this@LoginActivity, "yes  " + sum, Toast.LENGTH_SHORT).show()
//
//                    }.addOnFailureListener {
//                        Toast.makeText(this@LoginActivity, "no", Toast.LENGTH_SHORT).show()
//                    }
////                    Toast.makeText(this@LoginActivity, ""+sum, Toast.LENGTH_SHORT).show()
////                    database.child("Admin").child(id).updateChildren(data1)
////                    db.document(id).update(data1)
////                    val Logout = data.get("Logout").toString()
//                }
//            }
//        }
    }

//    private fun data_number(id : String , one_login : Int , login: Int,newPersonMap: Map<String, Int>) {
//        val personQuery = db
//            .whereEqualTo("Login", one_login)
//            .get()
//
//        db.document(id).update("Login", login)
//
//
////        if(personQuery.documents.isNotEmpty()) {
////            for(document in personQuery) {
////                try {
////                    db.document(document.id).update("Login", login).await()
//////                    db.document(document.id).set(
//////                        newPersonMap,
//////                        SetOptions.merge()
//////                    ).await()
////
////                    Toast.makeText(this@LoginActivity, "ok", Toast.LENGTH_SHORT).show()
////
////                } catch (e: Exception) {
////                    withContext(Dispatchers.Main) {
////                        Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
////                    }
////                }
////            }
////        } else {
////            withContext(Dispatchers.Main) {
////                Toast.makeText(this@LoginActivity, "No persons matched the query.", Toast.LENGTH_LONG).show()
////            }
////        }
//    }


    private fun SizeALlText() {
        if (!ShardPreferans.getInstance().GetSize)
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