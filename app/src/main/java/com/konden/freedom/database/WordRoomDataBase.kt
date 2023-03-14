package com.konden.freedom.database

import android.content.Context
import android.service.autofill.UserData
import android.util.Log
import androidx.constraintlayout.helper.widget.MotionEffect
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.konden.readandcuttext.appcontroller.AppController
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.concurrent.Executors


@Database(entities = [EntityWord::class], version = 1, exportSchema = false)

 abstract class WordRoomDatabase : RoomDatabase() {
    private var jsonobject: JSONObject? = null

    abstract fun wordDao(): WordDao

    companion object {

        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instans = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java, "word_database_asra"
                )

                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            GetData_Level_IS_FIRST()
                            Log.e("TAG", "onCreate 222223456789: ")

                        }

                        private fun GetData_Level_IS_FIRST() {
                            Log.e("TAG", "GetData_Level_IS_FIRST: ")
                            val wordDao: WordDao
                            wordDao = WordRoomDatabase.INSTANCE!!.wordDao()
                            Log.e("TAG", "GetData_Level_IS_FIRST: ")
                            try {
                                val jsonArray: JSONArray = JSONArray(loadJSONFromAsset())
                                Log.e(MotionEffect.TAG, "GetData_Level: 2")
                                for (i in 0 until jsonArray!!.length()) {
                                    WordRoomDatabase.INSTANCE!!.jsonobject =
                                        jsonArray.getJSONObject(i)
                                    Log.e(MotionEffect.TAG, "GetData_Level: 3")
                                    val Number_no: Int =
                                        WordRoomDatabase.INSTANCE!!.jsonobject!!.getInt("رقم الهوية")
                                    Log.e("TAG", "getAllData: level_no ");
                                    val name: String =
                                        WordRoomDatabase.INSTANCE!!.jsonobject!!.getString("الاسم")
                                    val word: EntityWord = EntityWord(Number_no, Number_no, name)
                                    wordDao.insert(word)

                                }
                            } catch (e: JSONException) {
                                e.printStackTrace()
                                Log.e(MotionEffect.TAG, "getAllData: error")
                                Log.e("TAG", "loadJSONFromAsset:11111111111111111111111111111 ")

                            }
                        }
                        
                        private fun loadJSONFromAsset(): String? {
                            Log.e("TAG", "loadJSONFromAsset:11111111111111111111111111111")
                            val json: String
                            json = try {
                                val inputStream: InputStream =
                                    AppController.instance.getBaseContext().getAssets().open("asra.json") //تقوم بجلب ملف الجيسن
                                val size = inputStream.available() //بتجبلك البايتات فيه كم حجمها
                                val buffer = ByteArray(size)
                                //Stream :  assets  فتح قناه ما بين الكلاس الخاص فيا وما بين ملف ال
                                inputStream.read(buffer)
                                Log.e("TAG", "loadJSONFromAsset:11111111111111111111111111111 ")
                                inputStream.close()
                                String(buffer, StandardCharsets.UTF_8) // convert byte to String
                            } catch (e: IOException) {
                                Log.e(MotionEffect.TAG, "loadJSONFromAsset: ")
                                e.printStackTrace()
                                Log.e("TAG", "loadJSONFromAsset:11111111111111111111111111111 ")

                                return null
                            }
                            return json

                        }
                    })
                    .build()
                INSTANCE = instans
                return instans
            }

        }
    }
}

