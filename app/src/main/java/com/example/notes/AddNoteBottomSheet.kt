package com.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notes.databinding.BottomSheetNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


/**
 * Created by Abhin.
 */
class AddNoteBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetNotesBinding

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    lateinit var mDatabase: DatabaseReference

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
        mDatabase = FirebaseDatabase.getInstance().reference

        binding.btnAddNote.setOnClickListener {
            addNote()
        }
    }

    private fun addNote() {
        val hashMap = HashMap<String, String>()
        when {
            binding.edtNotes.text.isNullOrEmpty() -> {
                Toast.makeText(activity, "Please enter note", Toast.LENGTH_SHORT).show()
            }
            binding.edtAmount.text.isNullOrEmpty() -> {
                Toast.makeText(activity, "Please enter amount", Toast.LENGTH_SHORT).show()
            }
            else -> {
                hashMap["note"] = binding.edtNotes.text.toString()
                hashMap["amount"] = binding.edtAmount.text.toString()
                val id = mDatabase.push().key
                mDatabase.child("Notes").child(id!!).setValue(hashMap).addOnCompleteListener {
                    dismiss()
                }
            }
        }
    }
}