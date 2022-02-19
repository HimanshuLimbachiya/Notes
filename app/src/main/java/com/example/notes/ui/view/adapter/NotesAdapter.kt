package com.example.notes.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ItemNotesBinding
import com.example.notes.model.AddNote

class NotesAdapter(var list: ArrayList<AddNote>) : RecyclerView.Adapter<NotesAdapter.NoteVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        return NoteVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_notes,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class NoteVH(var binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: AddNote) {
            binding.mData = note
        }
    }
}
