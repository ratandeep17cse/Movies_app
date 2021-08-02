package com.example.movies_app

import android.content.Context
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.NullPointerException

@Database(entities = [Note::class], version=1)
abstract class MovieDatabase:RoomDatabase() {

    abstract fun noteDao() : MovieDao
    companion object {
        @Volatile
         var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "offline_data"

                    ).build()
                    INSTANCE = instance

                    //println("Monkke")
                }

                return instance
            }

        }

          private val roomCallBack1=object:RoomDatabase.Callback(){

            override fun onCreate(db: SupportSQLiteDatabase) {

                super.onCreate(db)
                println("bbq")
//                db.execSQL("insert into note_table(title,description,priority) values ('Note 1','This is Note 1',1);")
//                db.execSQL("insert into note_table(title,description,priority) values ('Note 2','This is Note 2',2);")
//                db.execSQL("insert into note_table(title,description,priority) values ('Note 3','This is Note 3',3);")


            }

        }
//        suspend fun populate(db: SupportSQLiteDatabase)
//        {
//            withContext(Dispatchers.IO)
//            {
//                db.execSQL("insert into note_table(Title,Description) values ('Note 1','This is Note 2');")
//                db.execSQL("insert into note_table(Title,Description) values ('Note 2','This is Note 2');")
//                db.execSQL("insert into note_table(Title,Description) values ('Note 2','This is Note 2');")
//            }
//        }
    }

}