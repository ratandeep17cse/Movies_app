package com.example.movies_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MovieViewModel(application: MovieRepository) : ViewModel(){

    private var repository=application
    private var allNotes=repository.getAllNotes()


     fun insert(note: Note)=viewModelScope.launch() {
        repository.insert(note!!)
    }

//    fun insertList(list:List<Note>)=viewModelScope.launch(){
//
//    }
    fun update(note: Note)=viewModelScope.launch() {
        repository.update(note!!)
    }

    fun delete(note: Note)=viewModelScope.launch() {
        repository.delete(note!!)
    }

//    suspend fun deleteAllNotes() {
//        repository.deleteAllNotes()
//    }

    fun getAllNotes(): LiveData<List<Note>>{
        return allNotes
    }
     fun deleteAllNotes() = viewModelScope.launch{
        repository.deleteAllNotes()
    }


}
