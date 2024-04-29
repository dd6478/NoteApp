package com.eseo.noteapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eseo.noteapp.model.entity.Note

/**
 * Objet d'accès aux données pour la table des notes.
 */
@Dao
interface NoteDao {

    /**
     * Insère une note dans la base de données. Si la note existe déjà, la remplace.
     * @param note la note à insérer.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    /**
     * Sélectionne toutes les notes de la base de données.
     * @return toutes les notes.
     */
    @Query("SELECT * FROM note")
    fun getAllNotes() : LiveData<List<Note>>

    /**
     * Supprime une note par id.
     * @param id l'id de la note à supprimer.
     */
    @Query("DELETE FROM note WHERE id = :id")
    suspend fun deleteNote(id : Int)

    /**
     * Met à jour une note dans la base de données. Si la note n'existe pas, rien ne se passe.
     * @param note la note à mettre à jour.
     */
    @Update(
        entity = Note::class,
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun updateNote(note: Note)

}