package com.example.movies_app

import android.app.Application
import android.os.AsyncTask
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.loader.content.AsyncTaskLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MovieRepository(application: Application) {
    public var database = MovieDatabase.getInstance(application)
    private var movieDao = database.noteDao()
    private var allNotes = movieDao.getData()
    //private var noteDao:NoteDao?=null
   // private var allNotes: LiveData<List<Note>>?=null

//    fun NoteRepository(application: Application?) {
//        val database = NoteDatabase.getInstance(application!!)
//        noteDao = database.noteDao
//        allNotes = noteDao!!.getAllNotes()
//    }

   suspend fun insert(note: Note) {

        withContext(Dispatchers.IO)
        {
            movieDao.insert(note)
        }

    }

    suspend fun update(note: Note) {

        withContext(Dispatchers.IO)
        {
            movieDao.update(note)
        }
    }

    suspend fun delete(note: Note) {
       // DeleteNoteAsyncTask(noteDao!!).execute(note)

        withContext(Dispatchers.IO)
        {
            movieDao.delete(note)
        }
    }

    suspend fun deleteAllNotes() {
        //DeleteAllNotesAsyncTask(noteDao!!).execute()
        withContext(Dispatchers.IO)
        {
            movieDao.DeleteAll()
        }
    }

    fun getAllNotes(): LiveData<List<Note>> {

            return allNotes

    }
//    private class InsertNoteAsyncTask : AsyncTask{
//
//    }



}