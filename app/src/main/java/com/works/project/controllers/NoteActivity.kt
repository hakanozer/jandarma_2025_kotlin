package com.works.project.controllers

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.works.project.R
import com.works.project.configs.AppDatabase
import com.works.project.dao.NoteDao
import com.works.project.entities.Note
import java.util.Date

class NoteActivity : AppCompatActivity() {

    lateinit var db: AppDatabase
    lateinit var noteDao: NoteDao
    lateinit var n_txtTitle: EditText
    lateinit var n_txtDetail: EditText
    lateinit var n_btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_note)

        n_txtTitle = findViewById(R.id.n_txtTitle)
        n_txtDetail = findViewById(R.id.n_txtDetail)
        n_btnSave = findViewById(R.id.n_btnSave)

        db = Room.databaseBuilder(this, AppDatabase::class.java, "project.db").allowMainThreadQueries().build()
        noteDao = db.noteDao()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        n_btnSave.setOnClickListener {
            noteSave()
        }
    }

    fun noteSave() {
        val title = n_txtTitle.text.toString()
        val content = n_txtDetail.text.toString()
        val date = Date().toString()
        val note = Note(title = title, content = content, date = date)
        val status = noteDao.insert(note)
        if (status > 0) {
            Toast.makeText(this, "Note save success", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, "Note save fail", Toast.LENGTH_SHORT).show()
        }
    }

}