package com.eseo.noteapp

import android.app.Application
import com.eseo.noteapp.model.NoteDatabase
import com.eseo.noteapp.model.repository.NoteRepository

/**
 * Application principale qui initialise la base de données et le répertoire.
 */
class NoteApplication : Application() {

    /**
     * Initialisation paresseuse de la base de données.
     */
    val database by lazy { NoteDatabase.getNoteDatabase(this) }

    /**
     * Initialisation paresseuse du répertoire.
     */
    val repository by lazy { NoteRepository(database.getNoteDao()) }

}