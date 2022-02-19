package com.example.notes.ui.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.model.AddNote
import com.example.notes.ui.view.adapter.NotesAdapter
import com.example.notes.utils.MySimpleOnGestureListener
import com.example.notes.utils.MySimpleOnGestureListenerInterface
import com.example.notes.utils.firebase.FirebaseHelper
import com.example.notes.utils.firebase.NoteInterface
import com.example.notes.utils.openInsertDataBottomSheet

class HomeFragment : Fragment() {

    private lateinit var mDetector: GestureDetectorCompat

    lateinit var mBinding: FragmentHomeBinding
    var mNotesAdapter: NotesAdapter? = null
    var mList = ArrayList<AddNote>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        touchEvent(view)
        setNoteAdapter()
        FirebaseHelper.mNoteInterface = noteInterface
        FirebaseHelper.getNoteList()
        FirebaseHelper.getAddedNote()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun touchEvent(view: View) {
        mDetector = GestureDetectorCompat(activity, MySimpleOnGestureListener(listener))
        view.setOnTouchListener { _, event -> mDetector.onTouchEvent(event) }
    }

    private val listener = object : MySimpleOnGestureListenerInterface {
        override fun swipeUp() {
            activity?.openInsertDataBottomSheet()
        }
    }

    private fun setNoteAdapter() {
        mBinding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        mNotesAdapter = NotesAdapter(mList)
        mBinding.rvNotes.adapter = mNotesAdapter
    }

    private val noteInterface = object : NoteInterface {
        override fun showLoader() {
            mBinding.progressBar.visibility = View.VISIBLE
        }

        override fun hideLoader() {
            mBinding.progressBar.visibility = View.GONE
        }

        override fun getNoteList(mList: ArrayList<AddNote>) {
            this@HomeFragment.mList.clear()
            this@HomeFragment.mList.addAll(mList)
            mNotesAdapter?.notifyItemInserted(mList.size)
        }

        override fun getNote(note: AddNote) {
            mList.add(0, note)
            mNotesAdapter?.notifyItemInserted(mList.size)
        }

        override fun deleteNote(note: AddNote) {
        }

        override fun editNote(note: AddNote) {
        }
    }
}