package com.eseo.noteapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eseo.noteapp.model.dao.NoteDao
import com.eseo.noteapp.model.entity.Note


/**
 * Classe de base de données pour les notes.
 *
 * @property NoteDao Objet d'accès aux données pour la table des notes.
 */
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    /**
     * Récupère l'objet d'accès aux données pour la table des notes.
     */
    abstract fun getNoteDao() : NoteDao

    companion object{

        /**
         * Instance volatile de la base de données des notes.
         */
        @Volatile
        private var NOTE_INSTANCE : NoteDatabase? = null

        /**
         * Récupère l'instance de la base de données des notes.
         *
         * @param context Le contexte de l'application.
         * @return L'instance de la base de données des notes.
         */
        fun getNoteDatabase(context: Context) : NoteDatabase {
            if (NOTE_INSTANCE == null){
                val instance = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "noteDB"
                ).build()
                NOTE_INSTANCE = instance
            }
            return NOTE_INSTANCE!!
        }
    }
}