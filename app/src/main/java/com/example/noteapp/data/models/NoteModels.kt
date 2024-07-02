package com.example.noteapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModels(
    val title: String,
    val description: String,
    val color:Int,
    val timeMonth:String,
    val timeHours:String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
