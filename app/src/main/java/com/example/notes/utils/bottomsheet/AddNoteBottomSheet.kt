package com.example.notes.utils.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notes.R
import com.example.notes.databinding.BottomSheetNotesBinding
import com.example.notes.model.AddNote
import com.example.notes.utils.firebase.FirebaseHelper
import com.example.notes.utils.firebase.FirebaseHelper.databaseReference
import com.example.notes.utils.getCurrentDate
import com.example.notes.utils.getCurrentTime
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


/**
 * Created by Abhin.
 */
class AddNoteBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetNotesBinding

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddNote.setOnClickListener {
            addNote()
        }
    }

    private fun addNote() {
        when {
            binding.edtNotes.text.isNullOrEmpty() -> {
                Toast.makeText(activity, "Please enter note", Toast.LENGTH_SHORT).show()
            }
            binding.edtAmount.text.isNullOrEmpty() -> {
                Toast.makeText(activity, "Please enter amount", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val addNote = AddNote(
                    date = getCurrentDate(),
                    time = getCurrentTime(),
                    note = binding.edtNotes.text.toString(),
                    amount = binding.edtAmount.text.toString()
                )
                FirebaseHelper.addNote(addNote)
                dismiss()
            }
        }
    }
}