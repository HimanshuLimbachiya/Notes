package com.example.notes.utils.firebase

import com.example.notes.model.AddNote
import com.example.notes.utils.NOTES
import com.google.firebase.database.*

object FirebaseHelper {

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var mNoteInterface: NoteInterface

    fun initFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference(NOTES)
    }

    fun addNote(note: AddNote?) {
        val id = databaseReference.push().key
        note?.id = id
        if (!id.isNullOrEmpty()) {
            databaseReference.child(id).setValue(note)
        }
    }

    fun getNoteList() {
        val mList = ArrayList<AddNote>()
        mNoteInterface.showLoader()
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mList.clear()
                for (dataSnapshot1 in snapshot.children) {
                    val addNote: AddNote = dataSnapshot1.getValue(AddNote::class.java)!!
                    mList.add(addNote)
                }
                mNoteInterface.getNoteList(mList)
                //    mNoteInterface.hideLoader()
            }

            override fun onCancelled(error: DatabaseError) {
                mNoteInterface.hideLoader()
            }
        })
    }

    fun getAddedNote() {
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val addNote: AddNote = snapshot.getValue(AddNote::class.java)!!
                mNoteInterface.getNote(addNote)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}

interface NoteInterface {
    fun showLoader()
    fun hideLoader()
    fun getNoteList(mList: ArrayList<AddNote>)
    fun getNote(note: AddNote)
    fun deleteNote(note: AddNote)
    fun editNote(note: AddNote)
}