package com.eseo.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eseo.noteapp.model.repository.NoteRepository

/**
 * Factory pour la création de NoteViewModel.
 *
 * @property noteRepository Le répertoire des notes.
 */
class NoteViewModelFactory(
    private val noteRepository : NoteRepository
) : ViewModelProvider.Factory {

    /**
     * Crée une nouvelle instance de NoteViewModel.
     *
     * @param modelClass La classe du ViewModel à créer.
     * @return Une instance de NoteViewModel.
     * @throws IllegalArgumentException si modelClass n'est pas assignable à partir de NoteViewModel.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Classe ViewModel inconnue")
    }

}