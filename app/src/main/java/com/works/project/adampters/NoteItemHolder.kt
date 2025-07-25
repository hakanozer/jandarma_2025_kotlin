package com.works.project.adampters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.works.project.R
import com.works.project.entities.Note

class NoteItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(itemNote: Note) {
        val nr_title = itemView.findViewById<TextView>(R.id.nr_title)
        val nr_detail = itemView.findViewById<TextView>(R.id.nr_detail)
        nr_title.text = itemNote.title
        nr_detail.text = itemNote.content
    }

}