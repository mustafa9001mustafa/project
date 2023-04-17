package com.konden.freedom.app.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.konden.freedom.app.shard.ShardPreferans
import com.konden.freedom.databinding.ActivityLoginBinding
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    val db = FirebaseFirestore.getInstance()
    lateinit var pDialog: SweetAlertDialog


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
            MotionToast.createColorToast(this@LoginActivity, "تسجيل الدخول", "تم تسجيل الدخول بنجاح",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)

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

                MotionToast.createColorToast(this@LoginActivity, "حدث خطأ ما", "أدخل رقم الهوية لتسجيل الدخور",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
        })
    }

    private fun readnumber(number: Editable?) {
//        for (i in 0..10) {
//            print(i)
//        }
        pDialog = SweetAlertDialog(this@LoginActivity, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = "جاري البحث عن رقم الهوية"
        pDialog.setCancelable(false)
        pDialog.show()

        val mDatabaseRef = FirebaseDatabase.getInstance().getReference("Worksheet")
        val query = mDatabaseRef.orderByChild("رقم الهوية").equalTo(number.toString())
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    pDialog.dismissWithAnimation()

                    MotionToast.createColorToast(this@LoginActivity, "تسجيل الدخول", "تم تسجيل الدخول بنجاح",
                        MotionToastStyle.SUCCESS,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)


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

                            MotionToast.createColorToast(this@LoginActivity, "تم تسجيل الدخول", "تم تسجيل الدخول بنجاح",
                                MotionToastStyle.SUCCESS,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)

                        } else {


                            MotionToast.createColorToast(this@LoginActivity, "حدث خطأ ما", "حدث خطأ ما يرجى إعادة المحاولة في بعد",
                                MotionToastStyle.SUCCESS,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
                        }
                    }

                } else {
                    MotionToast.createColorToast(this@LoginActivity, "لم يتم الإستجابة", "رقم الهوية غير موجودر",
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
                    pDialog.dismissWithAnimation()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                MotionToast.createColorToast(this@LoginActivity, "حدث خطأ ما", "تأكد كم وجود إنترنت لديك",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
                pDialog.dismissWithAnimation()


            }
        })
    }


    private fun GetData(data_name: String) {
        db.collection("Admin").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (data in it.documents) {
                    val Login = data.get(data_name).toString().toInt()
                    val sum = Login + 1
                    val data1 = mapOf<String, Int>(data_name to sum)
                    val id: String = "W0wd10pOXrM94MCUpnFQ"
                    db.collection("Admin").document(id).update(data1).addOnSuccessListener {

                    }.addOnFailureListener {

                    }
                }
            }
        }
    }


//    private fun GetData2() {
//        val data1 = mapOf<String, Int>("Login" to 10)
//
//        db.collection("Admin").document("Login").update(data1).addOnSuccessListener {
//            Toast.makeText(this@LoginActivity, "yes  ", Toast.LENGTH_SHORT).show()
//        }.addOnFailureListener {
//            Toast.makeText(this@LoginActivity, "no", Toast.LENGTH_SHORT).show()
//        }
//
//
////        db.get().addOnSuccessListener {
////            if (!it.isEmpty) {
////                for (data in it.documents) {
////                    val Login = data.get("Login").toString().toInt()
////                    val id: String = "W0wd10pOXrM94MCUpnFQ"
////                    val sum = Login + 1
////                    db.whereEqualTo("Login" ,Login )
////                    val data1 = mapOf<String, Int>("Login" to sum)
//////                    data_number(id,Login,sum,data1)
////                    db.document(id).update(data1).addOnSuccessListener {
////                        Toast.makeText(this@LoginActivity, "yes  " + sum, Toast.LENGTH_SHORT).show()
////
////                    }.addOnFailureListener {
////                        Toast.makeText(this@LoginActivity, "no", Toast.LENGTH_SHORT).show()
////                    }
//////                    Toast.makeText(this@LoginActivity, ""+sum, Toast.LENGTH_SHORT).show()
//////                    database.child("Admin").child(id).updateChildren(data1)
//////                    db.document(id).update(data1)
//////                    val Logout = data.get("Logout").toString()
////                }
////            }
////        }
//    }
//
////    private fun data_number(id : String , one_login : Int , login: Int,newPersonMap: Map<String, Int>) {
////        val personQuery = db
////            .whereEqualTo("Login", one_login)
////            .get()
////
////        db.document(id).update("Login", login)
////
////
//////        if(personQuery.documents.isNotEmpty()) {
//////            for(document in personQuery) {
//////                try {
//////                    db.document(document.id).update("Login", login).await()
////////                    db.document(document.id).set(
////////                        newPersonMap,
////////                        SetOptions.merge()
////////                    ).await()
//////
//////                    Toast.makeText(this@LoginActivity, "ok", Toast.LENGTH_SHORT).show()
//////
//////                } catch (e: Exception) {
//////                    withContext(Dispatchers.Main) {
//////                        Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
//////                    }
//////                }
//////            }
//////        } else {
//////            withContext(Dispatchers.Main) {
//////                Toast.makeText(this@LoginActivity, "No persons matched the query.", Toast.LENGTH_LONG).show()
//////            }
//////        }
////    }


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