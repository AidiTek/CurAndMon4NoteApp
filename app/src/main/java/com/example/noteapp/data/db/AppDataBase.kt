package com.example.noteapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.data.db.daos.NoteDao
import com.example.noteapp.data.models.NoteModels

@Database(entities = [NoteModels::class],version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun notesDao(): NoteDao
}