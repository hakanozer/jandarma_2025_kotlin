package com.works.project.adampters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.works.project.R
import com.works.project.configs.AppDatabase
import com.works.project.entities.Note

class NoteAdapter(private val list: MutableList<Note>) : RecyclerView.Adapter<NoteItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemHolder {
        val view = NoteItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))
        val db = Room.databaseBuilder(parent.context, AppDatabase::class.java, "project.db").allowMainThreadQueries().build()
        val noteDao = db.noteDao()
        view.itemView.setOnLongClickListener {
            noteDao.delete(list[view.adapterPosition])
            // delete list item
            list.removeAt(view.adapterPosition)
            notifyItemRemoved(view.adapterPosition)
            return@setOnLongClickListener true
        }
        return view
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NoteItemHolder, position: Int) {
        holder.bindItem(list[position])
    }

}