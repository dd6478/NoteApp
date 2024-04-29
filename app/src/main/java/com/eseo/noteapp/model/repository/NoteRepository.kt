package com.eseo.noteapp.model.repository
import androidx.lifecycle.LiveData
import com.eseo.noteapp.model.dao.NoteDao
import com.eseo.noteapp.model.entity.Note

/**
 * Classe de répertoire pour gérer les opérations de la base de données des notes.
 *
 * @property noteDao Objet d'accès aux données pour la table des notes.
 * @property notes LiveData de la liste des notes.
 */
class NoteRepository(
    private val noteDao: NoteDao
) {

    /**
     * LiveData de la liste des notes.
     */
    val notes : LiveData<List<Note>> = noteDao.getAllNotes()

    /**
     * Insère une note dans la base de données.
     *
     * @param note La note à insérer.
     */
    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    /**
     * Supprime une note de la base de données par son id.
     *
     * @param id L'id de la note à supprimer.
     */
    suspend fun deleteNote(id : Int){
        noteDao.deleteNote(id)
    }

    /**
     * Met à jour une note existante dans la base de données.
     *
     * @param note La note avec les informations mises à jour.
     */
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

}