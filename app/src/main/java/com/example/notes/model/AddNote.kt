package com.example.notes.model

data class AddNote(
    var id: String? = null,
    val note: String? = null,
    var amount: String? = null,
    val isEdited: Boolean = false,
    var date: String? = null,
    var time: String? = null,
    var isDelete: Boolean = false,
    var imageUrl: String? = null
)