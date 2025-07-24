package com.works.project.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.works.project.entities.Note

@Dao
interface NoteDao {

    // insert note
    @Insert fun insert(note: Note) : Long

    @Delete fun delete(note: Note) : Int

    @Query("SELECT * FROM notes")
    fun getAll(): List<Note>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getById(id: Int): Note


}