package com.example.noteapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.data.models.NoteModels


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun noteInsert(noteModel :NoteModels)

    @Query("SELECT * FROM noteModel")
    fun getAll(): LiveData<List<NoteModels>>

    @Delete
    fun deletNot(noteModel: NoteModels)

    @Update
    fun updateNote(noteModel: NoteModels)

    @Query("SELECT *FROM noteModel WHERE id = :id")
    fun getNoteId(id:Int):NoteModels




}