package com.eseo.noteapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Classe représentant une note.
 *
 * @property id L'identifiant unique de la note.
 * @property title Le titre de la note.
 * @property text Le texte de la note.
 */
@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)

    /**
     * L'identifiant unique de la note.
     */
    var id: Int = 0,

    /**
     * Le titre de la note.
     */
    @ColumnInfo(name = "title")
    var title: String,

    /**
     * Le texte de la note.
     */
    @ColumnInfo(name = "text")
    var text: String
) : Serializable