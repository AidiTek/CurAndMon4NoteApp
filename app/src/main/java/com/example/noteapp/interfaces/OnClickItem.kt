package com.example.noteapp.interfaces

import com.example.noteapp.data.models.NoteModels

interface OnClickItem {

    fun onLongClick(noteModel: NoteModels)

    fun onClick(noteModel: NoteModels)

}