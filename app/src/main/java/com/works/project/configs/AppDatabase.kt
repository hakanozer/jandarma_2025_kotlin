package com.works.project.configs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.works.project.dao.NoteDao
import com.works.project.entities.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}