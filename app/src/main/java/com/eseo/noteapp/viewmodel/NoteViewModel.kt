package com.eseo.noteapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.model.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel pour gérer les opérations liées aux notes.
 *
 * @property noteRepository Le répertoire des notes.
 * @property notesLiveData LiveData de la liste des notes.
 */
class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    /**
     * LiveData de la liste des notes.
     */
    val notesLiveData: LiveData<List<Note>> = noteRepository.notes

    /**
     * Insère une note dans la base de données.
     *
     * @param note La note à insérer.
     */
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insertNote(note)
    }

    /**
     * Supprime une note de la base de données par son id.
     *
     * @param id L'id de la note à supprimer.
     */
    fun deleteNote(id: Int) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.deleteNote(id)
    }

    /**
     * Met à jour une note existante dans la base de données.
     *
     * @param note La note avec les informations mises à jour.
     */
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.updateNote(note)
    }
}