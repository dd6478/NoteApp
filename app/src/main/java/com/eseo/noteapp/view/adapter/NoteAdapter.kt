package com.eseo.noteapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eseo.noteapp.R
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.view.activity.UpdateActivity
import com.eseo.noteapp.viewmodel.NoteViewModel

/**
 * Adaptateur pour afficher les notes dans un RecyclerView.
 *
 * @property noteViewModel Le ViewModel des notes.
 */
class NoteAdapter(
    private val noteViewModel: NoteViewModel
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private lateinit var notes : MutableList<Note>

    init {
        notes = mutableListOf()
    }

    /**
     * Crée une nouvelle vue (invoquée par le gestionnaire de mise en page).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val noteView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(noteView)
    }

    /**
     * Remplace le contenu de la vue (invoquée par le gestionnaire de mise en page).
     */
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindNote(notes[position])
    }

    /**
     * Retourne la taille de votre ensemble de données (invokée par le gestionnaire de mise en page).
     */
    override fun getItemCount(): Int {
        return notes.size
    }

    /**
     * Charge les notes dans l'adaptateur.
     *
     * @param notes La liste des notes à charger.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun loadNotes(notes: List<Note>?) {
        this.notes = notes as MutableList<Note>
        notifyDataSetChanged()
    }

    /**
     * Fournit une référence aux vues pour chaque élément de données.
     */
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var noteTitle : TextView
        private var noteText : TextView
        private var editNoteImage : ImageView
        private var deleteNoteImage : ImageView

        init {
            noteTitle = itemView.findViewById(R.id.noteTitle)
            noteText = itemView.findViewById(R.id.noteText)
            editNoteImage = itemView.findViewById(R.id.editNoteImage)
            deleteNoteImage = itemView.findViewById(R.id.deleteNoteImage)
        }

        /**
         * Lie une note à la vue.
         *
         * @param note La note à lier.
         */
        fun bindNote(note: Note){
            noteText.text = note.text
            noteTitle.text = note.title
            editNoteImage.setOnClickListener {

                val context : Context = itemView.context
                val noteIntent = Intent(context, UpdateActivity::class.java)
                noteIntent.putExtra("UPDATE", note)
                context.startActivity(noteIntent)

            }

            deleteNoteImage.setOnClickListener {
                noteViewModel.deleteNote(note.id)
            }
        }
    }
}