package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Director
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.School
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Student
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.Subject
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.entity.relations.StudentSubjectCrossRef
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.RepositoryData
import com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.model.TypeConverterOwner

@Database(
    entities = [
        RepositoryData::class, School::class,
        Student::class,
        Director::class,
        Subject::class,
        StudentSubjectCrossRef::class], version = 3
)
@TypeConverters(TypeConverterOwner::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao
    abstract fun getSchoolDao(): SchoolDao

    companion object {
        @Volatile
        private var DB_INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                return DB_INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "school_db"
                ).allowMainThreadQueries().build().also {
                    DB_INSTANCE = it
                }
            }
        }
    }
    //https://github.com/google/dagger/issues/2381
}