package com.konden.freedom.app.ui


import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.konden.freedom.R
import com.konden.freedom.databinding.ActivityAdminBinding
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


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
        SetSpiner()
        news_spinner()
        ChangePassword()
    }

    private fun ChangePassword() {

        binding.btnChangePassword.setOnClickListener {
            if (binding.enterNowPassword.text.isNotEmpty() && binding.enterNewPassword.text.isNotEmpty())
                password(
                    binding.enterNowPassword.text.toString(),
                    binding.enterNewPassword.text.toString()
                )
            else
                MotionToast.createColorToast(this@AdminActivity, "حدث خطأ ما", "أدخل كلمة المرور لتغيرها",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
        }
    }


    private fun password(NowPassword: String, NewPassword: String) {
        db.collection("Admin").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (data in it.documents) {
                    val Password = data.get("Password").toString()
                    if (NowPassword.equals(Password))
                        ChangeNow(NewPassword)
                    else
                        MotionToast.createColorToast(this@AdminActivity, "حدث خطأ ما", "كلمة المرور خاطئة",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)

                }
            }
        }
    }

    private fun ChangeNow(NewPassword: String) {
        val data = mapOf<String, String>("Password" to NewPassword)
        db.collection("Admin").document("W0wd10pOXrM94MCUpnFQ").update(data).addOnSuccessListener {


            MotionToast.createColorToast(this@AdminActivity, "تم التغير", "تم تغير الرقم بنجاح",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
            binding.enterNowPassword.text.clear()
            binding.enterNewPassword.text.clear()
        }.addOnFailureListener {

            MotionToast.createColorToast(this@AdminActivity, "حدث خطأ ما", "تأكد من إتصالك بالإنترنت",
                MotionToastStyle.ERROR,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
        }
    }


    private fun AddNew(
        description: String, link: String, title: String, collection: String
    ) {
        if (binding.enterTitel.text.isNotEmpty() && binding.enterDescription.text.isNotEmpty() && binding.enterLink.text.isNotEmpty()) {

            val user = hashMapOf(
                "description" to description,
                "link" to "https://$link",
                "titel" to title,
                "time" to FieldValue.serverTimestamp()
            )

            db.collection(collection)
                .add(user)
                .addOnSuccessListener {

                    MotionToast.createColorToast(this@AdminActivity, "تم الإضافة", "تم إضافة العملية بنجاح",
                        MotionToastStyle.SUCCESS,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
                    binding.enterTitel.text.clear()
                    binding.enterLink.text.clear()
                    binding.enterDescription.text.clear()
                }
                .addOnFailureListener { e ->

                    MotionToast.createToast(this@AdminActivity, "حدث خطأ ما",""+ e,
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
                }

        }
    }


    private fun news_spinner() {
        val news = resources.getStringArray(R.array.news)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, news
        )
        binding.spinnerNew.adapter = adapter
        binding.spinnerNew.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {


                SwitchDataLive(news[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {


                MotionToast.createColorToast(this@AdminActivity, "حدث خطأ ما", "أعد العملية مرة أخرى",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)            }
        }
    }


    private fun SwitchDataLive(s: String) {
        val collection: String = when (s) {
            "خبر جديد" -> "live"
            "إنتهاك جديد" -> "DangerCard"
            else -> {
                "live"
            }
        }

        if (collection.equals("live")) {
            binding.enterDescription.hint = resources.getString(R.string.add_description_new)
            binding.enterLink.hint = resources.getString(R.string.add_link_new)
            binding.enterTitel.hint = resources.getString(R.string.add_title_new)

        } else {
            binding.enterDescription.hint = resources.getString(R.string.add_description_danger)
            binding.enterLink.hint = resources.getString(R.string.add_link_danger)
            binding.enterTitel.hint = resources.getString(R.string.add_title_danger)

        }
        Add_New(collection)
    }


    private fun Add_New(collection: String) {
        binding.addLive.setOnClickListener {
            if (binding.enterDescription.text.isNotEmpty() && binding.enterLink.text.isNotEmpty() && binding.enterTitel.text.isNotEmpty()) {
                val description: String = binding.enterDescription.text.toString()
                val link: String = binding.enterLink.text.toString()
                val titel: String = binding.enterTitel.text.toString()

                AddNew(description, link, titel, collection)

            } else {

                MotionToast.createColorToast(this@AdminActivity, "حدث خطأ ما", "أدخل البيانات للإضافة",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
            }
        }
    }

    private fun SetSpiner() {
        val Info = resources.getStringArray(R.array.Info)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, Info
        )
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {


                SwitchData(Info[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

                MotionToast.createColorToast(this@AdminActivity, "حدث خطأ ما", "تأكد من إتصالك بالإنترنت",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
            }
        }
    }


    private fun SwitchData(s: String) {
        val uid: String = when (s) {
            "الأسرى" -> "LvY4g7zWfGn73h4qyOYg"
            "المرضى" -> "3AeMUnoh6SqApKLE8x7Q"
            "الأسيرات" -> "D2C5kupgcnqPTb7HWmQO"
            "الأسرى الإداريون" -> "LYCxt4dR1A5ysEkqa3zT"
            "الصحفين" -> "RwBmNYs9p4rvtzEFpQ7R"
            "النواب" -> "TjF5Xj1vIjPKH5kVoXSn"
            "الأطفال" -> "Z7PpPFSmJpwKcvW8OfwP"
            "قدماء الأسرى" -> "fNIst62YVf3oChLDJ9A9"
            "المؤبدات" -> "nVlzS10hllBxuQRkRZry"
            else -> {
                "LvY4g7zWfGn73h4qyOYg"
            }
        }
        UpdateData(uid)

    }

    private fun UpdateData(uid: String) {
        binding.FButton.setOnClickListener {
            if (binding.enterNumberData.text.isNotEmpty()) {
                val data1 = mapOf<String, Int>("number" to binding.enterNumberData.text.toString().toInt())
                db.collection("Notes").document(uid).update(data1).addOnSuccessListener {
                    Toast.makeText(this@AdminActivity, "تم التغير", Toast.LENGTH_SHORT).show()

                    binding.enterNumberData.text.clear()

                }.addOnFailureListener {

                    MotionToast.createColorToast(this@AdminActivity, "حدث خطأ ما", "تأكد من إتصالك بالإنترنت",
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
                }
            }else
                MotionToast.createColorToast(this@AdminActivity, "حدث خطأ ما", "أدخل الرقم لتغير القيمة",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION, Typeface.DEFAULT_BOLD)
        }
    }

    private fun GetData() {
        db.collection("Admin").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (data in it.documents) {
                    val Guest = data.get("Guest").toString()
                    binding.guestNumberAll.text = Guest.toString()

                    val GuestNow = data.get("GuestNow").toString()
                    binding.guestNumberNow.text = GuestNow.toString()

                    val Login = data.get("Login").toString()
                    binding.loginNumber.text = Login.toString()

                    val Logout = data.get("Logout").toString()
                    binding.logoutNumber.text = Logout.toString()
                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}